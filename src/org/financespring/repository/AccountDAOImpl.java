package org.financespring.repository;

import org.financespring.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account getEntityById(Class entityClass, int id) {
        Session session = sessionFactory.getCurrentSession();
        Account account = (Account) session.get(entityClass, id);
        return account;
    }

    @Override
    public void saveEntity(Account entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    @Override
    public void updateEntity(Account entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public void deleteEntity(Account entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    @Override
    public List<Account> getListOfEntities() {
        Session session = sessionFactory.getCurrentSession();
        List<Account> listOfAccounts = session.createQuery("from Account").list();
        return listOfAccounts;
    }

}
