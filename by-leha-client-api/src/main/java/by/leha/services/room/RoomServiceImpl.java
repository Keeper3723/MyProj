package by.leha.services.room;

import by.leha.entity.room.Room;
import by.leha.exceptions.ResourceNotFoundException;
import by.leha.repositories.rooms.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements  RoomService{
    private final RoomRepository roomRepository;
    @Override
    public List<Room> getAll() {
        return roomRepository.findAll().orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Room getById(Long id) {
       return roomRepository.findRoomById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public boolean create(Room room) {
        return roomRepository.insertRoom(room);
    }

    @Override
    public boolean update(Long id,Room room) {
        return roomRepository.updateRoomById(id, room);
    }

    @Override
    public boolean delete(Long id) {
        return roomRepository.deleteRoomById(id);
    }

    @Override
    public List<Room> getNotBookedRooms() {
        return roomRepository.getAllNotBookedRooms().orElseThrow(ResourceNotFoundException::new);
    }
}
