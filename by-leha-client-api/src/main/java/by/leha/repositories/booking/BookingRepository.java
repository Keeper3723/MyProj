package by.leha.repositories.booking;

import by.leha.entity.booking.Booking;
import by.leha.entity.booking.enums.BookingStatus;
import by.leha.entity.client.Client;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Optional< List<Booking>> findAllBooking();

    Optional< Booking> findBookingById(Long id);

    boolean insertBooking(Booking booking);

    boolean updateBookingById(Long id, Booking booking);

    boolean deleteBookingById(Long id);

    Optional<Booking> getBookingByStatus(BookingStatus status);
}