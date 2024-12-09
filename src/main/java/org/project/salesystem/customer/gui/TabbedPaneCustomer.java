package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.customer.session.Session;

import javax.swing.*;
import java.util.List;

/**
 * This class is responsible for managing the main customer window with a tabbed interface.
 * It provides tabs for products, shopping cart, and customer purchases.
 */
public class TabbedPaneCustomer {

    /**
     * Opens the main customer window with a tabbed interface.
     */
    public static void openTabbedPane() {
        JFrame frame = createMainFrame();
        JTabbedPane tabbedPane = createTabbedPane();

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    /**
     * Creates the main JFrame that contains the tabbed pane.
     *
     * @return The JFrame instance.
     */
    private static JFrame createMainFrame() {
        JFrame frame = new JFrame("Venta Online");
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    /**
     * Creates and configures the tabbed pane, which contains three tabs:
     * - Products
     * - Shopping Cart
     * - Purchases
     *
     * @return The JTabbedPane instance.
     */
    private static JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // This array holds the last selected tab index to prevent unnecessary replacements.
        int[] lastSelectedIndex = {-1};

        // Adding tabs with corresponding panels
        tabbedPane.addTab("Productos", new CustomerPanel());
        tabbedPane.addTab("Carrito de compras", null);
        tabbedPane.addTab("Compras", null);

        // Adding a ChangeListener to dynamically populate the panels when tabs are selected
        tabbedPane.addChangeListener(e -> {
            if (lastSelectedIndex[0] != -1) {
                tabbedPane.setComponentAt(lastSelectedIndex[0], null);  // Remove the previous component
            }

            int selectedIndex = tabbedPane.getSelectedIndex();

            if (tabbedPane.getComponentAt(selectedIndex) == null) {
                switch (selectedIndex) {
                    case 0 -> tabbedPane.setComponentAt(0, new CustomerPanel());
                    case 1 -> tabbedPane.setComponentAt(1, new CartPanel());
                    case 2 -> tabbedPane.setComponentAt(2, new JScrollPane(createPurchasesPanel()));
                }
            }
            lastSelectedIndex[0] = selectedIndex;
        });

        return tabbedPane;
    }

    /**
     * Creates the Purchases panel by retrieving the customer's previous purchase data.
     *
     * @return The JPanel instance containing the list of purchases.
     */
    private static JPanel createPurchasesPanel() {
        int customerId = Session.getCurrentCustomer().getCustomerId();
        return new PurchasePanel(customerId);
    }
}
