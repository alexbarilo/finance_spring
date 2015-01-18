package org.financespring.repository;

import junit.framework.Assert;
import org.financespring.model.Account;
import org.financespring.model.BankTransaction;
import org.financespring.model.Client;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/spring-servlet.xml")
public class BankTransactionDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private BankTransactionDAO bankTransactionDAO;

    private Client currentClient;
    private Account currentAccount;
    private BankTransaction currentBankTransaction;

    @Before
    public void createTestedObjects() {
        createTestedClient();
        createTestedAccount();
        createTestedBankTransaction();
    }

    @Test
    @Transactional
    public void testSaveEntity() {
        BankTransaction bankTransaction = getTestedBankTransaction();
        Assert.assertNotNull(bankTransaction);
        System.out.println("testSaveEntity() fulfilled successfully");
    }

    @Test
    @Transactional
    public void testGetListOfEntities() {
        List<BankTransaction> listOfTransactions = bankTransactionDAO.getListOfEntities();
        Assert.assertNotNull(listOfTransactions.get(0));
        Assert.assertTrue(listOfTransactions.get(0).getId() > 0);
        System.out.println("testGetListOfEntities() fulfilled successfully");
    }
    /*
   Initialisation of the Client object used in test methods.
    */
    public void createTestedClient() {
        currentClient = new Client();
        currentClient.setFirstName("Bruno");
        currentClient.setLastName("Prosato");
        currentClient.setAddress("Via La Machina, 17");
        currentClient.setCity("Treviso");
        currentClient.setPostalCode("23400");
        currentClient.setSetOfAccounts(new HashSet<Account>());
    }
    /*
    Initialisation of the Account object used in test methods.
     */
    public void createTestedAccount() {
        currentAccount = new Account();
        currentAccount.setAccountNumber("EUR25300200456");
        currentAccount.setAmount(45000.0f);
        currentAccount.setCurrency("EUR");
        currentAccount.setDate("2014-10-15");
        currentAccount.setSetOfTransactions(new HashSet<BankTransaction>());
    }
    /*
    Initialisation of the BankTransaction object used in test methods.
     */
    public void createTestedBankTransaction() {
        currentBankTransaction = new BankTransaction();
        currentBankTransaction.setBenAccountNum("EURTRANSACTION");
        currentBankTransaction.setBenAmount(1000.0f);
        currentBankTransaction.setBenBankName("BANK");
        currentBankTransaction.setTransactionDate("2014-12-12");
    }

    /*
    Common functionality for the test methods. As after each test method the current transaction is rolled back
    the Client, Account and BankTransaction objects must be saved before any test method is fulfilled. At current version
    of application (1.0) these functionality is used in testSaveEntity() only.
     */
    public BankTransaction getTestedBankTransaction() {
        clientDAO.saveEntity(currentClient);
        currentClient.getSetOfAccounts().add(currentAccount);
        currentAccount.setClientId(currentClient);
        accountDAO.saveEntity(currentAccount);
        currentAccount.getSetOfTransactions().add(currentBankTransaction);
        currentBankTransaction.setAccountId(currentAccount);
        bankTransactionDAO.saveEntity(currentBankTransaction);

        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from BankTransaction bankTransaction where bankTransaction.benAccountNum='EURTRANSACTION'");
        BankTransaction bankTransaction = (BankTransaction) result.uniqueResult();
        return bankTransaction;
    }
}
