package by.leha.config.security;

import by.leha.services.login.LoginService;
import by.leha.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurity  {

    private final LoginService loginService;
    private final ApplicationContext applicationContext;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(loginService);
    return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider) throws Exception {
        return      http
                .httpBasic(Customizer.withDefaults())

              .csrf(AbstractHttpConfigurer::disable)
              .cors(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api1/**","/auth/**","/").permitAll()
                        .requestMatchers("/**").hasAuthority("USER")
                        .anyRequest().authenticated()

                )
              .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .exceptionHandling(handler -> handler.authenticationEntryPoint((request, response, authException) -> {

                  response.setStatus(HttpStatus.UNAUTHORIZED.value());
                  response.getWriter().write("Unauthorized");
              }
              ).accessDeniedHandler((request, response, accessDeniedException) ->  {
                  response.setStatus(HttpStatus.FORBIDDEN.value());
                  response.getWriter().write("Unauthorized");
              }))


              .anonymous(AbstractHttpConfigurer::disable)
                .build();
    }






    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        String generatedPassword = "password";
        return new InMemoryUserDetailsManager(User.withUsername("user")
                .password("{noop}"+generatedPassword).roles("USER")

                .build())
                ;


    }


    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
return new DefaultAuthenticationEventPublisher(delegate);
    }



}

