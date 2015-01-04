package org.financespring.service;

import java.util.List;

public interface EntityService<T> {

    public T getEntityById(Class entityClass, int id);
    public void saveEntity(T entity);
    public void deleteEntity(T entity);
    public void updateEntity(T entity);
    public List<T> getListOfEntities();

}
