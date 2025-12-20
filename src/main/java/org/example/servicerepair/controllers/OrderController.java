package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.ClientDAO;
import org.example.servicerepair.dao.DeviceDAO;
import org.example.servicerepair.dao.OrderDAO;
import org.example.servicerepair.source.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderDAO orderDAO;
    private final ClientDAO clientDAO;
    private final DeviceDAO deviceDAO;

    @Autowired
    public OrderController(OrderDAO orderDAO, ClientDAO clientDAO, DeviceDAO deviceDAO) {
        this.orderDAO = orderDAO;
        this.clientDAO = clientDAO;
        this.deviceDAO = deviceDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        List<Orders> orders = orderDAO.getAllOrders(page, size);
        int totalOrders = orderDAO.countOrders();
        int totalPages = (int) Math.ceil((double) totalOrders / size);

        model.addAttribute("ordersList", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "orders/ordersList";
    }

    @GetMapping("/addOrder")
    public String addOrder(Model model) {
        model.addAttribute("addOrder", new Orders());
        model.addAttribute("addClient", clientDAO.getAllClients());
        model.addAttribute("addDevice", deviceDAO.getAllDevices());

        return "orders/addOrder";
    }

    @PostMapping("")
    public String addOrder(@ModelAttribute("addOrder") Orders order, @RequestParam("client_id") int client_id, @RequestParam("device_id") int device_id) {
        order.setClient_id(client_id);
        order.setDevice_id(device_id);
        orderDAO.addOrder(order);

        return "redirect:/orders";
    }

    @GetMapping("/{id}")
    public String showOrder(Model model, @PathVariable("id") int id) {
        Orders orders = orderDAO.showOrder(id);

        if (orders != null) {
            model.addAttribute("order", orders);
            model.addAttribute("client", clientDAO.getById(orders.getClient_id()));
            model.addAttribute("device", deviceDAO.getById(orders.getDevice_id()));

            return "orders/showOrder";
        }
        return "redirect:/orders";
    }
}
