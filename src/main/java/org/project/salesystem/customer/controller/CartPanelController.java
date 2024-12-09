package org.project.salesystem.customer.controller;

import org.project.salesystem.admin.dao.ProductDAO;
import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.customer.dao.implementation.CartItemDAOImpl;
import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.dao.implementation.SaleDetailDAOImpl;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.customer.model.SaleDetail;
import org.project.salesystem.customer.session.Session;
import org.project.salesystem.customer.gui.CartPanel;
import org.project.salesystem.database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Controller that handles actions within the customer's shopping cart panel.
 * Allows managing the deletion of products from the cart, clearing the cart, and generating a purchase.
 */
public class CartPanelController {

    private final CartPanel cartPanel;
    private final CartItemDAOImpl cartItemDAO;
    private final SaleDAOImpl saleDAO;
    private final SaleDetailDAOImpl saleDetailDAO;

    /**
     * Constructor for the CartPanelController class.
     * Initializes the controller with the cart panel and the corresponding DAOs.
     *
     * @param cartPanel The cart panel where the actions are performed.
     */
    public CartPanelController(CartPanel cartPanel) {
        this.cartPanel = cartPanel;
        this.cartItemDAO = new CartItemDAOImpl();
        this.saleDAO = new SaleDAOImpl();
        this.saleDetailDAO = new SaleDetailDAOImpl();
    }

    /**
     * Deletes the selected product from the cart.
     * Shows a confirmation message if the deletion was successful.
     */
    public void deleteSelectedItem() {
        int selectedRow = cartPanel.getCartTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(cartPanel, "Seleccione un producto para eliminar.");
            return;
        }

        CartItem selectedItem = cartPanel.getTableModel().getCartItemAt(selectedRow);
        cartItemDAO.delete(selectedItem.getCartItemId());
        cartPanel.getTableModel().removeCartItem(selectedRow);
        JOptionPane.showMessageDialog(cartPanel, "Producto eliminado del carrito.");
        System.out.println("Fila seleccionada: " + selectedRow);
        System.out.println("CartItem ID: " + selectedItem.getCartItemId());
    }

    /**
     * Clears the shopping cart after user confirmation.
     * Shows a message if the cart has been cleared successfully.
     */
    public void clearCart() {
        int confirmation = JOptionPane.showConfirmDialog(cartPanel, "¿Estás seguro de que quieres vaciar el carrito?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            int cartId = cartPanel.getCurrentCart().getCartId();
            cartItemDAO.clearCart(cartId);
            cartPanel.getTableModel().clearCartItems();
            JOptionPane.showMessageDialog(cartPanel, "El carrito ha sido vaciado.");
        }
    }

    /**
     * Generates a sale from the items in the cart.
     * Creates a sale and its details, updating the stock of the products.
     * Shows a message upon successful purchase.
     */
    public void generateSale() {
        ProductDAO productDAO = new ProductDAOImpl();
        List<CartItem> cartItems = cartPanel.getTableModel().getCartItems();
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(cartPanel, "El carro esta vacío.");
            return;
        }

        double total = cartItems.stream().mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum();
        Sale sale = new Sale();
        sale.setDateOfSale(new Date());
        sale.setTotal(total);
        sale.setCustomer(Session.getCurrentCustomer());

        saleDAO.create(sale);

        for (CartItem item : cartItems) {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSale(sale);
            saleDetail.setProduct(item.getProduct());
            saleDetail.setQuantity(item.getQuantity());
            saleDetail.setProductTotal(item.getQuantity() * item.getProduct().getPrice());
            saleDetailDAO.create(saleDetail);

            productDAO.updateProductStock(item.getProduct().getId(), item.getQuantity());
        }

        JOptionPane.showMessageDialog(cartPanel, "Compra generada exitosamente.");
        cartItemDAO.clearCart(cartPanel.getCurrentCart().getCartId());
        cartPanel.getTableModel().clearCartItems();

        List<SaleDetail> saleDetails = saleDetailDAO.getSaleDetailsBySaleId(sale.getSaleId());
        Window window = SwingUtilities.getWindowAncestor(cartPanel);
        SaleDetailDialog saleDetailDialog = new SaleDetailDialog((Frame) window, saleDetails);
        saleDetailDialog.setVisible(true);
    }

}


