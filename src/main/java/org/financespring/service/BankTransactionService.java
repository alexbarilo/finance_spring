package org.financespring.service;

import org.financespring.model.Account;
import org.financespring.model.BankTransaction;

import java.util.List;

/**
 * Interface which declares methods for operations with bank transaction domain objects. Service-layer.
 */

public interface BankTransactionService {

    public void saveEntity(BankTransaction entity);
    public List<BankTransaction> getListOfEntities();
    public float getTotalTransactionsAmount();

}
