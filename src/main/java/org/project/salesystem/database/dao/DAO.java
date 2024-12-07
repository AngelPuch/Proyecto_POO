package org.project.salesystem.database.dao;

import java.util.List;

/**
 * Generic DAO interface that defines basic CRUD operations
 * @param <T> the type of entity the DAO will manage
 */

public interface DAO <T>{

    /**
     * Creates a new entity in the data source
     * @param t the entity to be created
     */
    void create(T t);

    /**
     * Read an entity from de data source by its identifier
     * @param id the identifier of the entity to read
     * @return the entity with the specified identifier
     */
    T read(Integer id);

    /**
     * Updates an existing entity in the data source
     * @param t the entity with updated data
     */
    void update(T t);

    /**
     * Deletes an entity from the data source by its identifier
     * @param id the identifier of the entity to delete
     */
    void delete(Integer id);

    /**
     * Reads all entities from the data source
     * @return a list of all entities
     */
    List<T> readAll();
}
