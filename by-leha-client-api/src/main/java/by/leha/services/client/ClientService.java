package by.leha.services.client;

import by.leha.entity.client.Client;
import by.leha.entity.login.Login;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client getClientById(Long id) throws RuntimeException;
    boolean removeClientById(Long id);
    boolean addClient(Client client);
    boolean updateClientById(Long id, Client client);
    boolean addLoginToClient(Client client, Long login_id);




}
