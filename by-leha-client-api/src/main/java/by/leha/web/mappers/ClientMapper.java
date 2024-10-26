package by.leha.web.mappers;


import by.leha.dto.ClientDto;
import by.leha.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements Mapper<Client, ClientDto>{


    @Override
    public ClientDto map(Client f) {
        return new ClientDto(f.getId(), f.getFullName(),f.getBirthDate(),f.getSex(), f.getPhoneNumber(),f.getPassportSerialNumber(),f.getEmail());
    }

    @Override
    public Client reverseMap(ClientDto t) {
        return Client.builder().id(t.getId())
                .sex(t.getSex())
                .email(t.getEmail())
                .fullName(t.getFullName())
                .passportSerialNumber(t.getPassportSerialNumber())
                .birthDate(t.getBirthDate())
                .phoneNumber(t.getPhoneNumber())
                .build();
    }
}
