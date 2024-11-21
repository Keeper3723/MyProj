package by.leha.services.roles;

import by.leha.entity.role.Role;

public interface RoleService {
    Role getRoleById(Integer id);
    Role getRoleByUsername(String username);
}
