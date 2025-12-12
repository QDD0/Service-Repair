package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.StorageDAO;
import org.example.servicerepair.dao.SuppliesDAO;
import org.example.servicerepair.source.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/storage")
public class StorageController {
    private final StorageDAO storageDAO;
    private final SuppliesDAO suppliesDAO;

    @Autowired
    public StorageController(StorageDAO storageDAO, SuppliesDAO suppliesDAO) {
        this.storageDAO = storageDAO;
        this.suppliesDAO = suppliesDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        List<Storage> storages = storageDAO.getAllStorage(page, size);
        int totalStorage = storageDAO.countAllStorage();
        int totalPages = (int) Math.ceil((double) totalStorage / (double) size);

        model.addAttribute("storagesList", storages);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "storage/storageList";
    }

    @GetMapping("/addStorage")
    public String addStorage(Model model) {
        model.addAttribute("addStorage", new Storage());
        model.addAttribute("addSupplier", suppliesDAO.getAllSuppliers());
        return "storage/addStorage";
    }

    @PostMapping("")
    public String addStorage(
            @ModelAttribute("addStorage") Storage storage,
            @RequestParam("supplier_id") int supplierId) {

        storage.setSupplier_id(supplierId);
        storageDAO.addStorage(storage);

        return "redirect:/storage";
    }
}
