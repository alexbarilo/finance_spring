package org.financespring.service;

import org.financespring.model.Account;
import org.financespring.model.Client;
import org.financespring.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    @Transactional
    public Account getEntityById(Class entityClass, int id) {
        Account account = accountDAO.getEntityById(entityClass, id);
        return account;
    }

    @Override
    @Transactional
    public void saveEntity(Account entity) {
        accountDAO.saveEntity(entity);
    }

    @Override
    @Transactional
    public void deleteEntity(Account entity) {
        accountDAO.deleteEntity(entity);
    }

    @Override
    @Transactional
    public void updateEntity(Account entity) {
        accountDAO.updateEntity(entity);
    }

    @Override
    @Transactional
    public List<Account> getListOfEntities() {
        List<Account> listOfAccounts = accountDAO.getListOfEntities();
        return listOfAccounts;
    }

    @Transactional
    public Account saveAndRetrieveEntity(Account entity) {
        Client client = entity.getClientId();
        Set<Account> setOfAccounts = client.getSetOfAccounts();
        if (setOfAccounts == null) {
            setOfAccounts = new HashSet<Account>();
        }
        setOfAccounts.add(entity);
        accountDAO.saveEntity(entity);
        return entity;
    }

    @Transactional
    public Account updateAndRetrieveEntity(Account entity) {
        Client client = entity.getClientId();
        Set<Account> setOfAccounts = client.getSetOfAccounts();

        setOfAccounts.add(entity);
        accountDAO.updateEntity(entity);
        entity = accountDAO.getEntityById(Account.class, entity.getId());

        return entity;
    }
}
