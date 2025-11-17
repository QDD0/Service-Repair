package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.ClientDAO;
import org.example.servicerepair.source.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("")
public class ClientController {
    private ClientDAO clientDAO;

    @Autowired
    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        List<Client> clients = clientDAO.getClientsPage(page, size);
        int totalClients = clientDAO.countClients();
        int totalPages = (int) Math.ceil((double) totalClients / size);

        model.addAttribute("clientList", clients);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "client/clientList";
    }
}
