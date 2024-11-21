package by.leha.services.login;

import by.leha.entity.login.Login;
import by.leha.entity.role.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;
import java.util.Set;

public interface LoginService extends UserDetailsService {

    List<Login> getAll();
    Login getById(Long id);
    Login getByUserName(String username);
    boolean update(Login login);
    boolean delete(Long id);
    boolean add(Login login);
    boolean addUser(Login login);

    boolean addRolesToLogin(Login login, Set<Role> roles);
}
