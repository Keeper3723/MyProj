package by.leha.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClientDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private Date birthDate;
    @NotEmpty
    private String sex;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String passportSerialNumber;
    @Email
    private String email;

    private Long login_id;






}
