package by.leha.web.controllers;

import by.leha.entity.hotel.enums.RoomStatus;
import by.leha.web.dto.booking.BookingDto;
import by.leha.web.security.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/new")
    public String booking() {

return "booking/bookingNew";
}
@GetMapping("/new/confirm")
public String bookingConfirm(@RequestParam RoomStatus roomStatus,
            @RequestHeader("Authorization") String token
) {

    return "booking/bookingConfirm";
}


}
