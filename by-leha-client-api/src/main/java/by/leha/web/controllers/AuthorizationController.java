package by.leha.web.controllers;

import by.leha.dto.client.ClientDto;
import by.leha.repositories.roles.RoleRepository;
import by.leha.services.client.ClientService;
import by.leha.dto.login.LoginDto;
import by.leha.services.login.LoginService;
import by.leha.web.controllers.rest.AuthController;
import by.leha.web.dto.client.ClientCreateDto;
import by.leha.web.mappers.ClientCreateDtoMapper;
import by.leha.web.mappers.LoginMapper;
import by.leha.web.security.JwtRequest;
import by.leha.web.security.JwtResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthorizationController {


private final ClientService clientService;
private final RoleRepository roleRepository;
private final ClientCreateDtoMapper clientCreateMapper;
private final LoginMapper loginMapper;
private final Environment environment;
private final PasswordEncoder passwordEncoder;
private final LoginService loginService;
private final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("login", new LoginDto());
    return "auth/login";
}
    @PostMapping("/login")
    @Transactional
    public String login(@ModelAttribute("login") LoginDto loginDto, BindingResult bindingResult, Model model){


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        }catch (BadCredentialsException e){
            bindingResult.reject("invalid_username");
            return "auth/login";
        }
       // JwtResponse token = (JwtResponse) authController.createAuthToken(new JwtRequest(loginDto.getUsername(), loginDto.getPassword())).getBody();

        return "static/index";
    }




@GetMapping("/registration")
    public String registrationPage(Model model){

        model.addAttribute("client", new ClientDto());
        model.addAttribute("login", new LoginDto());


    return "auth/registration";
}

@PostMapping("/registration")
@Transactional
    public String registerUser(@ModelAttribute("login") @Valid LoginDto loginDto,
                                 @ModelAttribute("client") @Valid ClientCreateDto clientDto,
                                 @RequestParam("confirm_password") String password_confirm,
                                 BindingResult bindingResult){

if(!loginDto.getPassword().equals(password_confirm))
    bindingResult.addError(new ObjectError(password_confirm, "Пароли не совпадают"));
loginDto.setPassword(passwordEncoder.encode(loginDto.getPassword()));
if(bindingResult.hasErrors()){
    return "auth/registration";
}
var client = clientCreateMapper.reverseMap(clientDto);
var login = loginMapper.reverseMap(loginDto);
clientService.addClient(client);
loginService.addUser(login);
clientService.addLoginToClient(client,login.getId());

    return "hotel/hotels";
}







}
