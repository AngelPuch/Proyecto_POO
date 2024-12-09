package org.project.salesystem.customer.controller;

import org.project.salesystem.customer.model.SaleDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * This class represents a dialog that shows the details of a sale, including the products,
 * their quantities, and the total for each product.
 * The dialog is modal, meaning the user must interact with it before returning to the main window.
 */
public class SaleDetailDialog extends JDialog {
    private JTable saleDetailTable;
    private JButton btnOk;

    /**
     * Constructor for creating the SaleDetailDialog.
     *
     * @param owner The owner frame of the dialog (usually the main window).
     * @param saleDetails A list of SaleDetail objects that contain the details of the sale.
     */
    public SaleDetailDialog(Frame owner, List<SaleDetail> saleDetails) {
        super(owner, "Detalle de la venta", true); // Modal window
        setLayout(new BorderLayout());

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad", "Total"}, 0);
        for (SaleDetail detail : saleDetails) {
            tableModel.addRow(new Object[]{
                    detail.getProduct().getName(),
                    detail.getQuantity(),
                    detail.getProductTotal()
            });
        }

        // Set up the table
        saleDetailTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(saleDetailTable);
        add(scrollPane, BorderLayout.CENTER);

        // "OK" button
        btnOk = new JButton("OK");
        btnOk.addActionListener(e -> dispose()); // Close the dialog
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnOk);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300); // Set the size of the window
        setLocationRelativeTo(owner); // Center the window relative to the main window
    }
}


