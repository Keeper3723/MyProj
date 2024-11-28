package by.leha.services.login;

import by.leha.entity.login.Login;
import by.leha.entity.role.Role;
import by.leha.exceptions.ResourceAlreadyExist;
import by.leha.exceptions.ResourceNotFoundException;
import by.leha.repositories.login.LoginRepository;
import by.leha.repositories.roles.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final RoleRepository roleRepository;


    @Override
    public List<Login> getAll() {
        return loginRepository.findAll().orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Login getById(Long id) {
        return loginRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Login getByUserName(String username) {

        return loginRepository.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public boolean update(Login login) {
        return loginRepository.update(login);
    }

    @Override
    public boolean delete(Long id) {
        return loginRepository.delete(id);
    }

    @Override
    public boolean add(Login login) {

      if (   loginRepository.findByUsername(login.getUsername()).isEmpty()){

          return  loginRepository.create(login);
      }
      throw new ResourceAlreadyExist();
    }

    @Override
    public boolean addUser(Login login) {

        if (   loginRepository.findByUsername(login.getUsername()).isEmpty()){
             login.getRoles().add(roleRepository.findByName("USER").get());
            return  loginRepository.create(login);
        }
        throw new ResourceAlreadyExist();
    }

    @Override
    public boolean addRolesToLogin(Login login, Set<Role> roles) {
        return loginRepository.addRolesToLogin(login, roles);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return loginRepository.findByUsername(username).orElseThrow(() ->{
            log.warn("Username {} not found", username);
           return new UsernameNotFoundException(String.format("User %s not found",username));
        } );


    }

}
