package by.leha.repositories.roles;

import by.leha.entity.role.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  {
    Optional<Role> findById(Integer id);
    Optional<Role> findByName(String name);


}
