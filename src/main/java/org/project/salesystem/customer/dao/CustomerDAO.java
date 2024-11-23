package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.database.dao.DAO;

public interface CustomerDAO extends DAO<Customer> {
    Customer findCustomerByUsernameAndPassword(String username, String password);
}
