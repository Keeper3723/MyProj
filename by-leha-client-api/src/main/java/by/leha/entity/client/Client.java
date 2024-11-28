package by.leha.entity.client;

import by.leha.entity.entity_utils.Sex;
import by.leha.entity.login.Login;
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
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "passport_serial_number")
    private String passportSerialNumber;
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "login_id",referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Login login;


}
