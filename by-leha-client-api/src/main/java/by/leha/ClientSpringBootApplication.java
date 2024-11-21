package by.leha;


import by.leha.repositories.client.ClientRepositoryImpl;
import by.leha.repositories.login.LoginRepositoryImpl;
import by.leha.utils.HibernateHelper;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.xml.multi.model.Configurations;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Slf4j
public class ClientSpringBootApplication {



    public static void main(String[] args) {
        log.info("Starting ClientSpringBootApplication");
        SpringApplication app = new SpringApplication(ClientSpringBootApplication.class);


        app.run(args);



    }
}
