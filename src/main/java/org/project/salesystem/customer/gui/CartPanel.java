package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.controller.CartPanelController;
import org.project.salesystem.customer.controller.CustomerPanelController;
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
    private CartPanelController controller;

    public CartPanel() {
        setLayout(new BorderLayout());
        initComponents();
        controller = new CartPanelController(this);
        add(new JScrollPane(cartTable), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private void initComponents() {

        int customerId = Session.getCurrentCustomer().getCustomerId();
        System.out.println("Customer ID obtenido: " + customerId);

        CartDAOImpl cartDAO = new CartDAOImpl();
        currentCart = cartDAO.getCartByCustomerId(Session.getCurrentCustomer());

        if (currentCart == null) {
            currentCart = new Cart(Session.getCurrentCustomer());  // Crea un nuevo carrito si no existe
            cartDAO.create(currentCart);  // Guarda el nuevo carrito en la base de datos
            System.out.println("Nuevo carrito creado para el cliente.");
        } else {
            System.out.println("Carrito existente cargado: " + currentCart.getCartId());
        }

        System.out.println("Cart ID obtenido: " + currentCart.getCartId());

        CartItemDAOImpl cartItemDAO = new CartItemDAOImpl();
        List<CartItem> cartItems = cartItemDAO.getCartItems(currentCart.getCartId());

        if (cartItems.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Items encontrados en el carrito: " + cartItems.size());
        }

        tableModel = new CartProductTableModel(cartItems);
        cartTable = new JTable(tableModel);

        btnDeleteItem = new JButton("Eliminar producto");
        btnDeleteItem.addActionListener(e -> controller.deleteSelectedItem());
        btnClearCart = new JButton("Vaciar carrito");
        btnClearCart.addActionListener(e -> controller.clearCart());
        btnPurchase = new JButton("Comprar");
        btnPurchase.addActionListener(e -> controller.generateSale());
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

    public Cart getCurrentCart() {
        return currentCart;
    }
}

