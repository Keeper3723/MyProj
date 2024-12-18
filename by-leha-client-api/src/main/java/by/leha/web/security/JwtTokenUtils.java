package by.leha.web.security;

import by.leha.services.client.ClientService;
import by.leha.services.login.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.audiance}")
    private Duration audiance;

    private SecretKey secretKey;
    private final ClientService clientService;
    private final LoginService loginService;


    @PostConstruct
    public void init() {
        secretKey =Keys.hmacShaKeyFor(secret.getBytes());


    }

public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    List<String> roleList = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();
    claims.put("roles", roleList);
    claims.put("client_id",clientService.findClientByLogin(loginService.getByUserName(userDetails.getUsername())).getId());

    Date issueDate = new Date();
    Date expieredDate = new Date(issueDate.getTime() + audiance.toMillis());

    return Jwts.builder()
            .claims().add(claims)
            .and().subject(userDetails.getUsername())
            .issuedAt(issueDate)
            .expiration(expieredDate)
            .signWith(secretKey)
            .compact();
    }

    private Claims getClaimsFromToken(String token) {
    return (Claims) Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parse(token)
            .getPayload();




    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();

    }
    public String getClientIdFromToken(String token){
        return getClaimsFromToken(token).get("client_id", String.class);
    }
    public List<String> getRolesFromToken(String token) {
        List<String> roleList = getClaimsFromToken(token).get("roles", List.class);
        return roleList;
    }


}
