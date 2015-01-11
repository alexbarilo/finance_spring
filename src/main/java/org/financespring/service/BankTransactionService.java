package org.financespring.service;

import org.financespring.model.Account;
import org.financespring.model.BankTransaction;

import java.util.List;

public interface BankTransactionService {

    public void saveEntity(BankTransaction entity);
    public List<BankTransaction> getListOfEntities();
    public float getTotalTransactionsAmount();

}
