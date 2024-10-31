package by.leha.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller("/booking")
@RequiredArgsConstructor
public class BookingController {

@GetMapping("/new")
    public String booking() {

return "booking/bookingNew";
}



}
