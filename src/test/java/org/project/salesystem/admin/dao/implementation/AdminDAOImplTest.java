package org.project.salesystem.admin.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.dao.AdminDAO;
import org.project.salesystem.admin.model.Admin;
import static org.junit.jupiter.api.Assertions.*;

class AdminDAOImplTest {
    @Test
    void testCreateAdmin(){
        AdminDAO adminDAO = new AdminDAOImpl();
        Admin admin = new Admin(3, "admin", "12345");

        adminDAO.create(admin);
        Admin retrievedAdmin = adminDAO.read(3);

        assertNotNull(retrievedAdmin);
        assertEquals("admin", retrievedAdmin.getUsername());
        assertEquals("12345", retrievedAdmin.getPassword());
    }

    @Test
    void testFindAdminByUsernameAndPassword(){
        AdminDAO adminDAO = new AdminDAOImpl();
        Admin admin = adminDAO.findAdminByUsernameAndPassword("admin", "12345");

        assertNotNull(admin);
        assertEquals(3, admin.getId());
        assertEquals("admin", admin.getUsername());
        assertEquals("12345", admin.getPassword());
    }

}