package org.project.salesystem.database.dao;

import java.util.List;

public interface DAO <T, K>{
    void create(T t);
    T read(K id);
    void update(T t);
    void delete(K id);
    List<T> readAll();
}
