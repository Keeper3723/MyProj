package by.leha.entity;

import jakarta.persistence.*;
import lombok.*;


import java.sql.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Client {
    //todo переопрделить hashcode и equals правильно
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "sex")
    private String sex;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "passport_serial_number")
    private String passportSerialNumber;
    @Column(name = "email")
    private String email;


}
