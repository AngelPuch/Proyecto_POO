package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.customer.session.Session;

import javax.swing.*;
import java.util.List;

public class TabbedPaneCustomer{
    public static void openTabbedPane() {
        JFrame frame = createMainFrame();
        JTabbedPane tabbedPane = createTabbedPane();

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JFrame createMainFrame(){
        JFrame frame = new JFrame("Venta Online");
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static JTabbedPane createTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane();

        int[] lastSelectedIndex = {-1};

        tabbedPane.addTab("Productos", new CustomerPanel());
        tabbedPane.addTab("Carrito de compras", null);
        tabbedPane.addTab("Compras", null);

        tabbedPane.addChangeListener(e ->{
            if (lastSelectedIndex[0] != -1) {
                tabbedPane.setComponentAt(lastSelectedIndex[0], null);
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

    private static JPanel createPurchasesPanel() {
        // Obtener el ID del usuario actual desde la sesión
        int customerId = Session.getCurrentCustomer().getCustomerId();

        // Obtener las compras del usuario desde la base de datos
        SaleDAOImpl saleDAO = new SaleDAOImpl();
        List<Sale> sales = saleDAO.getSalesByCustomerId(customerId);

        // Crear el panel de compras
        return new PurchasePanel(sales);
    }
}
