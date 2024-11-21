package by.leha.web.dto.client;




import by.leha.entity.entity_utils.Sex;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;

@Valid
public record ClientCreateDto(@NotEmpty String fullName, @NotEmpty Date birthDate, @NotEmpty Sex sex, @NotEmpty String phoneNumber, @NotEmpty String passportSerialNumber, @Email String email ) {



}
