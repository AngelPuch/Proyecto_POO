package org.project.salesystem.admin.model;

import java.io.Serializable;

/**
 * Represents an administrator user in the system.
 * This class contains basic information about the admin such as their username and password
 * It provides getter and setter methods to access and modify these details
 */

public class Admin implements Serializable {

    /** The unique identifier for the admin */
    private int adminId;

    /** The username of the admin */
    private String username;

    /** The password of the admin */
    private String password;

    public Admin() {
    }

    public Admin(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public int getId() { return adminId; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }


    public void setId(int adminId) { this.adminId = adminId; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
