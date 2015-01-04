package org.financespring.service;

import org.financespring.model.Account;

public interface AccountService extends EntityService<Account> {

    public Account saveAndRetrieveEntity(Account entity);
    public Account updateAndRetrieveEntity(Account entity);

}
