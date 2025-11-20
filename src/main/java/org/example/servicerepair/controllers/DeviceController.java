package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.DeviceDAO;
import org.example.servicerepair.source.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
