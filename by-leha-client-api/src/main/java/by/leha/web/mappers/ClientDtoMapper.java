package by.leha.web.mappers;


import by.leha.dto.client.ClientDto;
import by.leha.entity.client.Client;
import by.leha.entity.entity_utils.Sex;
import by.leha.mappers.Mapper;
import by.leha.services.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientDtoMapper implements Mapper<Client, ClientDto> {

    private final LoginService loginService;

    @Override
    public ClientDto map(Client f) {
        return new ClientDto(
                f.getId()
                , f.getFullName()
                ,f.getBirthDate()
                ,f.getSex().name()
                , f.getPhoneNumber()
                ,f.getPassportSerialNumber()
                ,f.getEmail()
                ,f.getLogin().getId());
    }

    @Override
    public Client reverseMap(ClientDto t) {
        return Client.builder()
                .id(t.getId())
                .sex(Sex.valueOf(t.getSex()))
                .email(t.getEmail())
                .fullName(t.getFullName())
                .passportSerialNumber(t.getPassportSerialNumber())
                .birthDate(t.getBirthDate())
                .phoneNumber(t.getPhoneNumber())
                .build();
    }
}
