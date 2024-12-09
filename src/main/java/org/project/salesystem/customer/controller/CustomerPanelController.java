package org.project.salesystem.customer.controller;

import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.dao.implementation.CartItemDAOImpl;
import org.project.salesystem.customer.gui.CustomerPanel;
import org.project.salesystem.customer.dao.implementation.CartDAOImpl;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.customer.session.Session;

import javax.swing.*;

/**
 * This class is responsible for controlling the actions related to the customer panel.
 * It handles the logic for adding products to the shopping cart and managing the customer's cart.
 */
public class CustomerPanelController {

    private CustomerPanel customerPanel;

    /**
     * Constructor that initializes the controller with the customer panel.
     *
     * @param customerPanel The customer panel that this controller will manage.
     */
    public CustomerPanelController(CustomerPanel customerPanel) {
        this.customerPanel = customerPanel;
    }

    /**
     * This method handles the action of adding a selected product to the shopping cart.
     * It checks if the user has selected a product, entered a valid quantity,
     * and ensures that there is enough stock to add the product to the cart.
     * If all conditions are met, the product is added to the cart.
     */
    public void addToCartAction() {
        int selectedRow = customerPanel.getProductTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(customerPanel, "Por favor selecciona un producto.");
            return;
        }

        String input = customerPanel.getTextField().getText();
        if (input.isEmpty() || !input.matches("\\d+")) {
            JOptionPane.showMessageDialog(customerPanel, "Por favor ingresa una cantidad valida.");
            return;
        }

        int quantity = Integer.parseInt(input);
        Product product = customerPanel.getTableModel().getProductAt(selectedRow);

        if (quantity > product.getStock()) {
            JOptionPane.showMessageDialog(customerPanel, "No hay suficiente stock para la cantidad solicitada.");
        } else {
            CartItem cartItem = getCartItem(quantity, product);
            CartItemDAOImpl cartItemDAO = new CartItemDAOImpl();
            cartItemDAO.create(cartItem);
            customerPanel.getTextField().setText("");
            JOptionPane.showMessageDialog(customerPanel, "Producto agregado al carrito\n.");
        }
    }

    /**
     * This method creates a new CartItem object, which represents a product with a given quantity
     * that a customer adds to their shopping cart. If the customer does not already have a cart,
     * a new cart is created for them.
     *
     * @param quantity The quantity of the product to be added to the cart.
     * @param product The product to be added to the cart.
     * @return A new CartItem object representing the added product and quantity.
     */
    private static CartItem getCartItem(int quantity, Product product) {
        Customer currentCustomer = Session.getCurrentCustomer();
        CartDAOImpl cartDAO = new CartDAOImpl();
        Cart cart = cartDAO.getCartByCustomerId(currentCustomer);

        if (cart == null) {
            // If no cart is found, create a new one
            cart = new Cart(currentCustomer);  // Create a new cart for the customer
            cartDAO.create(cart);  // Save the cart in the database
        }
        CartItem cartItem = new CartItem(quantity, cart, product);
        return cartItem;
    }
}



