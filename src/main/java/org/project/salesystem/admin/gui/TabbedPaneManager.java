package org.project.salesystem.admin.gui;

import javax.swing.*;

public class TabbedPaneManager {

    public static void openTabbedPane() {
        JFrame frame = createMainFrame();
        JTabbedPane tabbedPane = createTabbedPane();

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JFrame createMainFrame(){
        JFrame frame = new JFrame("Sistema de ventas");
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static JTabbedPane createTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Productos", new ProductPanel());
        tabbedPane.addTab("Proveedores", new SupplierPanel());
        tabbedPane.addTab("Categorías", new CategoryPanel());
        tabbedPane.addTab("Ventas", new SalePanel());
        tabbedPane.addTab("Acerca de", new InformationPanel());
        return tabbedPane;
    }
}
