package by.leha.web.mappers;

import by.leha.entity.client.Client;
import by.leha.mappers.Mapper;
import by.leha.services.login.LoginService;
import by.leha.web.dto.client.ClientCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientCreateDtoMapper  implements Mapper<Client, ClientCreateDto> {
    private final LoginService loginService;

    @Override
    public ClientCreateDto map(Client client) {
        return  new ClientCreateDto(
                client.getFullName()
                ,client.getBirthDate()
                ,client.getSex()
                ,client.getPhoneNumber()
                ,client.getPassportSerialNumber()
                ,client.getEmail());
    }

    @Override
    public Client reverseMap(ClientCreateDto clientCreateDto) {

        return Client.builder()
                .email(clientCreateDto.email())
                .sex(clientCreateDto.sex())
                .phoneNumber(clientCreateDto.phoneNumber())
                .birthDate(clientCreateDto.birthDate())
                .passportSerialNumber(clientCreateDto.passportSerialNumber())
                .fullName(clientCreateDto.fullName())

                .build();

    }
}
