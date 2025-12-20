package org.example.servicerepair.dao;

import org.example.servicerepair.source.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentsDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Payments> getAllPayments(int page, int size) {
        int offset = (page - 1) * size;

        return jdbcTemplate.query("SELECT * FROM payments ORDER BY id_payment LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Payments.class), size, offset);
    }

    public int countPayments() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM payments", Integer.class);
    }

    public void addPayment(Payments payment) {
        jdbcTemplate.update("INSERT INTO payments(order_id, amount, payment_date, payment_method) VALUES (?, ?, ?, ?)",
                payment.getOrder_id(), payment.getAmount(), payment.getPayment_date(), payment.getPayment_method());
    }

    public Payments showPayment(int id) {
        return jdbcTemplate.query("SELECT * FROM payments WHERE id_payment = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Payments.class)).stream().findAny().orElse(null);
    }

    public void updatePayment(Payments payment, int id) {
        jdbcTemplate.update("UPDATE payments SET order_id=?, amount=?, payment_date=?, payment_method=? WHERE id_payment=?",
                payment.getOrder_id(), payment.getAmount(), payment.getPayment_date(), payment.getPayment_method(), id);
    }

    public void deletePayment(int id) {
        jdbcTemplate.update("DELETE FROM payments WHERE id_payment = ?", id);
    }
}
