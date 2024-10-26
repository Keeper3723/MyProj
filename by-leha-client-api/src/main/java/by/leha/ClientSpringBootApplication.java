package by.leha;


import by.leha.config.security.WebSecurity;
import by.leha.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {  SecurityAutoConfiguration.class})
@Slf4j
public class ClientSpringBootApplication {


    public static void main(String[] args) {
        log.info("Starting ClientSpringBootApplication");
        SpringApplication app = new SpringApplication(ClientSpringBootApplication.class);


        app.run(args);



    }
}
