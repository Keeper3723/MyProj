package by.leha.web.mappers;

import by.leha.entity.booking.Booking;
import by.leha.entity.booking.enums.BookingStatus;
import by.leha.mappers.Mapper;
import by.leha.services.room.RoomService;
import by.leha.web.dto.booking.BookingCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookingCreateMapper implements Mapper<Booking, BookingCreateDto> {
    private final RoomService roomService;
    @Override
    public BookingCreateDto map(Booking booking) {
        return BookingCreateDto.builder()
                .room_id( booking.getRoom().getId())
                .bookingAmount(booking.getAmount())
                .ends_at(booking.getDate_out())
                .starts_at(booking.getDate_in())
                .roomClass(booking.getRoom().getRoomClass())
                .build();
    }

    @Override
    public Booking reverseMap(BookingCreateDto bookingCreateDto) {

        var room = roomService.getById(bookingCreateDto.getRoom_id());
        return Booking.builder()
                .status(BookingStatus.NOT_ACTIVE)
                .date_in(bookingCreateDto.getStarts_at())
                .room(room)
                .date_out(bookingCreateDto.getEnds_at())
                .amount(bookingCreateDto.getBookingAmount())
                .build();
    }
}
