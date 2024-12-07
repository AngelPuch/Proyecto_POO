package org.project.salesystem.admin.dao;

import org.project.salesystem.admin.model.Admin;
import org.project.salesystem.database.dao.DAO;

/**
 * Provides the data access methods for the {@link Admin} entity
 * This interface extends the generic {@link DAO} interface and defines a method
 * for validating an admin's login credentials based on their usernames and password
 */

public interface AdminDAO extends DAO<Admin> {

    /**
     * Verifies the login credentials of an admin
     * This method checks if there is an admin in the system with the given username and password
     * @param username the username of the admin
     * @param password the password of the admin
     * @return an {@link Admin} object if the credentials are correct, otherwise null
     */
    Admin findAdminByUsernameAndPassword(String username, String password);
}
