package by.leha.services.room;

import by.leha.entity.room.Room;

import java.util.List;

public interface RoomService  {
    List<Room> getAll();
    Room getById(Long id);
    boolean create(Room room);
    boolean update(Long id,Room room);
    boolean delete(Long id);
    List<Room> getNotBookedRooms();



}
