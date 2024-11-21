package by.leha.repositories.roles;

import by.leha.dto.utils.RoleEnum;
import by.leha.entity.role.Role;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final SessionFactory sessionFactory;
    @Override
    public Optional< Role> findById(Integer id) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try{

           var role = Optional.of( entityManager.find(Role.class,id));
            entityManager.getTransaction().commit();
            return role;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return Optional.empty();
        }



    }

    @Override
    public Optional<Role> findByName(String name) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try{
            var roleEnum = RoleEnum.valueOf(name);
            var role = entityManager.createQuery("""

from Role as r where r.role in (:role)

""", Role.class).setParameter("role", roleEnum ).getSingleResult();

            entityManager.getTransaction().commit();
            return Optional.of( role);
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return Optional.empty();
        }
    }

}
