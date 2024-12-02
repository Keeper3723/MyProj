package by.leha.web.mappers;

import by.leha.entity.booking.Booking;
import by.leha.mappers.Mapper;
import by.leha.services.client.ClientService;
import by.leha.web.dto.booking.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper implements Mapper<Booking, BookingDto> {
    private final ClientService clientService;

    @Override
    public BookingDto map(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .room(booking.getRoom())
                .amount(booking.getAmount())
                .date_in(booking.getDate_in())
                .date_out(booking.getDate_out())
                .status( booking.getStatus())
                .client_id(booking.getClient_id().getId())
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
