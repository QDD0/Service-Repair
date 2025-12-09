package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.SuppliesDAO;
import org.example.servicerepair.source.Suppliers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SuppliesController {
    private final SuppliesDAO suppliesDAO;

    @Autowired
    public SuppliesController(SuppliesDAO suppliesDAO) {
        this.suppliesDAO = suppliesDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        List<Suppliers> suppliers = suppliesDAO.getAllSuppliers(page, size);
        int totalSupplies = suppliesDAO.countAllSuppliers();
        int totalPages = (int) Math.ceil((double) totalSupplies / size);

        model.addAttribute("suppliersList", suppliers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "suppliers/suppliersList";
    }

    @GetMapping("/addSupplier")
    public String addSupplier(Model model) {
        model.addAttribute("addSupplier", new Suppliers());
        return "suppliers/addSupplier";
    }

    @PostMapping("")
    public String addSupplier(@ModelAttribute("addSupplier") Suppliers suppliers) {
        suppliesDAO.addSupplier(suppliers);
        return "redirect:/suppliers";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        Suppliers suppliers = suppliesDAO.getByIdSupplier(id);

        if (suppliers != null) {
            model.addAttribute("suppliers", suppliers);
            return "suppliers/showSupplier";
        }
        return "redirect:/suppliers";
    }

    @GetMapping("/{id}/edit")
    public String editById(@PathVariable("id") int id, Model model) {
        Suppliers suppliers = suppliesDAO.getByIdSupplier(id);

        if (suppliers != null) {
            model.addAttribute("suppliers", suppliers);
            return "suppliers/editSupplier";
        }
        return "redirect:/suppliers";
    }

    @PostMapping("/{id}/update")
    public String updateById(@PathVariable("id") int id, @ModelAttribute("suppliers") Suppliers suppliers) {
        suppliesDAO.editSupplier(id, suppliers);
        return "redirect:/suppliers";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") int id) {
        suppliesDAO.deleteSupplier(id);
        return "redirect:/suppliers";
    }
}
