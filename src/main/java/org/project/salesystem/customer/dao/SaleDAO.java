package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.database.dao.DAO;

import java.util.List;

public interface SaleDAO extends DAO<Sale> {
    List<Sale> getSalesByCustomerId(int id);
    //Sale getSaleDetail(int id);
}
