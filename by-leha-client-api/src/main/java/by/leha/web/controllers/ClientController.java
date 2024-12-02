package by.leha.web.controllers;

import by.leha.services.client.ClientService;
import by.leha.web.security.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final JwtTokenUtils jwtUtils;
@GetMapping("")
    public String getClients(@RequestHeader("Authorization") String jwtToken, Model model) {

    model.addAttribute("client", clientService.getClientById(Long.parseLong( jwtUtils.getClientIdFromToken(jwtToken))));
    return "client/clientOffice";
}




}
