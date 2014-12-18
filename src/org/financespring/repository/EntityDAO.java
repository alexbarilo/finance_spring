package org.financespring.repository;

import java.util.List;

public interface EntityDAO<T> {

    public T getEntityById(T entity, int id);
    public void saveEntity(T entity);
    public void updateEntity(T entity);
    public void deleteEntity(T entity);
    public List<T> getListOfEntities();

}
