package by.leha.web.dto.booking;

import by.leha.entity.room.enums.RoomClass;
import by.leha.entity.room.enums.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingCreateDto {
    private RoomClass roomClass;
    private Date starts_at;
    private Date ends_at;
    private int bookingAmount;
    private Long room_id;

}
