package by.leha.web.mappers;

import by.leha.entity.login.Login;

import by.leha.entity.role.Role;
import by.leha.mappers.Mapper;
import by.leha.dto.login.LoginDto;
import by.leha.services.roles.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginMapper implements Mapper<Login, LoginDto> {


    private final RoleService roleService;
    @Override
    public Login reverseMap(LoginDto loginDto) {
    Set<Role> roles =
            loginDto.getRoles().stream().map(role -> roleService.getRoleByUsername(role.name())).collect(Collectors.toSet());
        return  Login.builder()
                .id(loginDto.getId())
                .roles(roles)
                .username(loginDto.getUsername())
                .password(loginDto.getPassword())
                .build();
    }

    @Override
    public LoginDto map(Login login) {
        return LoginDto.builder()
                .username(login.getUsername())
                .roles(login.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()))
                .id(login.getId())
                .password(login.getPassword()).build();

    }
}
