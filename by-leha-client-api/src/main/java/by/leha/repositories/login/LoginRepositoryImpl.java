package by.leha.repositories.login;

import by.leha.entity.login.Login;

import by.leha.entity.role.Role;
import by.leha.services.roles.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


import java.net.UnknownServiceException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public  class LoginRepositoryImpl implements LoginRepository {

    private final SessionFactory sessionFactory;
    private final RoleService roleService;



    @Override

    public Optional< List<Login>> findAll() {
        var session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            var logins = session.createQuery("from Login", Login.class).list();
            session.getTransaction().commit();

            return Optional.of( logins);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Login> findById(Long id) {
        var session = sessionFactory.createEntityManager();
        session.getTransaction().begin();

        try {
            var login = session.find(Login.class, id);
            session.getTransaction().commit();
            return Optional.of(login);
        } catch (Exception e) {
            session.getTransaction().rollback();
            return Optional.empty();
        }


    }

    @Override
    public Optional<Login> findByUsername(String username) {

        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try{

            var client =  entityManager.createQuery("""
select  Login  where  Login.username = :username
""", Login.class).setParameter("username", username).getSingleResult();
            entityManager.getTransaction().commit();
            return Optional.of( client);


        }catch (Exception e){

            entityManager.getTransaction().rollback();
            return Optional.empty();
        }


    }

    @Override
    public boolean create(Login login) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(login);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e ){
            entityManager.getTransaction().rollback();
            return false;
        }




    }

    @Override
    public boolean update(Login login) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {


            boolean isUpdated = entityManager.createQuery("""
                    UPDATE Login l set
 l.username = :name,
 l.password = :password,
 l.roles = :roles
 where l.id = :id
""")
                    .setParameter("id",login.getId())
                    .setParameter("name",login.getUsername())
                    .setParameter("password",login.getPassword())
                    .setParameter("roles",login.getRoles())
                    .executeUpdate( ) >0;

entityManager.getTransaction().commit();
        return isUpdated;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return false;
        }


    }


    @Override
    public boolean delete(Long id) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entityManager.find(Login.class, id));

            entityManager.getTransaction().commit();

            return true;

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean addRolesToLogin(Login login, Set<Role> roles) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            login.getRoles().addAll(roles);
            entityManager.merge(login);


            entityManager.getTransaction().commit();
            return true;
        }catch ( Exception e){
            entityManager.getTransaction().rollback();
            return false;
        }
    }


}
