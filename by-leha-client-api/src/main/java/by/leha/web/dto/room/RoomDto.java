package by.leha.web.dto.room;

import by.leha.entity.room.enums.RoomClass;
import by.leha.entity.room.enums.RoomStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private Long id;
    private String floor;
    private String number;
    private RoomStatus roomStatus_id;
    private RoomClass roomClass_id;

}
