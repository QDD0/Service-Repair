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

    @GetMapping("/{id}")
    public String showStorage(Model model, @PathVariable("id") int id) {
        Storage storage = storageDAO.showStorage(id);

        if (storage != null) {
            model.addAttribute("storage", storage);
            model.addAttribute( "supplier", suppliesDAO.getByIdSupplier(storage.getSupplier_id()));
            return "storage/showStorage";
        }
        return "redirect:/storage";
    }

    @GetMapping("/{id}/edit")
    public String editStorage(Model model, @PathVariable("id") int id) {
        Storage storage = storageDAO.showStorage(id);
        if (storage != null) {
            model.addAttribute("storage", storage);
            model.addAttribute("supplier", suppliesDAO.getAllSuppliers());
            return "storage/editStorage";
        }
        return "redirect:/storage";
    }

    @PostMapping("/{id}/update")
    public String updateStorage(@ModelAttribute("storage") Storage storage, @PathVariable("id") int id, @RequestParam("supplier_id") int supplierId) {
        storage.setPart_id(id);
        storage.setSupplier_id(supplierId);

        storageDAO.updateStorage(storage, id);

        return "redirect:/storage";
    }

    @PostMapping("/{id}/delete")
    public String deleteStorage(@PathVariable("id") int id) {
        storageDAO.deleteStorage(id);
        return "redirect:/storage";
    }
}
