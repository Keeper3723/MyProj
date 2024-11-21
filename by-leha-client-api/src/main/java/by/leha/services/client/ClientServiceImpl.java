package by.leha.services.client;

import by.leha.entity.client.Client;
import by.leha.entity.login.Login;
import by.leha.repositories.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;


    @Override
    public List<Client> getAllClients() {
     return clientRepository.findAllClients();
    }

    @Override
    public Client getClientById(Long id) {
        try {
            return clientRepository.findClientById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeClientById(Long id) {
        return clientRepository.deleteClientById(id);
    }

    @Override
    public boolean addClient(Client client) {
       return clientRepository.insertClient(client);
    }

    @Override
    public boolean updateClientById(Long id, Client client) {
        return clientRepository.updateClientById(id, client);



    }

    @Override
    public boolean addLoginToClient(Client client, Long loginId) {
        return clientRepository.addLoginToClient(loginId,client);
    }
}
