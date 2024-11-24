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
        tabbedPane.addTab("Productos", new InventoryPanel());
        tabbedPane.addTab("Proveedores", null);
        tabbedPane.addTab("Ventas", null);
        tabbedPane.addTab("Acerca de", null);
        return tabbedPane;
    }
}
