package by.leha;

import by.leha.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ClientSpringBootApplication {
    public static void main(String[] args) {
        log.info("Starting ClientSpringBootApplication");
        SpringApplication app = new SpringApplication(ClientSpringBootApplication.class);
        app.run(args);


    }
}
