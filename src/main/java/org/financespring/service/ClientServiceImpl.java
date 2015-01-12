package org.financespring.service;

import org.financespring.model.Client;
import org.financespring.repository.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of ClientService-interface.
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Transactional
    @Override
    public Client getEntityById(Class entityClass, int id) {
        Client client = clientDAO.getEntityById(entityClass, id);
        return client;
    }

    @Transactional
    @Override
    public void saveEntity(Client client) {
        clientDAO.saveEntity(client);
    }

    @Transactional
    @Override
    public void deleteEntity(Client client) {
        clientDAO.deleteEntity(client);
    }

    @Transactional
    @Override
    public void updateEntity(Client client) {
        clientDAO.updateEntity(client);
    }

    @Transactional
    @Override
    public List<Client> getListOfEntities() {
        List<Client> listOfClients = clientDAO.getListOfEntities();
        return listOfClients;
    }

}
