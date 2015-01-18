package org.financespring.repository;

import org.financespring.model.Account;
import org.financespring.model.BankTransaction;
import org.financespring.model.Client;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/spring-servlet.xml")
public class AccountDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private AccountDAO accountDAO;

    private static Client currentClient;
    private static Account currentAccount;

    /*
    Initializes Client and Account objects. The method is fulfilled before each test to reinitilize objects - id
    of the objects must be nulled before save/update operations.
     */
    @Before
    public void createTestedObjects() {
        createTestedAccount();
        createTestedClient();
    }

    @Test
    @Transactional
    public void testGetEntityById() {
        Account account = getTestedAccount();
        account = accountDAO.getEntityById(Account.class, account.getId());
        Assert.assertNotNull(account);
        Assert.assertEquals("EUR25300200456", account.getAccountNumber());
        Assert.assertEquals(account.getAmount(), 47000.0f, 2000.0f);
        Assert.assertEquals("EUR", account.getCurrency());
        Assert.assertEquals("2014-10-15", account.getDate());
        System.out.println("testGetEntityById() fulfilled successfully");
    }

    @Test
    @Transactional
    public void testSaveEntity() {
        Account account = this.getTestedAccount();
        Assert.assertNotNull(account);
        System.out.println("testSaveEntity() fulfilled successfully");
    }

    @Test
    @Transactional
    public void testUpdateEntity() {
        Account account = getTestedAccount();
        account.setAccountNumber("EUR0000000");
        accountDAO.updateEntity(account);

        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Account account where account.id=:name");
        result.setInteger("name", account.getId());
        account = (Account) result.uniqueResult();

        Assert.assertEquals("EUR0000000", account.getAccountNumber());
        System.out.println("testUpdateEntity() fulfilled successfully");
    }

    @Test
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void testDeleteEntity() {
        Account account = getTestedAccount();
        accountDAO.deleteEntity(account);
        account = accountDAO.getEntityById(Account.class, account.getId());
        Assert.assertNull(account);
        System.out.println("testDeleteEntity() fulfilled successfully");
    }

    @Test
    @Transactional
    public void testGetListOfEntities() {
        Account account = getTestedAccount();
        List<Account> listOfAccounts = accountDAO.getListOfEntities();
        Assert.assertNotNull(listOfAccounts.get(0));
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
    }

    /*
    Common functionality for the test methods. As after each test method the current transaction is rolled back
    the Client and Account objects must be saved before any test method is fulfilled.
     */
    public Account getTestedAccount() {
        clientDAO.saveEntity(currentClient);
        currentClient.getSetOfAccounts().add(currentAccount);
        currentAccount.setClientId(currentClient);
        accountDAO.saveEntity(currentAccount);
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Account account where account.accountNumber='EUR25300200456'");
        Account account = (Account) result.uniqueResult();
        return account;
    }

}
