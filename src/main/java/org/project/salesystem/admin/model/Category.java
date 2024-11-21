package org.project.salesystem.admin.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int categoryId;
    private String name;
    private String description;

    public Category() {
    }

    public Category(int categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public int getId() { return categoryId; }

    public String getName() { return name; }

    public String getDescription() { return description; }


    public void setId(int categoryId) { this.categoryId = categoryId; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
