package by.leha.repositories.client;

import by.leha.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> findAllClients();
    Client findClientById(Long id);
    boolean insertClient(Client client);
    boolean updateClientById(Long id,Client client);
    boolean deleteClientById(Long id);



}
