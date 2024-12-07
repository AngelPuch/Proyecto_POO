package org.project.salesystem.customer.controller;

import org.project.salesystem.customer.dao.implementation.CartItemDAOImpl;
import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.dao.implementation.SaleDetailDAOImpl;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.customer.model.SaleDetail;
import org.project.salesystem.customer.session.Session;
import org.project.salesystem.customer.gui.CartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class CartPanelController {
    private final CartPanel cartPanel;
    private final CartItemDAOImpl cartItemDAO;
    private final SaleDAOImpl saleDAO;
    private final SaleDetailDAOImpl saleDetailDAO;

    public CartPanelController(CartPanel cartPanel) {
        this.cartPanel = cartPanel;
        this.cartItemDAO = new CartItemDAOImpl();
        this.saleDAO = new SaleDAOImpl();
        this.saleDetailDAO = new SaleDetailDAOImpl();
    }

    public void deleteSelectedItem() {
        int selectedRow = cartPanel.getCartTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(cartPanel, "Seleccione un producto para eliminar.");
            return;
        }

        CartItem selectedItem = cartPanel.getTableModel().getCartItemAt(selectedRow);
        cartItemDAO.deleteCartItem(selectedItem.getCartItemId());
        cartPanel.getTableModel().removeCartItem(selectedRow);
        JOptionPane.showMessageDialog(cartPanel, "Producto eliminado del carrito.");
        System.out.println("Fila seleccionada: " + selectedRow);
        System.out.println("ID del CartItem: " + selectedItem.getCartItemId());
    }

    public void clearCart() {
        int confirmation = JOptionPane.showConfirmDialog(cartPanel, "¿Está seguro de vaciar el carrito?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            int cartId = cartPanel.getCurrentCart().getCartId();
            cartItemDAO.clearCart(cartId);
            cartPanel.getTableModel().clearCartItems();
            JOptionPane.showMessageDialog(cartPanel, "El carrito ha sido vaciado.");
        }
    }

    public void generateSale() {
        List<CartItem> cartItems = cartPanel.getTableModel().getCartItems();
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(cartPanel, "El carrito está vacío.");
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
        }

        JOptionPane.showMessageDialog(cartPanel, "Compra generada correctamente.");
        cartItemDAO.clearCart(cartPanel.getCurrentCart().getCartId());
        cartPanel.getTableModel().clearCartItems();

        List<SaleDetail> saleDetails = saleDetailDAO.getSaleDetailsBySaleId(sale.getSaleId());
        Window window = SwingUtilities.getWindowAncestor(cartPanel);
        SaleDetailDialog saleDetailDialog = new SaleDetailDialog((Frame) window, saleDetails);
        saleDetailDialog.setVisible(true);
    }
}

