package org.financespring.service;

import org.financespring.model.Client;
import org.financespring.repository.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Interface defines generic type Client. Service-layer.
 */
public interface ClientService extends EntityService<Client> {
}
