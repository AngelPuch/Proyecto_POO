package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.SaleDetailReportViewController;
import org.project.salesystem.admin.controller.SaleDetailReportViewTable;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class SaleDetailReportView extends JFrame {
    private SaleDetailReportViewTable saleDetailReportViewTable;
    private JTable table;
    private int idSale;
    private JLabel nameField;
    private JLabel phoneNumberField;
    private JLabel addressField;
    private JLabel dateField;
    private JLabel totalField;
    SaleDetailReportViewController controller;


    public SaleDetailReportView(int idSale) {
        this.idSale = idSale;
        setTitle("Detalle de la Venta");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initComponents();
        this.controller = new SaleDetailReportViewController(this);
        setVisible(true);
    }

    public int getIdSale() {
        return idSale;
    }

    public void setNameLabel(String nameField) {
        this.nameField.setText(nameField);
    }

    public void setPhoneNumberLabel(String phoneNumberField) {
        this.phoneNumberField.setText(phoneNumberField);
    }

    public void setAddressLabel(String addressField) {
        this.addressField.setText(addressField);
    }

    public void setDateLabel(Date dateField) {
        this.dateField.setText(String.valueOf(dateField));
    }

    public void setTotalLabel(String totalField) {
        this.totalField.setText(totalField);
    }

    private void initComponents() {
        saleDetailReportViewTable = new SaleDetailReportViewTable(idSale);
        table = new JTable(saleDetailReportViewTable);

        nameField = new JLabel();
        phoneNumberField = new JLabel();
        addressField = new JLabel();
        dateField = new JLabel();
        totalField = new JLabel();

        JPanel headerPanel = new JPanel(new GridLayout(2, 2));
        headerPanel.setBorder(BorderFactory.createTitledBorder("Información de la Venta"));
        headerPanel.add(new JLabel("Cliente: "));
        headerPanel.add(nameField);
        headerPanel.add(new JLabel("Teléfono: "));
        headerPanel.add(phoneNumberField);
        headerPanel.add(new JLabel("Dirección: "));
        headerPanel.add(addressField);
        headerPanel.add(new JLabel("Fecha de Venta: "));
        headerPanel.add(dateField);

        JPanel totalPanel = new JPanel();
        totalPanel.setBackground(new Color(173, 216, 230));
        totalPanel.add(new JLabel("Total de Venta: "));
        totalPanel.add(totalField);



        add(new JScrollPane(table), BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);
        add(totalPanel, BorderLayout.SOUTH);


    }
}
