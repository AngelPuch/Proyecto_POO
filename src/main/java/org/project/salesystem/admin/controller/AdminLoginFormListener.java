package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.AdminDAO;
import org.project.salesystem.admin.dao.implementation.AdminDAOImpl;
import org.project.salesystem.admin.gui.AdminLoginForm;
import static org.project.salesystem.admin.gui.TabbedPaneManager.openTabbedPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the actions performed on the {@link AdminLoginForm}
 */

public class AdminLoginFormListener implements ActionListener {
    private final AdminLoginForm adminLoginForm;

    public AdminLoginFormListener(AdminLoginForm adminLoginForm) {
        this.adminLoginForm = adminLoginForm;
    }

    /**
     * Invoked when an action occurs, such as clicking the login button
     * @param e the event triggered by user interaction.
     */
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

    /**
     * Authenticates the admin by checking the provided credentials
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return {@code true} if authentication is successful, {@code false} otherwise
     */
    private boolean authenticate(String username, char[] password) {
        AdminDAO adminDAO = new AdminDAOImpl();
        return adminDAO.findAdminByUsernameAndPassword(username, new String(password)) != null;
    }
}
