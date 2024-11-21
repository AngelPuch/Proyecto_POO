package org.project.salesystem.admin.model;

import java.io.Serializable;

public class Admin implements Serializable {
    private int adminId;
    private String username;
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
