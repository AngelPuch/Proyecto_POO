package org.project.salesystem.customer.gui;


import org.project.salesystem.customer.controller.PurchaseTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a panel that displays a list of sales and their details.
 */
public class PurchasePanel extends JPanel {
    PurchaseTableModel purchaseTableModel;

    public PurchasePanel(int customerID) {
        this.purchaseTableModel = new PurchaseTableModel(customerID);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);


        JPanel salePanel = new JPanel(new BorderLayout());
        JTable saleDetailsTable = new JTable(purchaseTableModel);
        salePanel.add(new JScrollPane(saleDetailsTable), BorderLayout.CENTER);

        add(salePanel);

    }
}

