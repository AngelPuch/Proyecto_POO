package org.project.salesystem.admin.controller;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.gui.SupplierPanel;

import static org.junit.jupiter.api.Assertions.*;

class SupplierPanelControllerTest {
    /**
     * El metodo (isValidPhoneNumber) lo volví público
     * El metodo (validateNonEmptyField) lo volví público
     */

    @Test
    void testIsValidPhoneNumberSuccess() {
        SupplierPanel supplierPanel = new SupplierPanel();
        supplierPanel.getNumberField().setText("1234567891");

        SupplierPanelController controller = new SupplierPanelController(supplierPanel);
        assertTrue(controller.isValidPhoneNumber());
    }

    @Test
    void testIsValidPhoneNumberFailure() {
        SupplierPanel supplierPanel = new SupplierPanel();
        supplierPanel.getNumberField().setText("123567891");

        SupplierPanelController controller = new SupplierPanelController(supplierPanel);
        assertFalse(controller.isValidPhoneNumber());
    }

    @Test
    void testIsValidPhoneNumberFailureNoNumber() {
        SupplierPanel supplierPanel = new SupplierPanel();
        supplierPanel.getNumberField().setText("abcdefghij");

        SupplierPanelController controller = new SupplierPanelController(supplierPanel);
        assertFalse(controller.isValidPhoneNumber());
    }

    @Test
    void testValidateNonEmptyFieldSuccess() {
        SupplierPanel supplierPanel = new SupplierPanel();
        supplierPanel.getNameField().setText("Nombre prueba");
        supplierPanel.getNumberField().setText("1234567891");

        SupplierPanelController controller = new SupplierPanelController(supplierPanel);
        assertTrue(controller.validateNonEmptyField());
    }

    @Test
    void testValidateNonEmptyFieldFailure() {
        SupplierPanel supplierPanel = new SupplierPanel();
        supplierPanel.getNameField().setText("   ");
        supplierPanel.getNumberField().setText("1234567891");

        SupplierPanelController controller = new SupplierPanelController(supplierPanel);
        assertFalse(controller.validateNonEmptyField());
    }

}