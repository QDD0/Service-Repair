package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.DeviceDAO;
import org.example.servicerepair.source.Client;
import org.example.servicerepair.source.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceDAO deviceDAO;

    @Autowired
    public DeviceController(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        List<Device> deviceList = deviceDAO.getDevicesPage(page, size);
        int totalDevices = deviceDAO.countDevices();
        int totalPages = (int) Math.ceil((double) totalDevices / size);

        model.addAttribute("deviceList", deviceList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "devices/devicesList";
    }

    @GetMapping("/addDevice")
    public String addDevice(Model model) {
        model.addAttribute("addDevice", new Device());
        return "devices/addDevice";
    }

    @PostMapping("")
    public String addDevice(Device device, @ModelAttribute("addDevice") Model model) {
        deviceDAO.addDevice(device);
        return "redirect:/devices";
    }

    @GetMapping("/{id}")
    public String showById(Model model, @PathVariable("id") int id) {
        Device device = deviceDAO.getById(id);

        if (device != null) {
            model.addAttribute("device", device);
            return "devices/showDevice";
        }
        return "redirect:/devices";
    }

    @GetMapping("/{id}/edit")
    public String editDevice(Model model, @PathVariable("id") int id) {
        Device device = deviceDAO.getById(id);

        if (device != null) {
            model.addAttribute("device", device);
            return "devices/editDevice";
        }
        return "redirect:/devices";
    }

    @PostMapping("/{id}/update")
    public String updateDevice(@PathVariable("id") int id, @ModelAttribute("device") Device device) {
        deviceDAO.editDevice(id, device);
        return "redirect:/devices";
    }

    @PostMapping("/{id}/delete")
    public String deleteDevice(@PathVariable("id") int id) {
        deviceDAO.deleteDevice(id);
        return "redirect:/devices";
    }
}
