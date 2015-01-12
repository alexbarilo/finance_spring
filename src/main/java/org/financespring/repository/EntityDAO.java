package org.financespring.repository;

import java.util.List;

/**
 * Generic DAO-interface which declares methods for CRUD-operations with client and account domain objects.
 * @param <T>
 */
public interface EntityDAO<T> {

    public T getEntityById(Class entityClass, int id);
    public void saveEntity(T entity);
    public void updateEntity(T entity);
    public void deleteEntity(T entity);
    public List<T> getListOfEntities();

}
