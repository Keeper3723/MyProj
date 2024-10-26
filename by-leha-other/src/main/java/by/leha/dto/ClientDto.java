package by.leha.dto;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String fullName;
    private Date birthDate;
    private String sex;
    private String phoneNumber;
    private String passportSerialNumber;
    private String email;





}
