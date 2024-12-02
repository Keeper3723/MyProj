package by.leha.services.booking;

import by.leha.entity.booking.Booking;
import by.leha.repositories.booking.BookingRepository;
import by.leha.services.room.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final RoomService roomService;
    private  final BookingRepository bookingRepository;
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
    @Transactional
    public boolean createBooking(Booking booking) {
return false;
    }

}
