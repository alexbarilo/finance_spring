package org.financespring.service;

import java.util.List;

/**
 * Generic interface which represents Service-layer. Facade for DAO-layer. @Transactional.
 * @param <T>
 */
public interface EntityService<T> {

    public T getEntityById(Class entityClass, int id);
    public void saveEntity(T entity);
    public void deleteEntity(T entity);
    public void updateEntity(T entity);
    public List<T> getListOfEntities();

}
