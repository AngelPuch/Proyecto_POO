package org.project.salesystem.admin.model;

import java.io.Serializable;

/**
 * Represents a product category in the system
 * This class contains information about the category such as its name and description
 */

public class Category implements Serializable {

    /** The unique identifier for the category */
    private int categoryId;

    /** The name of the category */
    private String name;

    /** A description of the category */
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


    public void setId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

}
