package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.dao.implementation.CartDAOImpl;
import org.project.salesystem.customer.dao.implementation.CartItemDAOImpl;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.customer.session.Session;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CartPanel extends JPanel {
    private JTable cartTable;
    private CartProductTableModel tableModel;
    private JButton btnDeleteItem, btnClearCart, btnPurchase;
    private Cart currentCart;

    public CartPanel() {
        setLayout(new BorderLayout());
        initComponents();
        add(new JScrollPane(cartTable), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private void initComponents() {
        int customerId = Session.getCurrentCustomer().getCustomerId();
        CartDAOImpl cartDAO = new CartDAOImpl();
        currentCart = cartDAO.getCartByCustomerId(Session.getCurrentCustomer());

        if (currentCart == null) {
            JOptionPane.showMessageDialog(this, "No tienes un carrito de compras. Crea uno primero.");
            return;
        }

        // Crear el modelo de la tabla con los productos del carrito
        CartItemDAOImpl cartItemDAO = new CartItemDAOImpl();
        List<CartItem> cartItems = cartItemDAO.getCartItems(currentCart.getCartId());

        tableModel = new CartProductTableModel(cartItems);
        cartTable = new JTable(tableModel);

        btnDeleteItem = new JButton("Eliminar producto");
        btnClearCart = new JButton("Vaciar carrito");
        btnPurchase = new JButton("Comprar");
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(btnDeleteItem);
        panel.add(btnClearCart);
        panel.add(btnPurchase);
        return panel;
    }

    public CartProductTableModel getTableModel() {
        return tableModel;
    }

    public JTable getCartTable() {
        return cartTable;
    }

    public JButton getBtnDeleteItem() {
        return btnDeleteItem;
    }

    public JButton getBtnClearCart() {
        return btnClearCart;
    }

    public JButton getBtnPurchase() {
        return btnPurchase;
    }
}

