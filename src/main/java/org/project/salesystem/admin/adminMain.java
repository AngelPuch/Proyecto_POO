package org.project.salesystem.admin;

import org.project.salesystem.admin.gui.AdminLoginForm;

import javax.swing.*;

public class adminMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            AdminLoginForm adminLoginForm = new AdminLoginForm();
            adminLoginForm.setVisible(true);
        });
    }
}
