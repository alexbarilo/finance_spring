package org.financespring.repository;

import org.financespring.model.BankTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of BankTransactionsDAO-interface.
 */

@Repository
public class BankTransactionDAOImpl implements BankTransactionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveEntity(BankTransaction entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    @Override
    public List<BankTransaction> getListOfEntities() {
        Session session = sessionFactory.getCurrentSession();
        List<BankTransaction> listOfTransactions = session.createQuery("from BankTransaction ").list();
        return listOfTransactions;
    }
}
