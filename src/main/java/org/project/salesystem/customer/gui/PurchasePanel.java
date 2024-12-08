package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.model.Sale;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class represents a panel that displays a list of sales and their details.
 */
public class PurchasePanel extends JPanel {

    /**
     * Constructor that sets up the panel displaying the sales.
     *
     * @param sales List of Sale objects to be displayed in the panel.
     */
    public PurchasePanel(List<Sale> sales) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        // Iterate through each sale and create a panel to display its details
        for (Sale sale : sales) {
            JPanel salePanel = new JPanel(new BorderLayout());
            salePanel.setBorder(BorderFactory.createTitledBorder("Sale ID: " + sale.getSaleId() + " - Date: " + sale.getDateOfSale()));

            // Create a table to display sale details
            JTable saleDetailsTable = new JTable(new SaleDetailTableModel(sale.getDetails())); // Implements a custom TableModel
            JScrollPane scrollPane = new JScrollPane(saleDetailsTable);
            salePanel.add(scrollPane, BorderLayout.CENTER);

            // Add the sale panel to the main panel
            add(salePanel);
        }
    }
}

