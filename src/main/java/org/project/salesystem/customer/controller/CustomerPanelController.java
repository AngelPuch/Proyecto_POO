package org.project.salesystem.customer.controller;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.dao.implementation.CartItemDAOImpl;
import org.project.salesystem.customer.gui.CustomerPanel;
import org.project.salesystem.customer.gui.CustomerProductTableModel;
import org.project.salesystem.customer.dao.implementation.CartDAOImpl;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.customer.session.Session;

import javax.swing.*;


public class CustomerPanelController{
    private CustomerPanel customerPanel;

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
            CartItem cartItem = getCartItem(quantity, product);
            CartItemDAOImpl cartItemDAO = new CartItemDAOImpl();
            cartItemDAO.addCartItem(cartItem);

            JOptionPane.showMessageDialog(customerPanel, "Producto añadido al carrito.");
        }
    }

    private static CartItem getCartItem(int quantity, Product product) {
        Customer currentCustomer = Session.getCurrentCustomer();
        CartDAOImpl cartDAO = new CartDAOImpl();
        Cart cart = cartDAO.getCartByCustomerId(currentCustomer);

        if (cart == null) {
            // Si no se encuentra el carrito, lo creamos
            cart = new Cart(currentCustomer);  // Creamos un nuevo carrito para el cliente
            cartDAO.create(cart);  // Guardamos el carrito en la base de datos
        }
        CartItem cartItem = new CartItem(quantity, cart, product);
        return cartItem;
    }

}


