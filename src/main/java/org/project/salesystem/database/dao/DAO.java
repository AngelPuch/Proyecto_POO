package org.project.salesystem.database.dao;

import java.util.List;

public interface DAO <T>{
    void create(T t);
    T read(Integer id);
    void update(T t);
    void delete(Integer id);
    List<T> readAll();
}
