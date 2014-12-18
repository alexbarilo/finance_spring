package org.financespring.service;

import org.financespring.model.Client;
import org.financespring.repository.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;

public interface ClientService {

    public void saveClient(Client client);


}
