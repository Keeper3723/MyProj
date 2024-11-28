package by.leha.services.booking;

import by.leha.entity.booking.Booking;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BookingServiceImpl implements BookingService {
    @Override
    public List<Booking> getAll() {
        return List.of();
    }

    @Override
    public Booking getByID(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, Booking booking) {
        return false;
    }



    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean insert(Booking booking) {
        return false;
    }

}
