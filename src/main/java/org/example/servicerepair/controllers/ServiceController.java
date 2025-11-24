package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.ServicesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/services")
public class ServiceController {
    private final ServicesDAO servicesDAO;

    @Autowired
    public ServiceController(ServicesDAO servicesDAO) {
        this.servicesDAO = servicesDAO;
    }
}
