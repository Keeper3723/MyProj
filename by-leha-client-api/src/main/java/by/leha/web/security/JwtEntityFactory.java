package by.leha.web.security;


import by.leha.entity.login.Login;
import by.leha.entity.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {
    public static JwtEntity createJwtEntity(Login login) {

        return new JwtEntity(
                login.getId(),
                login.getUsername(),
                login.getPassword(),
                mapToGrantedAuthorizatin(new ArrayList<>(login.getRoles())));

    }


    private static List<GrantedAuthority> mapToGrantedAuthorizatin(List<Role> roles) {

    return roles.stream()
            .map(Role::getRole)
            .map(Enum::name)
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }



}
