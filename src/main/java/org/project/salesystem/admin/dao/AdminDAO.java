package org.project.salesystem.admin.dao;

import org.project.salesystem.admin.model.Admin;
import org.project.salesystem.database.dao.DAO;

public interface AdminDAO extends DAO<Admin> {
    Admin findAdminByUsernameAndPassword(String username, String password);
}
