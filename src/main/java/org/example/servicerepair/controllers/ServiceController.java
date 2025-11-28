package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.ServicesDAO;
import org.example.servicerepair.source.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {
    private final ServicesDAO servicesDAO;

    @Autowired
    public ServiceController(ServicesDAO servicesDAO) {
        this.servicesDAO = servicesDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        List<Services> services = servicesDAO.getAllServices(page, size);
        int totalServices = servicesDAO.countServices();
        int totalPage = (int) Math.ceil((double) totalServices / size);

        model.addAttribute("servicesList", services);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPage);

        return "services/servicesList";
    }
}
