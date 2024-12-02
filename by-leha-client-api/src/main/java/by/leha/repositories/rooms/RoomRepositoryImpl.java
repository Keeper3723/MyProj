package by.leha.repositories.rooms;

import by.leha.entity.room.Room;

import by.leha.entity.room.enums.RoomStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoomRepositoryImpl implements RoomRepository{
    private final SessionFactory sessionFactory;

    @Override
    public Optional< List<Room>> findAll() {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            var rooms = entityManager.createQuery("""
From Room
""", Room.class).getResultList();
            entityManager.getTransaction().commit();
            log.info("all room was found");
            return Optional.of( rooms);
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            log.info("rooms not found");
            return Optional.empty();

        }
    }

    @Override
    public Optional<Room> findRoomById(Long id) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            var room = entityManager.find(Room.class,id);
            entityManager.getTransaction().commit();
            log.info("room by id %s was found".formatted(id));
            return Optional.of(room);

        }catch (Exception e){
            entityManager.getTransaction().rollback();
            log.warn("room by id %s wasn't found".formatted(id));
            return Optional.empty();
        }

    }

    @Override
    public boolean insertRoom(Room room) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {

            entityManager.persist(room);
            entityManager.getTransaction().commit();
            log.info("room %s was inserted");
            return true;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            log.warn("room %s wasn't inserted");
            return false;
        }

    }

    @Override
    public boolean updateRoomById(Long id, Room room) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {

           boolean wasRoomChanged = entityManager.createQuery("""
update Room set 
floor = :floor,
roomClass = :roomClass,
status  = :status,
number = :number
where id = :id
""")
                    .setParameter("floor" ,room.getFloor())
                    .setParameter("roomClass",room.getRoomClass())
                    .setParameter("status", room.getStatus())
                    .setParameter("number", room.getNumber())
                    .setParameter("id", room.getId()).executeUpdate()>0;
            entityManager.getTransaction().commit();
            log.info("room by id %s was changed".formatted(id));
            return wasRoomChanged;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            log.warn("room by id %s wasn't changed".formatted(id));
            return false;
        }

    }

    @Override
    public boolean deleteRoomById(Long id) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            boolean wasRoomDeleted = entityManager.createQuery("""
delete Room where id = :id
""")
                    .setParameter("id",id)
                    .executeUpdate()>0;
            entityManager.getTransaction().commit();
            return wasRoomDeleted;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            log.warn("room by id %s wasn't deleted".formatted(id));
            return false;
        }

    }


    @Override
    public Optional< List<Room>> getAllNotBookedRooms() {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            var openRooms = entityManager.createQuery("""
from Room where status = :status
""",Room.class)
                    .setParameter("status", RoomStatus.OPEN)
                    .getResultList();
            entityManager.getTransaction().commit();
            log.info("Not Booked rooms was found");
           return Optional.of(openRooms);
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            log.warn("Not Booked rooms wasn't found");
            return Optional.empty();

        }
    }


}
