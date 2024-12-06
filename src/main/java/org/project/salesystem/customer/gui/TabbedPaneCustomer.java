package org.project.salesystem.customer.gui;

import javax.swing.*;

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
        tabbedPane.addTab("Productos", customerPanel);
        tabbedPane.addTab("Carrito de compras", null);
        return tabbedPane;
    }
}
