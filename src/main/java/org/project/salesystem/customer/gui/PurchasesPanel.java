package org.project.salesystem.customer.gui;
import org.project.salesystem.customer.model.Sale;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PurchasesPanel extends JPanel {

    public PurchasesPanel(List<Sale> sales) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        for (Sale sale : sales) {
            JPanel salePanel = new JPanel(new BorderLayout());
            salePanel.setBorder(BorderFactory.createTitledBorder("Compra ID: " + sale.getSaleId() + " - Fecha: " + sale.getDateOfSale()));

            JTable saleDetailsTable = new JTable(new SaleDetailTableModel(sale.getDetails())); // Implementa un TableModel
            JScrollPane scrollPane = new JScrollPane(saleDetailsTable);
            salePanel.add(scrollPane, BorderLayout.CENTER);

            add(salePanel);
        }
    }
}

