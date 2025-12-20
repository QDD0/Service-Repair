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

        return jdbcTemplate.query("SELCET * FROM payments ORDER BY !!! LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Payments.class), size, offset);
    }

    public int countPayments() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM payments", Integer.class);
    }
}
