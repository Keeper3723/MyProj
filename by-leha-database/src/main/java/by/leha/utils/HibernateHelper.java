package by.leha.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;



@org.springframework.context.annotation.Configuration
public class HibernateHelper {
    @Bean
    public SessionFactory sessionFactory( ) {


        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory();


    }

}
