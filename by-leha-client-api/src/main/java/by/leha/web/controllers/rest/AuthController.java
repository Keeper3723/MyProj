package by.leha.web.controllers.rest;

import by.leha.exceptions.dtos.AppError;
import by.leha.services.login.LoginService;
import by.leha.web.security.JwtResponse;
import by.leha.web.security.JwtRequest;
import by.leha.web.security.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final LoginService loginService;
    private final JwtTokenUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authReq){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.username(), authReq.password()));
        }catch (BadCredentialsException e){
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Invalid password or login", new Date()),HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = loginService.loadUserByUsername(authReq.username());
        String token = jwtUtils.generateToken(userDetails);
        var response = new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
        response.getHeaders().set("Authorization","Bearer "+token);
        return response;


    }

}
