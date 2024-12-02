package by.leha.web.controllers.rest;

import by.leha.entity.room.Room;
import by.leha.entity.room.enums.RoomClass;
import by.leha.services.room.RoomService;
import by.leha.web.dto.room.RoomDto;
import by.leha.web.mappers.RoomDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api1/rooms")
@RequiredArgsConstructor
public class RoomRestController {
    private final RoomService roomService;
    private final RoomDtoMapper mapper;


    @GetMapping(name = "")
    public ResponseEntity<List<?>> getAllRooms() {
        return   ResponseEntity.ok( roomService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id){
    return  ResponseEntity.ok(roomService.getById(id));
    }

    @GetMapping("/open")
    public ResponseEntity<List<?>> isOpen(){
        return ResponseEntity.ok( roomService.getNotBookedRooms());
    }

    @PostMapping
    public ResponseEntity<String> createRoom(@RequestBody RoomDto roomDto) {
return ResponseEntity.ok(roomService.create( mapper.map(roomDto))? "Room created":"Room not created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> uodateRoom(@RequestBody RoomDto roomDto, @PathVariable Long id){
       return ResponseEntity.ok(  roomService.update(id,mapper.map(roomDto))? "Room was updated": "Room wasn't updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteRoom(@PathVariable Long id){
       return ResponseEntity.ok( roomService.delete(id)? "Room was deleted":"Room wasn't deleted");

    }

}
