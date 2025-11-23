package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.EmployDAO;
import org.example.servicerepair.source.Employ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/addEmploy")
    public String addEmploy(Model model) {
        model.addAttribute("employ", new Employ());
        return "employs/addEmploy";
    }

    @PostMapping("")
    public String addEmploy(Employ employ) {
        employDAO.addEmploy(employ);
        return "redirect:/employs";
    }

    @GetMapping("/{id}")
    public String showById(Model model, @PathVariable("id") int id) {
        Employ employs = employDAO.getById(id);
        if (employs != null) {
            model.addAttribute("employs", employs);
            return "employs/showEmploy";
        }
        return "redirect:/employs";
    }

    @GetMapping("/{id}/edit")
    public String editEmploy(Model model, @PathVariable("id") int id) {
        Employ employs = employDAO.getById(id);

        if (employs != null) {
            model.addAttribute("employ", employs);
            return "employs/editEmploy";
        }
        return "redirect:/employs";
    }

    @PostMapping("/{id}/update")
    public String updateEmploy(@ModelAttribute("employ") Employ employ, @PathVariable("id") int id) {
        employDAO.editEmploy(id, employ);
        return "redirect:/employs";
    }

    @PostMapping("{id}/delete")
    public String deleteEmploy(@PathVariable("id") int id) {
        employDAO.deleteById(id);
        return "redirect:/employs";
    }
}
