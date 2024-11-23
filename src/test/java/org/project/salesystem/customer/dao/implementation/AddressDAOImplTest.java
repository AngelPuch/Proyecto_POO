package org.project.salesystem.customer.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.customer.model.Address;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOImplTest {

    @Test
    void testCreateAddress() {
        AddressDAOImpl addressDAO = new AddressDAOImpl();
        Address address = new Address(1, "91020", "Av. Xalapa", 14155, "Xalapa-Enríquez", "Veracruz", "Mexico");

        addressDAO.create(address);
        Address retrievedAddress = addressDAO.read(1);

        assertNotNull(retrievedAddress);
        assertEquals("91020", retrievedAddress.getPostalCode());
        assertEquals("Av. Xalapa", retrievedAddress.getStreet());
        assertEquals(14155, retrievedAddress.getNumber());
        assertEquals("Xalapa-Enríquez", retrievedAddress.getCity());
        assertEquals("Mexico", retrievedAddress.getCountry());
    }



}