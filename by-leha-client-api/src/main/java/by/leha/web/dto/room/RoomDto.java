package by.leha.web.dto.room;

import by.leha.entity.hotel.enums.RoomClass;
import by.leha.entity.hotel.enums.RoomStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
