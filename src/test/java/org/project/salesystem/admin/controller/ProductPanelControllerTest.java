package org.project.salesystem.admin.controller;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.gui.ProductPanel;

import static org.junit.jupiter.api.Assertions.*;

class ProductPanelControllerTest {

    /*
    * Volví público el metodo que se encarga de validar campos vacíos (validateNonEmptyField)
    * Volví público el metodo que se encarga de validar si los campos que requieren
    * un dato numerico si contiene un número o no (areValidInputs)
     */

    @Test
    void testValidateNonEmptyFieldSuccess() {
        ProductPanel productPanel = new ProductPanel();
        productPanel.getNameField().setText("Nombre no vacío");
        productPanel.getPriceField().setText("4");
        productPanel.getStockField().setText("8");

        ProductPanelController controller = new ProductPanelController(productPanel);

        assertTrue(controller.validateNonEmptyField());
    }

    @Test
    void testValidateNonEmptyFieldFailure() {
        ProductPanel productPanel = new ProductPanel();
        productPanel.getNameField().setText("Nombre no vacío");
        productPanel.getPriceField().setText("");
        productPanel.getStockField().setText("");

        ProductPanelController controller = new ProductPanelController(productPanel);

        assertFalse(controller.validateNonEmptyField());
    }

    @Test
    void testAreValidInputsSuccess() {
        ProductPanel productPanel = new ProductPanel();
        productPanel.getPriceField().setText("5.8");
        productPanel.getStockField().setText("150");
        ProductPanelController controller = new ProductPanelController(productPanel);
        assertTrue(controller.areValidInputs());
    }

    @Test
    void testAreValidInputsFailure() {
        ProductPanel productPanel = new ProductPanel();
        productPanel.getPriceField().setText("5.8");
        productPanel.getStockField().setText("Hola");
        ProductPanelController controller = new ProductPanelController(productPanel);
        assertFalse(controller.areValidInputs());
    }

    @Test
    void testAreValidInputsNegativeNumber() {
        ProductPanel productPanel = new ProductPanel();
        productPanel.getPriceField().setText("-5.8");
        productPanel.getStockField().setText("15");
        ProductPanelController controller = new ProductPanelController(productPanel);
        assertFalse(controller.areValidInputs());
    }





}