package org.financespring.repository;

import org.financespring.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client getEntityById(Client entityClass, int id) {
        Session session = sessionFactory.getCurrentSession();
        Client currentClient = (Client) session.load(Client.class, id);
        return currentClient;
    }

    @Override
    public void saveEntity(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.save(client);
    }

    @Override
    public void deleteEntity(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(client);
    }

    @Override
    public void updateEntity(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.update(client);
    }

    @Override
    public List<Client> getListOfEntities() {
        Session session = sessionFactory.getCurrentSession();
        List<Client> listOfClients = session.createQuery("from Client").list();
        return listOfClients;
    }
}
