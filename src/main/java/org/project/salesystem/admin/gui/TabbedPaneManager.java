package org.project.salesystem.admin.gui;

import javax.swing.*;

/**
 * Manages the creation and opening of the main application frame with a tabbed pane.
 * This class handles the layout of the main frame, including adding multiple tabs
 * for different sections of the sales system, such as Products, Suppliers, Categories,
 * Sales, and Information
 */
public class TabbedPaneManager {

    /**
     * Opens the main application window with a tabbed pane containing different panels.
     */
    public static void openTabbedPane() {
        JFrame frame = createMainFrame();
        JTabbedPane tabbedPane = createTabbedPane();

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    /**
     * Creates and configures the main frame for the application
     * @return a {@link JFrame} object configured as the main window for the sales system
     */
    private static JFrame createMainFrame(){
        JFrame frame = new JFrame("Sistema de ventas");
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    /**
     * Creates and configures the tabbed pane that contains various sections of the sales system.
     * The tabbed pane will have tabs for Products, Suppliers, Categories, Sales, and Information
     * @return  a {@link JTabbedPane} object that contains all the tabs for the sales system's sections
     */
    private static JTabbedPane createTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Productos", new ProductPanel());
        tabbedPane.addTab("Proveedores", new SupplierPanel());
        tabbedPane.addTab("Categorías", new CategoryPanel());
        tabbedPane.addTab("Ventas", new SalePanel());
        tabbedPane.addTab("Acerca de", new InformationPanel());

        /*
         * If the products window is selected, a new instance is created to load
         * all the data again in case new providers or categories have been added.
         */
        tabbedPane.addChangeListener(e ->{
            if (tabbedPane.getSelectedIndex() == 0) {
                tabbedPane.setComponentAt(0, new ProductPanel());
            }
        });
        return tabbedPane;
    }
}
