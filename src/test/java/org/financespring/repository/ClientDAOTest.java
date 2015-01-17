package org.financespring.repository;

import junit.framework.Assert;
import org.financespring.model.Client;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/spring-servlet.xml")
public class ClientDAOTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private ClientDAO clientDAO;

    static Client currentClient;

    /*
    Create Client object to test CRUD operations.
     */
    @BeforeClass
    public static void createTestedClient() {
        currentClient = new Client();
        currentClient.setFirstName("Bruno");
        currentClient.setLastName("Prosato");
        currentClient.setAddress("Via La Machina, 17");
        currentClient.setCity("Treviso");
        currentClient.setPostalCode("23400");
    }

    @Test
    @Transactional
    public void testGetEntityById() {
        Client client  = clientDAO.getEntityById(Client.class, this.getTestedClient().getId());
        Assert.assertNotNull(client);
        Assert.assertEquals("Bruno", client.getFirstName());
        Assert.assertEquals("Prosato", client.getLastName());
        Assert.assertEquals("Via La Machina, 17", client.getAddress());
        Assert.assertEquals("Treviso" ,client.getCity());
        Assert.assertEquals("23400", client.getPostalCode());
        System.out.println("testGetEntityById() fulfilled successfully");
    }

    @Test
    @Transactional
    public void testSaveEntity() {
        Client client = this.getTestedClient();
        Assert.assertNotNull(client);
        System.out.println("testSaveEntity() fulfilled successfully");
    }

    @Test
    @Transactional
    public void testDeleteEntity() {
        Client client = this.getTestedClient();
        clientDAO.deleteEntity(client);
        client = clientDAO.getEntityById(Client.class, client.getId());
        Assert.assertNull(client);
        System.out.println("testDeleteEntity() fulfilled successfully");
    }

    @Test
    @Transactional
    public void testUpdateEntity() {
        clientDAO.saveEntity(currentClient);
        currentClient.setLastName("Prosantinto");
        clientDAO.updateEntity(currentClient);
        Session session = sessionFactory.getCurrentSession();
        Client client = (Client) session.createQuery("from Client client where client.firstName='Bruno'").uniqueResult();
        Assert.assertEquals("Prosantinto", client.getLastName());
        System.out.println("testUpdateEntity() fulfilled successfully");
    }

    @Test
    @Transactional
    public void testGetListOfEntities() {
        List<Client> listOfClients = clientDAO.getListOfEntities();
        clientDAO.saveEntity(currentClient);
        Assert.assertTrue(listOfClients.size() < clientDAO.getListOfEntities().size());
        System.out.println("testGetListOfEntities() fulfilled successfully");
    }

    /*
    Common functionality for the most of test methods. As after each CRUD-operation transaction is rolled back
    it needs the test Client object to be saved.
     */
    public Client getTestedClient() {
        clientDAO.saveEntity(currentClient);
        Session session = sessionFactory.getCurrentSession();
        Client client = (Client) session.createQuery("from Client client where client.firstName='Bruno'").uniqueResult();
        return client;
    }
}
