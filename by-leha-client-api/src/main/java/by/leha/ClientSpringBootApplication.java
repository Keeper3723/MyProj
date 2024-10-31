package by.leha;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
@Slf4j
public class ClientSpringBootApplication {


    public static void main(String[] args) {
        log.info("Starting ClientSpringBootApplication");
        SpringApplication app = new SpringApplication(ClientSpringBootApplication.class);


        app.run(args);



    }
}
