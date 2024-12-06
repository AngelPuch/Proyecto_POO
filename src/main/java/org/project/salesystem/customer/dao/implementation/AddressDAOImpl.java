package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.customer.model.Address;
import org.project.salesystem.database.dao.DatabaseConnection;
import org.project.salesystem.database.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements DAO<Address> {
    @Override
    public void create(Address address) {
        String query = "INSERT INTO address VALUES (null, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            setAddressValues(address, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la nueva dirección", e);
        }
    }

    @Override
    public Address read(Integer id) {
        Address address = null;
        String query = "SELECT * FROM address WHERE addres_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    address = convertToAddress(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la dirección con el ID: " + id, e);
        }
        return address;
    }

    @Override
    public void update(Address address) {
        String query = "UPDATE address SET postal_code = ?, street = ?, number = ?, " +
                "city = ?, state = ?, country = ? WHERE addres_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            setAddressValues(address, ps);
            ps.setInt(7, address.getAddressId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM address WHERE addres_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la dirección con el ID: " + id, e);
        }
    }

    @Override
    public List<Address> readAll() {
        List<Address> addressList = new ArrayList<>();
        String query = "SELECT * FROM address";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                addressList.add(convertToAddress(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el registro de todas las direcciones", e);
        }
        return addressList;
    }

    private void setAddressValues(Address address, PreparedStatement ps) throws SQLException {
        ps.setString(1, address.getPostalCode());
        ps.setString(2, address.getStreet());
        ps.setInt(3, address.getNumber());
        ps.setString(4, address.getCity());
        ps.setString(5, address.getState());
        ps.setString(6, address.getCountry());
    }

    private Address convertToAddress(ResultSet rs) throws SQLException {
        Address address = new Address(
                rs.getInt("addres_id"),
                rs.getString("postal_code"),
                rs.getString("street"),
                rs.getInt("number"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("country")
        );
        return address;
    }
}
