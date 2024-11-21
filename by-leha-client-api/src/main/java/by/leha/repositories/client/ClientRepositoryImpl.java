package by.leha.repositories.client;

import by.leha.entity.client.Client;
import by.leha.entity.login.Login;
import by.leha.services.login.LoginService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ClientRepositoryImpl implements  ClientRepository {
    //todo прописать правильные транзакции в методах

    private final SessionFactory sessionFactory;
    private final LoginService loginService;

    @Override

    public List<Client> findAllClients() {
        var entityManager = sessionFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            var clients = entityManager.createQuery("""

                    from  Client
""", Client.class).getResultList();

        entityManager.getTransaction().commit();
        log.info("all clients was found");
        return clients;
      }catch (Exception e){
            entityManager.getTransaction().rollback();
            log.error("Exception {0}",e);
            return null;
        }


    }



    @Override

    public Client findClientById(Long id){
        var entityManager = sessionFactory.getCurrentSession();
        entityManager.getTransaction().begin();
        try{
           var client = entityManager.createQuery("""
                   from Client as c   where  c.id = :id
""", Client.class).setParameter("id", id).getSingleResult();

entityManager.getTransaction().commit();

return client;
        } catch (Exception e ){

            entityManager.getTransaction().rollback();
            return null;
        }




    }


    @Override

    public boolean updateClientById(Long id, Client client){
        var entityManager = sessionFactory.createEntityManager();

        boolean isUpdated = entityManager.createQuery("""
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



        return isUpdated;


    }
    @Override

    public boolean deleteClientById(Long id){
        var entityManager =  sessionFactory.createEntityManager();

       boolean idClientChancged  = entityManager.createQuery("""
delete Client  where id = :id
""").setParameter("id", id).executeUpdate()>0;

        return idClientChancged;
    }

    @Override
    @Transactional
    public boolean addLoginToClient(Long login_id, Client client) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            var login = loginService.getById(login_id);

            client.setLogin(login);
            entityManager.merge(client);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){

            entityManager.getTransaction().rollback();
            return false;

        }



    }

    @Override

    public boolean insertClient(Client client){
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        return true;
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            return false;
        }


    }

}
