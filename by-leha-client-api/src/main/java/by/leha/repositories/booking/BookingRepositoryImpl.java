package by.leha.repositories.booking;

import by.leha.entity.booking.Booking;
import by.leha.entity.booking.enums.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookingRepositoryImpl implements BookingRepository{
    private final SessionFactory sessionFactory;
    @Override
    public Optional< List<Booking>> findAllBooking() {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
         var books = entityManager.createQuery("""
from Booking
""", Booking.class).getResultList();
            entityManager.getTransaction().commit();
            return Optional.of(books);
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return Optional.empty();
        }


    }

    @Override
    public Optional< Booking> findBookingById(Long id) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            var book = entityManager.find(Booking.class, id);
            entityManager.getTransaction().commit();
            return Optional.of(book);
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return Optional.empty();
        }
    }

    @Override
    public boolean insertBooking(Booking booking) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
           entityManager.persist(booking);

            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
         return false;
        }
    }

    @Override
    public boolean updateBookingById(Long id, Booking booking) {
        var entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
        var wasUpdated =   entityManager.createQuery("""
update Booking set  
status = :status,
amount = :amount,
client_id = :client_id,
date_in = :date_in,
date_out = :date_out,
room = :room
where id = :id
""")
                    .setParameter("status",booking.getStatus())
                    .setParameter("amount", booking.getAmount())
                    .setParameter("client_id",booking.getClient_id())
                    .setParameter("date_in", booking.getDate_in())
                    .setParameter("date_out", booking.getDate_out())
                    .setParameter("room",booking.getRoom())
                    .setParameter("id", booking.getId())
                    .executeUpdate()>0;
            entityManager.getTransaction().commit();

            return wasUpdated;

        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean deleteBookingById(Long id) {
        return false;
    }

    @Override
    public Optional<Booking> getBookingByStatus(BookingStatus status) {
        return Optional.empty();
    }


}
