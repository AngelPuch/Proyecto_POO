package org.project.salesystem.customer.gui;

import javax.swing.*;
import java.awt.*;

public class MainCustomerWindow extends JFrame{

    public MainCustomerWindow(){
        setTitle("Sistema de Clientes");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents(){
        JPanel panel = new JPanel(new GridLayout(2,1,20,20));
        JButton loginButton = new JButton("Iniciar Sesión");
        JButton registerButton = new JButton("Registrarse");

        loginButton.addActionListener(e -> openLoginForm());
        registerButton.addActionListener(e -> openRegisterForm());

        panel.add(loginButton);
        panel.add(registerButton);
        add(panel);
    }

    private void openLoginForm(){
        new CustomerLoginForm().setVisible(true);
        this.dispose();
    }

    private void openRegisterForm(){
        new CustomerRegisterForm().setVisible(true);
        this.dispose();
    }

}
