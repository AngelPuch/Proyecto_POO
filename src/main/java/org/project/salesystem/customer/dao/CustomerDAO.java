package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.database.dao.DAO;

/**
 * Interface defining data access operations for Customer entities.
 * Extends the generic DAO interface for CRUD operations.
 */
public interface CustomerDAO extends DAO<Customer> {

    /**
     * Finds a customer based on their username and password.
     *
     * @param username the username of the customer.
     * @param password the password of the customer.
     * @return the Customer object if found, otherwise null.
     */
    Customer findCustomerByUsernameAndPassword(String username, String password);
}

