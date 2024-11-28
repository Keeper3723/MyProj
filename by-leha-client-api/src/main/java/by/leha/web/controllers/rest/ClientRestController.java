package by.leha.web.controllers.rest;

import by.leha.dto.client.ClientDto;
import by.leha.entity.client.Client;
import by.leha.services.client.ClientService;
import by.leha.web.mappers.ClientDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api1/clients")
public class ClientRestController {
    private final ClientDtoMapper mapper;
    private final ClientService clientService;


    public ClientRestController(@Autowired @Qualifier("clientDtoMapper") ClientDtoMapper mapper, @Autowired @Qualifier("clientServiceImpl") ClientService clientService) {
        this.mapper = mapper;
        this.clientService = clientService;
    }


    @GetMapping("")
    public List<ClientDto> getAllClient() {
        return clientService.getAllClients().stream().map(mapper::map).toList();
    }


    @PostMapping("")
    public ResponseEntity<?>createClient(@RequestBody ClientDto clientDto) {
        Client client = mapper.reverseMap(clientDto);
        boolean isAdded = clientService.addClient(client);
        if (isAdded)
            return new ResponseEntity<ClientDto>(mapper.map(client), HttpStatus.CREATED);
        else {
            return new ResponseEntity<Client>( HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {

        var client = clientService.getClientById(id);

        if (client == null){

            return new ResponseEntity<Client>( HttpStatus.NOT_FOUND);

        }


        return new ResponseEntity<Client>(client, HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable long id) {
        boolean wasCreated = clientService.removeClientById(id);

        if (wasCreated) return new ResponseEntity<String>("Client was deleted", HttpStatus.OK);
        return new ResponseEntity<String>("Client wasnt created", HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClientById(@RequestBody ClientDto clientDto, @PathVariable Long id) {

        boolean isClientUpdated = clientService.updateClientById(id, mapper.reverseMap(clientDto));

        if (isClientUpdated) {
            return new ResponseEntity<String>("Client was updated", HttpStatus.OK);
        } else return new ResponseEntity<String>("Client was not updated", HttpStatus.BAD_REQUEST);


    }


}
