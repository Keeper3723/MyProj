package by.leha.entity.login;


import by.leha.entity.role.Role;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "login")
public class Login implements Serializable , UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "login")
    private String username;
    @Column(name = "password")
    private String password;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "logins_roles",
            joinColumns = {@JoinColumn(name = "login_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(Role::getRole)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }
}