package org.project.salesystem.admin.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.dao.AdminDAO;
import org.project.salesystem.admin.model.Admin;
import static org.junit.jupiter.api.Assertions.*;

class AdminDAOImplTest {
    @Test
    void testFindAdminByUsernameAndPasswordSucces(){
        AdminDAO adminDAO = new AdminDAOImpl();
        Admin admin = adminDAO.findAdminByUsernameAndPassword("administrador", "12345");

        assertNotNull(admin);
        assertEquals("administrador", admin.getUsername());
        assertEquals("12345", admin.getPassword());
    }

    @Test
    void testFindAdminByUsernameAndPasswordFailure(){
        AdminDAO adminDAO = new AdminDAOImpl();
        Admin admin = adminDAO.findAdminByUsernameAndPassword("admin", "12345");

        assertNull(admin);
    }

}