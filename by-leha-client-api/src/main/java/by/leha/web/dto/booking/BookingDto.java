package by.leha.web.dto.booking;


import by.leha.entity.booking.enums.BookingStatus;
import by.leha.entity.room.Room;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {

    private Long id;


    private Integer amount;


    private Room room;

    private Long client_id;

    private Date date_in;

    private Date date_out;

    private BookingStatus status;




}
