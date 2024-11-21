package by.leha.repositories.client;

import by.leha.entity.client.Client;
import by.leha.entity.login.Login;

import java.util.List;

public interface ClientRepository {
    List<Client> findAllClients();
    Client findClientById(Long id);
    boolean insertClient(Client client);
    boolean updateClientById(Long id,Client client);
    boolean deleteClientById(Long id);
    boolean addLoginToClient(Long login, Client client);



}
