package org.project.salesystem.customer.controller;

import org.junit.jupiter.api.Test;
import org.project.salesystem.customer.gui.CustomerRegisterForm;
import org.project.salesystem.customer.model.Customer;

import static org.junit.jupiter.api.Assertions.*;
class CustomerRegisterFormControllerTest {

    @Test
    void testValidateNonEmptyFieldSuccess() {
        CustomerRegisterForm customerRegisterForm = new CustomerRegisterForm();
        customerRegisterForm.getNameField().setText("A");
        customerRegisterForm.getPhoneField().setText("1234567890");
        customerRegisterForm.getUsernameFlied().setText("sd");
        customerRegisterForm.getPasswordField().setText("1235");
        customerRegisterForm.getStreetField().setText("calle");
        customerRegisterForm.getPostal_codeField().setText("98765");
        customerRegisterForm.getCityField().setText("xalapa");
        customerRegisterForm.getStateField().setText("veracruz");

        CustomerRegisterFormController controller  = new CustomerRegisterFormController(customerRegisterForm);
        assertTrue(controller.validateNonEmptyField());
    }

    @Test
    void testValidateNonEmptyFieldFailure() {
        CustomerRegisterForm customerRegisterForm = new CustomerRegisterForm();
        customerRegisterForm.getNameField().setText("");
        customerRegisterForm.getPhoneField().setText("1234567890");
        customerRegisterForm.getUsernameFlied().setText("sd");
        customerRegisterForm.getPasswordField().setText("1235");
        customerRegisterForm.getStreetField().setText("calle");
        customerRegisterForm.getPostal_codeField().setText("98765");
        customerRegisterForm.getCityField().setText("xalapa");
        customerRegisterForm.getStateField().setText("veracruz");

        CustomerRegisterFormController controller  = new CustomerRegisterFormController(customerRegisterForm);
        assertFalse(controller.validateNonEmptyField());
    }

    @Test
    void testCreateCustomerSuccess() {
        CustomerRegisterForm customerRegisterForm = new CustomerRegisterForm();
        customerRegisterForm.getNameField().setText("A");
        customerRegisterForm.getPhoneField().setText("1234567890");
        customerRegisterForm.getUsernameFlied().setText("sd");
        customerRegisterForm.getPasswordField().setText("1235");
        customerRegisterForm.getStreetField().setText("calle");
        customerRegisterForm.getPostal_codeField().setText("98765");
        customerRegisterForm.getCityField().setText("xalapa");
        customerRegisterForm.getStateField().setText("veracruz");

        CustomerRegisterFormController controller  = new CustomerRegisterFormController(customerRegisterForm);

        Customer customer = controller.createCustomer();

        assertNotNull(customer);
        assertEquals("A", customer.getName());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("veracruz", customer.getState());
    }
  
}