package org.financespring.service;

import org.financespring.model.BankTransaction;
import org.financespring.repository.BankTransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

    @Autowired
    private BankTransactionDAO bankTransactionDAO;

    @Override
    @Transactional
    public void saveEntity(BankTransaction entity) {
        bankTransactionDAO.saveEntity(entity);
    }

    @Override
    @Transactional
    public List<BankTransaction> getListOfEntities() {
        List<BankTransaction> listOfTransactions = bankTransactionDAO.getListOfEntities();
        return listOfTransactions;
    }
}
