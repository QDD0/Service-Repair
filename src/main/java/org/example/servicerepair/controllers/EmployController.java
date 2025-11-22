package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.EmployDAO;
import org.example.servicerepair.source.Employ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employs")
public class EmployController {
    private final EmployDAO employDAO;

    @Autowired
    public EmployController(EmployDAO employDAO) {
        this.employDAO = employDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        List<Employ> employList = employDAO.getAllEmploys(page, size);
        int totalEmploys = employDAO.countEmploys();
        int totalPages = (int) Math.ceil((double) totalEmploys / size);

        model.addAttribute("employList", employList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "employs/employList";
    }
}
