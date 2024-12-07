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
        CustomerPanel customerPanel = new CustomerPanel();
        CartPanel cartPanel = new CartPanel();
        JPanel purchasesPanel = createPurchasesPanel();
        tabbedPane.addTab("Productos", customerPanel);
        tabbedPane.addTab("Carrito de compras", cartPanel);
        tabbedPane.addTab("Compras", new JScrollPane(purchasesPanel));
        return tabbedPane;
    }

    private static JPanel createPurchasesPanel() {
        // Obtener el ID del usuario actual desde la sesión
        int customerId = Session.getCurrentCustomer().getCustomerId();

        // Obtener las compras del usuario desde la base de datos
        SaleDAOImpl saleDAO = new SaleDAOImpl();
        List<Sale> sales = saleDAO.getSalesByCustomerId(customerId);

        // Crear el panel de compras
        return new PurchasesPanel(sales);
    }
}
