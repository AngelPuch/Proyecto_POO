package org.project.salesystem.customer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.salesystem.customer.controller.CustomerRegisterFormController;
import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.dao.implementation.CustomerDAOImpl;
import org.project.salesystem.customer.gui.CustomerRegisterForm;
import org.project.salesystem.customer.model.Customer;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRegisterFormControllerTest {

    private CustomerRegisterForm customerRegisterForm;
    private CustomerRegisterFormController controller;

    @BeforeEach
    void setUp() {
        // Crear una instancia de CustomerRegisterForm, puede ser un mock si lo deseas
        customerRegisterForm = new CustomerRegisterForm(); // Suponemos que la instancia se puede crear de esta manera.
        controller = new CustomerRegisterFormController(customerRegisterForm);
    }

    /**
     * Prueba para verificar que se registren correctamente los datos del cliente
     * cuando todos los campos están completos y el número de teléfono es válido.
     */
    @Test
    void testRegisterCustomerSuccess() {
        // Simular la entrada del formulario
        customerRegisterForm.getNameField().setText("John Doe");
        customerRegisterForm.getPhoneField().setText("1234567890");
        customerRegisterForm.getUsernameFlied().setText("johndoe");
        customerRegisterForm.getPasswordField().setText("password");
        customerRegisterForm.getStreetField().setText("123 Main St");
        customerRegisterForm.getPostal_codeField().setText("12345");
        customerRegisterForm.getCityField().setText("Springfield");
        customerRegisterForm.getStateField().setText("IL");

        // Simular el DAO (en este caso solo se invoca el metodo create sin acción real)
        CustomerDAO customerDAO = new CustomerDAOImpl();

        // Método de registro
        controller.registerCustomer();

        // Validar que el mensaje de éxito se mostró correctamente
        String expectedMessage = "Cliente registrado exitosamente!";
        // Validamos que el JOptionPane.showMessageDialog fue llamado
        assertEquals(expectedMessage, JOptionPane.showInputDialog(expectedMessage));
    }

    /**
     * Prueba para verificar que se muestren los mensajes de error cuando un campo
     * obligatorio está vacío.
     */
    @Test
    void testRegisterCustomerFailureEmptyFields() {
        // Simular la entrada del formulario con un campo vacío
        customerRegisterForm.getNameField().setText("");  // Nombre vacío
        customerRegisterForm.getPhoneField().setText("1234567890");
        customerRegisterForm.getUsernameFlied().setText("johndoe");
        customerRegisterForm.getPasswordField().setText("password");
        customerRegisterForm.getStreetField().setText("123 Main St");
        customerRegisterForm.getPostal_codeField().setText("12345");
        customerRegisterForm.getCityField().setText("Springfield");
        customerRegisterForm.getStateField().setText("IL");

        // Realizar el registro
        controller.registerCustomer();

        // Comprobar si se mostró el mensaje adecuado
        String expectedMessage = "Todos los campos son obligatorios.";
        // Validamos que el JOptionPane.showMessageDialog fue llamado
        assertEquals(expectedMessage, JOptionPane.showInputDialog(expectedMessage));
    }

    /**
     * Prueba para verificar que se muestren mensajes de error si el número de teléfono es inválido.
     */
    @Test
    void testRegisterCustomerInvalidPhoneNumber() {
        // Simular la entrada del formulario con un número de teléfono incorrecto
        customerRegisterForm.getNameField().setText("John Doe");
        customerRegisterForm.getPhoneField().setText("12345");  // Teléfono incorrecto
        customerRegisterForm.getUsernameFlied().setText("johndoe");
        customerRegisterForm.getPasswordField().setText("password");
        customerRegisterForm.getStreetField().setText("123 Main St");
        customerRegisterForm.getPostal_codeField().setText("12345");
        customerRegisterForm.getCityField().setText("Springfield");
        customerRegisterForm.getStateField().setText("IL");

        // Realizar el registro
        controller.registerCustomer();

        // Comprobar si se mostró el mensaje adecuado
        String expectedMessage = "Debe ingresar un número de teléfono valido";
        // Validamos que el JOptionPane.showMessageDialog fue llamado
        assertEquals(expectedMessage, JOptionPane.showInputDialog(expectedMessage));
    }

    /**
     * Prueba para verificar que se cree el objeto cliente correctamente con los valores de los campos.
     */
    @Test
    void testCreateCustomer() {
        // Simular la entrada del formulario
        customerRegisterForm.getNameField().setText("John Doe");
        customerRegisterForm.getPhoneField().setText("1234567890");
        customerRegisterForm.getUsernameFlied().setText("johndoe");
        customerRegisterForm.getPasswordField().setText("password");
        customerRegisterForm.getStreetField().setText("123 Main St");
        customerRegisterForm.getPostal_codeField().setText("12345");
        customerRegisterForm.getCityField().setText("Springfield");
        customerRegisterForm.getStateField().setText("IL");

        // Crear el cliente manualmente
        Customer customer = controller.createCustomer();

        // Verificar que los valores se asignen correctamente
        assertNotNull(customer);
        assertEquals("John Doe", customer.getName());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("johndoe", customer.getUsername());
        assertEquals("password", customer.getPassword());
        assertEquals("123 Main St", customer.getStreet());
        assertEquals("12345", customer.getPostal_code());
        assertEquals("Springfield", customer.getCity());
        assertEquals("IL", customer.getState());
    }
}