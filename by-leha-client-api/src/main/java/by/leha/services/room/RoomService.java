package by.leha.services.room;

import by.leha.entity.hotel.Room;

import java.util.List;

public interface RoomService  {
    List<Room> getAll();
    Room getById(Long id);
    boolean create(Room room);
    boolean update(Room room);
    boolean delete(Long id);



}
