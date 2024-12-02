package by.leha.services.booking;

import by.leha.entity.booking.Booking;
import by.leha.web.dto.booking.BookingDto;
import by.leha.web.mappers.BookingMapper;

import java.util.List;

public interface BookingService {
    List<Booking> getAll();
    Booking getByID(Long id);
    boolean update(Long id, Booking booking);
    boolean delete(Long id);
    boolean createBooking(Booking booking);






}
