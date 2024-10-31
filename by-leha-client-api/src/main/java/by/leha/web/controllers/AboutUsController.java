package by.leha.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/about-us")
public class AboutUsController {




    @GetMapping("")
    public String aboutUs() {


        return "showcase/about_us";
    }



}
