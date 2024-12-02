package by.leha.web.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String header = request.getHeader("host");
       String username = null;
       String jwtToken = null;

       if (header != null && header.startsWith("Bearer ")) {
           jwtToken = header.substring(7);
       }
       try {
           username = jwtTokenUtils.getUsernameFromToken(jwtToken);

       } catch (ExpiredJwtException e){
           log.info("JWT token expired");
       }catch (Exception s){
           log.info("JWT token signature exception");
       }
       if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        jwtTokenUtils.getRolesFromToken(jwtToken).stream().map(SimpleGrantedAuthority::new).toList()

                );

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
       filterChain.doFilter(request, response);
    }
}
