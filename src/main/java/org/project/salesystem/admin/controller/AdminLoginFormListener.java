package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.AdminDAO;
import org.project.salesystem.admin.dao.implementation.AdminDAOImpl;
import org.project.salesystem.admin.gui.AdminLoginForm;
import static org.project.salesystem.admin.gui.TabbedPaneManager.openTabbedPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginFormListener implements ActionListener {
    private final AdminLoginForm adminLoginForm;

    public AdminLoginFormListener(AdminLoginForm adminLoginForm) {
        this.adminLoginForm = adminLoginForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = adminLoginForm.getUsernameText();
        char[] password = adminLoginForm.getPasswordText();
        if (authenticate(username, password)) {
            adminLoginForm.showMessage("Inicio de sesión exitoso");
            JOptionPane.showMessageDialog(adminLoginForm, "Bienvenido " + username);
            adminLoginForm.dispose();
            openTabbedPane();
        } else {
            adminLoginForm.showMessage("Usuario o contraseña incorrectos");
        }
    }

    private boolean authenticate(String username, char[] password) {
        AdminDAO adminDAO = new AdminDAOImpl();
        return adminDAO.findAdminByUsernameAndPassword(username, new String(password)) != null;
    }
}
