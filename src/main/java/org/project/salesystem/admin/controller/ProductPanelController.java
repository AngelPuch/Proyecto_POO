package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.gui.ProductPanel;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;
import org.project.salesystem.customer.dao.SaleDetailDAO;
import org.project.salesystem.customer.dao.implementation.SaleDetailDAOImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductPanelController handles the user actions related to managing products in the inventory
 * It processes the add, delete, and search actions, updating the product list and interacting with the DAO layer
 */

public class ProductPanelController {
    private ProductPanel inventarioPanel;;
    private ProductDAOImpl productDAO;

    public ProductPanelController(ProductPanel inventarioPanel) {
        this.inventarioPanel = inventarioPanel;
        productDAO = new ProductDAOImpl();
    }

    /**
     * Handles the action to add a new {@link Product} to the inventory
     * It validates the input fields, creates a product, and updates the table model
     */
    public void addProductAction(){
        if (validateNonEmptyField()){
            if (areValidInputs()) {
                Product product = createProduct();
                inventarioPanel.getProductTableModel().addProduct(product);
                clearFields();
            }
        }else{
            JOptionPane.showMessageDialog(inventarioPanel, "Todos los campos son obligatorios");
        }
    }

    /**
     * Handles the action to delete a selected {@link Product} from the inventory
     * It removes the selected {@link Product} from the table model
     */
    public void deleteProductAction() {
        int selectedRow = inventarioPanel.getTable().getSelectedRow();
        if (selectedRow != -1){
            int productID = inventarioPanel.getProductTableModel().getProductList().get(selectedRow).getId();
            SaleDetailDAO saleDetailDAO = new SaleDetailDAOImpl();
            if (saleDetailDAO.hasSaleDetailsAssociatedWithProducts(productID)) {
                JOptionPane.showMessageDialog(inventarioPanel,
                        "No se puede eliminar el producto porque esta asociado a otras tablas");
            }else {
                inventarioPanel.getProductTableModel().removeProduct(selectedRow);
            }
        }else{
            JOptionPane.showMessageDialog(inventarioPanel, "Selecciona una fila para eliminar");
        }
    }

    /**
     * Handles the action to filter the {@link List} of {@link Product} based on the search input
     * The {@link Product} {@link List} is filtered based on the name of the product
     */
    public void filterProductListAction(){
        String searchText = inventarioPanel.getSearchField().getText().trim();
        List<Product> filteredProductList = new ArrayList<>();
        for (Product product: productDAO.readAll()){
            if (product.getName().toLowerCase().contains(searchText.toLowerCase())){
                filteredProductList.add(product);
            }
        }

        if (filteredProductList.isEmpty()) {
            inventarioPanel.setMessage("No se encontraron coincidencias");
            inventarioPanel.getProductTableModel().showFilteredList(new ArrayList<>());
        }else {
            inventarioPanel.setMessage("");
            inventarioPanel.getProductTableModel().showFilteredList(filteredProductList);
        }
    }

    /**
     * Validates that all the necessary fields are filled out
     * @return {@code true} if all fields are filled, {@code false} otherwise
     */
    public boolean validateNonEmptyField(){
        return !inventarioPanel.getNameField().getText().trim().isEmpty() &&
                !inventarioPanel.getPriceField().getText().trim().isEmpty() &&
                !inventarioPanel.getStockField().getText().trim().isEmpty();
    }

    /**
     * Creates a {@link Product} object from the data entered in the input fields
     * @return the created {@link Product} instance
     */
    private Product createProduct(){
        Product product = new Product();
        product.setName(inventarioPanel.getNameField().getText());
        product.setPrice(Double.parseDouble(inventarioPanel.getPriceField().getText()));
        product.setStock(Integer.parseInt(inventarioPanel.getStockField().getText()));
        product.setSupplier((Supplier) inventarioPanel.getComboTypeSupplier().getSelectedItem());
        product.setCategory((Category) inventarioPanel.getComboTypeCategory().getSelectedItem());
        return product;
    }

    /**
     * Validates that the price and stock input fields contain valid numerical values.
     * @return {@code true} if both price and stock are valid positive numbers,
     *         {@code false} otherwise.
     */
    public boolean areValidInputs() {
        try {
            double price = Double.parseDouble(inventarioPanel.getPriceField().getText());
            int stock = Integer.parseInt(inventarioPanel.getStockField().getText());
            if (price < 0 || stock < 0) {
                JOptionPane.showMessageDialog(inventarioPanel,
                        "Los valores del precio y del stock deben ser números positivos");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(inventarioPanel,
                    "Por favor, ingresa un valor numérico valido para los campos de precio y stock");
            return false;
        }
    }

    /**
     * Clears the input fields for product data
     */
    private void clearFields() {
        inventarioPanel.getNameField().setText("");
        inventarioPanel.getPriceField().setText("");
        inventarioPanel.getStockField().setText("");
    }

}
