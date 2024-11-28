package by.leha.web.mappers;

import by.leha.entity.booking.Booking;
import by.leha.mappers.Mapper;
import by.leha.web.dto.booking.BookingDto;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper implements Mapper<Booking, BookingDto> {
    @Override
    public BookingDto map(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .room(booking.getRoom())
                .amount(booking.getAmount())
                .date_in(booking.getDate_in())
                .date_out(booking.getDate_out())
                .status( booking.getStatus())
                .build();
    }

    @Override
    public Booking reverseMap(BookingDto bookingDto) {
        return Booking.builder()
                .id(bookingDto.getId())
                .amount(bookingDto.getAmount())
                .date_in(bookingDto.getDate_in())
                .date_out(bookingDto.getDate_out())
                .room(bookingDto.getRoom())
                .status(bookingDto.getStatus())
                .build();
    }
}
