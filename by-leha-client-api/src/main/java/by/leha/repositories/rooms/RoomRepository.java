package by.leha.repositories.rooms;

import by.leha.entity.room.Room;
import by.leha.entity.room.enums.RoomStatus;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
   Optional< List<Room>> findAll();
    Optional<Room> findRoomById(Long id);
    boolean insertRoom(Room room);
    boolean updateRoomById(Long id,Room room);
    boolean deleteRoomById(Long id);

    Optional<List<Room>> getAllNotBookedRooms();

}
