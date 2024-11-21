package by.leha.repositories.login;

import by.leha.entity.login.Login;
import by.leha.entity.role.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LoginRepository  {
    Optional< List<Login>> findAll();
    Optional <Login> findById(Long id);
    Optional<Login> findByUsername(String username);
    boolean create(Login login);
    boolean update(Login login);
    boolean delete(Long id);


    boolean addRolesToLogin(Login login, Set<Role> roles);
}
