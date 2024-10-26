package by.leha.services;

import by.leha.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client getClientById(Long id) throws RuntimeException;
    boolean removeClientById(Long id);
    boolean addClient(Client client);
    boolean updateClientById(Long id, Client client);




}
