package org.example.servicerepair.controllers;

import org.example.servicerepair.dao.ClientDAO;
import org.example.servicerepair.dao.DeviceDAO;
import org.example.servicerepair.dao.PaymentsDAO;
import org.example.servicerepair.source.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentsDAO paymentsDAO;


    @Autowired
    public PaymentController(PaymentsDAO paymentsDAO, ClientDAO clientDAO, DeviceDAO deviceDAO) {
        this.paymentsDAO = paymentsDAO;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam("6") int size) {
        List<Payments> payments = paymentsDAO.getAllPayments(page, size);
        int totalPayment = paymentsDAO.countPayments();
        int totalPages = (int) Math.ceil((double) totalPayment / size);

        model.addAttribute("paymentsList", payments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "payments/paymentsList";
    }
}
