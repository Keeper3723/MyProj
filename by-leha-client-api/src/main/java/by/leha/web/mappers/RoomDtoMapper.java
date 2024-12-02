package by.leha.web.mappers;

import by.leha.entity.room.Room;
import by.leha.entity.room.enums.RoomClass;
import by.leha.entity.room.enums.RoomStatus;
import by.leha.mappers.Mapper;
import by.leha.web.dto.room.RoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoMapper implements Mapper<RoomDto, Room> {
    @Override
    public Room map(RoomDto roomDto) {
        return Room.builder()
                .id(roomDto.getId())
                .floor(roomDto.getFloor())
                .number(roomDto.getNumber())
                .roomClass(RoomClass.BUDGET)
                .status( RoomStatus.OPEN)
                .build();
    }

    @Override
    public RoomDto reverseMap(Room room) {
            return RoomDto.builder()
                    .id(room.getId())
                    .floor(room.getFloor())
                    .number(room.getNumber())
                    .roomClass_id(room.getRoomClass())
                    .roomStatus_id(room.getStatus())
                    .build();
    }
}
