package org.project.salesystem.admin.gui;

import javax.swing.*;
import java.awt.*;


public class InformationPanel extends JPanel {

    public InformationPanel() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Acerca de Sistema de Ventas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextArea infoArea = new JTextArea();
        infoArea.setBackground(new Color(240, 240, 240));
        infoArea.setEditable(false);
        infoArea.setText("""     
        Sistema de Ventas de Videojuegos
        
        Este sistema simula un entorno de ventas para una tienda de videojuegos.
        
        Roles:
        - Administrador: El admin tiene control total sobre el inventario, incluyendo la \
        gestión de proveedores y categorías de videojuegos. También puede ver y generar reportes \
        de ventas.
        
        - Cliente: Los clientes pueden ver los productos disponibles, agregarlos a su carrito \
        de compras y realizar compras de videojuegos.
        
        Funcionalidades:
        - Gestión de productos, proveedores y categorías de videojuegos.
        - Registro, eliminación y edición de proveedores y categorías.
        - Generación de reportes de ventas para el administrador.
        - Visualización y compra de productos para los clientes.
        
        Este proyecto forma parte de la experiencia educativa de Diseño y Programación Orientada \
        a Objetos
        
        Desarrollado por:
        Angel Jonathan Puch Hernández
        Jose Luis Silva Gómez
        Rodrigo Luna Vazquez""");


        add(titleLabel, BorderLayout.NORTH);
        add(infoArea, BorderLayout.CENTER);
    }

}
