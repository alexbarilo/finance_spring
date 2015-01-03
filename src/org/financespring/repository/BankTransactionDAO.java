package org.financespring.repository;

import org.financespring.model.BankTransaction;

import java.util.List;

public interface BankTransactionDAO {

    public void saveEntity(BankTransaction entity);
    public List<BankTransaction> getListOfEntities();

}
