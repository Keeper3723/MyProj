package by.leha.repositories.client;

import by.leha.entity.Client;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor(onConstructor_ = @Autowired)
@NoArgsConstructor
@Slf4j
public class ClientRepositoryImpl implements  ClientRepository {
    //todo настроить hibernate config, создать бд, доделать энтити, создать базовые rest, crud операции

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Client> findAllClients() {
        var session = sessionFactory.openSession();


        var clients =  session.createQuery("""
from  Client
""", Client.class).getResultList();

        log.info("all clients was found");
        return clients;



    }




    public Client findClientById(Long id){
        var session =sessionFactory.openSession();
        return session.createQuery("""
from Client c where  c.id = :id
""", Client.class).setParameter("id", id).getSingleResult();

    }


    @Override
    @Transactional
    public boolean updateClientById(Long id, Client client){
        var session = sessionFactory.openSession();
        session.beginTransaction();
        boolean isUpdated = session.createQuery("""
update Client   set birthDate=  :birth_date,
fullName = :full_name,
email = :email,
passportSerialNumber = :passport_data,
phoneNumber = :phone_number,
sex = :sex
 where id = :id

""").setParameter("birth_date", client.getBirthDate())
                .setParameter("full_name", client.getFullName())
                .setParameter("email",client.getEmail())
                .setParameter("passport_data", client.getPassportSerialNumber())
                .setParameter("phone_number", client.getPhoneNumber())
                .setParameter("sex", client.getSex())
                .setParameter("id", id)

        .executeUpdate()>0;


        session.getTransaction().commit();
        return isUpdated;


    }
    @Override
    @Transactional
    public boolean deleteClientById(Long id){
        var session =  sessionFactory.openSession();
        session.beginTransaction();
       boolean idClientChancged  = session.createQuery("""
delete Client  where id = :id
""").setParameter("id", id).executeUpdate()>0;
        session.getTransaction().commit();
        return idClientChancged;
    }

    @Override
    public boolean insertClient(Client client){
        var session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist("Client",client);

        session.getTransaction().commit();
        return true;
    }

}
