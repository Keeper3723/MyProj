package by.leha.web.controllers.rest;

import by.leha.entity.booking.Booking;
import by.leha.services.booking.BookingService;
import by.leha.web.dto.booking.BookingDto;
import by.leha.web.mappers.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingRestController {
    private final BookingService bookingService;
    private final BookingMapper mapper;


    @GetMapping("")
    public ResponseEntity<List<?>> getAllBooking() {
        return ResponseEntity.ok(bookingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {

        return ResponseEntity.ok(bookingService.getByID(id));
    }

    @PostMapping
    public ResponseEntity<String> addBooking(@RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(bookingService.insert(mapper.reverseMap(bookingDto)) ? "booking was added" : "booking wasn't added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@RequestBody BookingDto bookingDto, @PathVariable Long id) {
        return ResponseEntity.ok(bookingService.update(id, mapper.reverseMap(bookingDto)) ? "booking was updated" : "booking wasn't updated");

    }

    @DeleteMapping("/{id}0")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.delete(id) ? "Booking was deleted" : "booking wasn't deleted");
    }
}
