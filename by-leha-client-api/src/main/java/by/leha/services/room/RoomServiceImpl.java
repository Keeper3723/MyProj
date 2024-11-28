package by.leha.services.room;

import by.leha.entity.hotel.Room;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements  RoomService{

    @Override
    public List<Room> getAll() {
        return List.of();
    }

    @Override
    public Room getById(Long id) {
        return null;
    }

    @Override
    public boolean create(Room room) {
        return false;
    }

    @Override
    public boolean update(Room room) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
