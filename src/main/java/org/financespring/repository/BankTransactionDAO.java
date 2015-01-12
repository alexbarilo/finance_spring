package org.financespring.repository;

import org.financespring.model.BankTransaction;

import java.util.List;

/**
 * DAO-interface which declares methods for operations with bank transaction domain objects.
 */
public interface BankTransactionDAO {

    public void saveEntity(BankTransaction entity);
    public List<BankTransaction> getListOfEntities();

}
