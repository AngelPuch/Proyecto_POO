package org.project.salesystem.customer.controller;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.dao.implementation.CartItemDAOImpl;
import org.project.salesystem.customer.gui.CartPanel;
import org.project.salesystem.customer.gui.CustomerPanel;
import org.project.salesystem.customer.gui.CustomerProductTableModel;
import org.project.salesystem.customer.dao.implementation.CartDAOImpl;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.customer.session.Session;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CustomerPanelController{
    private CustomerPanel customerPanel;
    private CustomerProductTableModel customerProductTableModel;
    private JTable table;
    private CartDAOImpl cartDAO;


    public CustomerPanelController(CustomerPanel customerPanel, CustomerProductTableModel customerProductTableModel, JTable table, CartDAOImpl cartDAO) {
        this.customerPanel = customerPanel;
        this.customerProductTableModel = customerProductTableModel;
        this.table = table;
        this.cartDAO = cartDAO;
    }

    public CustomerPanelController(CustomerPanel customerPanel) {
        this.customerPanel = customerPanel;
    }


    public void addToCartAction() {
        int selectedRow = customerPanel.getProductTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(customerPanel, "Por favor, selecciona un producto.");
            return;
        }

        String input = customerPanel.getTextField().getText();
        if (input.isEmpty() || !input.matches("\\d+")) {
            JOptionPane.showMessageDialog(customerPanel, "Ingresa una cantidad válida.");
            return;
        }

        int quantity = Integer.parseInt(input);
        Product product = customerPanel.getTableModel().getProductAt(selectedRow);

        if (quantity > product.getStock()) {
            JOptionPane.showMessageDialog(customerPanel, "No hay suficiente stock para la cantidad solicitada.");
        } else {
            Customer currentCustomer = Session.getCurrentCustomer();
            CartDAOImpl cartDAO = new CartDAOImpl();
            Cart cart = cartDAO.getCartByCustomerId(currentCustomer);

            if (cart == null) {
                // Si no se encuentra el carrito, lo creamos
                cart = new Cart(currentCustomer);  // Creamos un nuevo carrito para el cliente
                cartDAO.create(cart);  // Guardamos el carrito en la base de datos
            }
            CartItem cartItem = new CartItem(quantity, cart, product);
            CartItemDAOImpl cartItemDAO = new CartItemDAOImpl();
            cartItemDAO.addCartItem(cartItem);

            JOptionPane.showMessageDialog(customerPanel, "Producto añadido al carrito.");
        }
    }

}


