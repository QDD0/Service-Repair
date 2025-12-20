package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.ClientDAO;
import org.example.servicerepair.dao.DeviceDAO;
import org.example.servicerepair.dao.OrderDAO;
import org.example.servicerepair.dao.PaymentsDAO;
import org.example.servicerepair.source.Orders;
import org.example.servicerepair.source.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentsDAO paymentsDAO;
    private final OrderDAO orderDAO;

    @Autowired
    public PaymentController(PaymentsDAO paymentsDAO, OrderDAO orderDAO) {
        this.paymentsDAO = paymentsDAO;
        this.orderDAO = orderDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int size) {
        List<Payments> payments = paymentsDAO.getAllPayments(page, size);
        int totalPayment = paymentsDAO.countPayments();
        int totalPages = (int) Math.ceil((double) totalPayment / size);

        model.addAttribute("paymentsList", payments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "payments/paymentsList";
    }

    @GetMapping("/addPayment")
    public String addPayment(Model model) {
        model.addAttribute("addPayment", new Payments());
        model.addAttribute("addOrder", orderDAO.getAllOrders());

        return "payments/addPayment";
    }

    @PostMapping("")
    public String addPayment(@ModelAttribute("addPayment") Payments payments, @RequestParam("order_id") int order_id) {
        payments.setOrder_id(order_id);
        paymentsDAO.addPayment(payments);

        return "redirect:/payments";
    }

    @GetMapping("/{id}")
    public String showPayment(@PathVariable("id") int id, Model model) {
        Payments payments = paymentsDAO.showPayment(id);

        if (payments != null) {
            model.addAttribute("payment", payments);
            model.addAttribute("orders", orderDAO.showOrder(payments.getOrder_id()));

            return "payments/showPayment";
        }
        return "redirect:/payments";
    }

    @GetMapping("/{id}/edit")
    public String editPayment(@PathVariable("id") int id, Model model) {
        Payments payments = paymentsDAO.showPayment(id);

        if (payments != null) {
            model.addAttribute("payment", payments);
            model.addAttribute("orders", orderDAO.getAllOrders());

            return "payments/editPayment";
        }
        return "redirect:/payments";
    }

    @PostMapping("/{id}/update")
    public String updatePayment(@PathVariable("id") int id,
                                @ModelAttribute("payment") Payments payments,
                                @RequestParam("order_id") int order_id) {
        payments.setOrder_id(order_id);
        paymentsDAO.updatePayment(payments, id);
        return "redirect:/payments";
    }

    @PostMapping("/{id}/delete")
    public String deletePayment(@PathVariable("id") int id) {
        paymentsDAO.deletePayment(id);
        return "redirect:/payments";
    }
}
