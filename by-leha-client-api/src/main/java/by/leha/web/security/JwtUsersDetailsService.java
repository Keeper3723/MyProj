package by.leha.web.security;

import by.leha.entity.login.Login;
import by.leha.services.login.LoginService;
import by.leha.services.roles.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtUsersDetailsService implements UserDetailsService {

    private final LoginService loginService;
    private final RoleService roleService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginService.getByUserName(username);
        return JwtEntityFactory.createJwtEntity(login);
    }
    public void createNewUser(Login login){
        var role = roleService.getRoleByUsername("USER");
        loginService.addRolesToLogin(login, Set.of(role));
    }
}
