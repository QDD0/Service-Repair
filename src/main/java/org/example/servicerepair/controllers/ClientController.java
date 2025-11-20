package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.ClientDAO;
import org.example.servicerepair.source.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
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

        return "clients/clientList";
    }

    @GetMapping("/addClient")
    public String addClient(Model model) {
        model.addAttribute("addClient", new Client());
        return "clients/addClient";
    }

    @PostMapping()
    public String addClient(@ModelAttribute("addClient") Client client) {
        clientDAO.addClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String showById(Model model, @PathVariable("id") int id) {
        Client client = clientDAO.getById(id);

        if (client != null) {
            model.addAttribute("client", client);
            return "clients/showClient";
        }
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editClient(Model model, @PathVariable("id") int id) {
        Client client = clientDAO.getById(id);

        if (client != null) {
            model.addAttribute("client", client);
            return "clients/editClient";
        }
        return "redirect:/clients";
    }

    @PostMapping("/{id}/update")
    public String updateClient(@PathVariable("id") int id, @ModelAttribute("client") Client client) {
        clientDAO.editClient(id, client);

        return "redirect:/clients";
    }

    @PostMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") int id) {
        clientDAO.deleteClient(id);
        return "redirect:/clients";
    }
}
