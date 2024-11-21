package by.leha.web.security;

import by.leha.entity.login.Login;
import by.leha.entity.role.Role;
import by.leha.services.login.LoginService;
import by.leha.web.dto.JwtResponse;
import by.leha.web.security.props.JwtProperties;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final UserDetailsService userDetailsService;

    private final LoginService loginService;
    private final RestTemplateAutoConfiguration restTemplateAutoConfiguration;


    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

    }

    public String createAccessToken(Long userId, String username, Set<Role> roles) {
Claims claims = Jwts.claims().subject(username).build();
claims.put("roles", resolveRoles(roles));
claims.put("id", userId);
    Date now = new Date();
    Date validity = new Date(now.getTime()+jwtProperties.getAccess());
    return Jwts.builder()
            .claims(claims)
            .issuedAt(now)
            .expiration(validity)
            .signWith(secretKey)
            .compact();


    }
    private List<String> resolveRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getRole)
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    public String createRefreshToken(Long userId, String username){
        Claims claims = Jwts.claims().subject(username).build();
        claims.put("id", userId);
        Date now = new Date();
        Date validity = new Date(jwtProperties.getRefresh() + now.getTime());
        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey)
                .compact();
    }

public JwtResponse refreshUserToken(String refreshToken) throws Exception {
        JwtResponse jwtResponse = new JwtResponse();

        if(!validateToken(refreshToken)){
            throw new Exception();
        }

        Long userId = Long.valueOf(getId(refreshToken));
        Login login = loginService.getById(userId);
        jwtResponse.setId(userId);
        jwtResponse.setUsername(login.getUsername());
        jwtResponse.setAccessToken(createAccessToken(userId, login.getUsername(), login.getRoles()));
        jwtResponse.setRefreshToken(createRefreshToken(userId, login.getUsername()));

return jwtResponse;
     }

public boolean validateToken(String token) {


        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

        return !claims.getPayload().getExpiration().before(new Date());


}

private String getId(String token) {

        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseUnsecuredClaims(token)
                .getPayload()
                .getSubject();

}



public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
}

private String getUsername(String token) {
    return Jwts
            .parser()
            .verifyWith(secretKey)
            .build()
            .parseUnsecuredClaims(token)
            .getPayload()
            .get("username")
            .toString();

}






}
