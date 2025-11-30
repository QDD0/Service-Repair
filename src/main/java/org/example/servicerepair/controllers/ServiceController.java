package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.ServicesDAO;
import org.example.servicerepair.source.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/addService")
    public String addService(Model model) {
        model.addAttribute("addServices", new Services());
        return "services/addService";
    }

    @PostMapping("")
    public String addService(@ModelAttribute("addServices") Services services) {
        servicesDAO.addService(services);
        return "redirect:/services";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        Services services = servicesDAO.getByIdService(id);

        if (services != null) {
            model.addAttribute("services", services);
            return "services/showService";
        }
        return "redirect:/services";
    }

    @GetMapping("/{id}/edit")
    public String editService(Model model, @PathVariable("id") int id) {
        Services services = servicesDAO.getByIdService(id);

        if (services != null) {
            model.addAttribute("services", services);
            return "services/editService";
        }
        return "redirect:/services";
    }

    @PostMapping("/{id}/update")
    public String updateService(@ModelAttribute("services") Services services, @PathVariable("id") int id) {
        servicesDAO.editService(id, services);
        return "redirect:/services";
    }

    @PostMapping("/{id}/delete")
    public String deleteService(@PathVariable("id") int id) {
        servicesDAO.deleteService(id);
        return "redirect:/services";
    }
}
