package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.controller.CartPanelController;
import org.project.salesystem.customer.dao.implementation.CartDAOImpl;
import org.project.salesystem.customer.dao.implementation.CartItemDAOImpl;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.customer.session.Session;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * JPanel that represents the cart interface where users can view, manage,
 * and purchase items in their shopping cart.
 */
public class CartPanel extends JPanel {

    private JTable cartTable;
    private CartProductTableModel tableModel;
    private JButton btnDeleteItem, btnClearCart, btnPurchase;
    private Cart currentCart;
    private final CartPanelController controller;

    /**
     * Constructor that initializes the cart panel with components
     * and sets up event listeners.
     */
    public CartPanel() {
        setLayout(new BorderLayout());
        initComponents();
        controller = new CartPanelController(this);
        add(new JScrollPane(cartTable), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Initializes the components of the CartPanel, including the cart data,
     * the cart table, and the action buttons.
     */
    private void initComponents() {
        CartDAOImpl cartDAO = new CartDAOImpl();
        currentCart = cartDAO.getCartByCustomerId(Session.getCurrentCustomer());

        if (currentCart == null) {
            currentCart = new Cart(Session.getCurrentCustomer());  // Creates a new cart if it doesn't exist
            cartDAO.create(currentCart);  // Saves the new cart in the database
        }

        CartItemDAOImpl cartItemDAO = new CartItemDAOImpl();
        List<CartItem> cartItems = cartItemDAO.getCartItems(currentCart.getCartId());

        tableModel = new CartProductTableModel(cartItems);
        cartTable = new JTable(tableModel);

        btnDeleteItem = new JButton("Delete Item");
        btnDeleteItem.addActionListener(e -> controller.deleteSelectedItem());
        btnClearCart = new JButton("Clear Cart");
        btnClearCart.addActionListener(e -> controller.clearCart());
        btnPurchase = new JButton("Purchase");
        btnPurchase.addActionListener(e -> controller.generateSale());
    }

    /**
     * Creates a panel containing buttons for managing the cart.
     *
     * @return JPanel with buttons to manage the cart.
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(btnDeleteItem);
        panel.add(btnClearCart);
        panel.add(btnPurchase);
        return panel;
    }

    /**
     * Returns the table model used by the cart table.
     *
     * @return CartProductTableModel for the cart table.
     */
    public CartProductTableModel getTableModel() {
        return tableModel;
    }

    /**
     * Returns the cart table component.
     *
     * @return JTable representing the cart table.
     */
    public JTable getCartTable() {
        return cartTable;
    }

    /**
     * Returns the current cart being used by the customer.
     *
     * @return Cart object representing the current cart.
     */
    public Cart getCurrentCart() {
        return currentCart;
    }
}
