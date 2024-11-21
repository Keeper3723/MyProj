package by.leha.services.roles;

import by.leha.entity.role.Role;
import by.leha.exceptions.ResourceNotFoundException;
import by.leha.repositories.roles.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements  RoleService{
    private final RoleRepository roleRepository;
    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Role getRoleByUsername(String name) {
        return roleRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }
}
