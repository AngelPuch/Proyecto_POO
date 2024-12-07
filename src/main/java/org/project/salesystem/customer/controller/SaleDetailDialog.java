package org.project.salesystem.customer.controller;
import org.project.salesystem.customer.model.SaleDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SaleDetailDialog extends JDialog {
    private JTable saleDetailTable;
    private JButton btnOk;

    public SaleDetailDialog(Frame owner, List<SaleDetail> saleDetails) {
        super(owner, "Detalles de la Venta", true); // Ventana modal
        setLayout(new BorderLayout());

        // Crear el modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad", "Total"}, 0);
        for (SaleDetail detail : saleDetails) {
            tableModel.addRow(new Object[]{
                    detail.getProduct().getName(),
                    detail.getQuantity(),
                    detail.getProductTotal()
            });
        }

        // Configurar la tabla
        saleDetailTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(saleDetailTable);
        add(scrollPane, BorderLayout.CENTER);

        // Botón "OK"
        btnOk = new JButton("OK");
        btnOk.addActionListener(e -> dispose()); // Cerrar la ventana
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnOk);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300); // Tamaño de la ventana
        setLocationRelativeTo(owner); // Centrar respecto a la ventana principal
    }
}

