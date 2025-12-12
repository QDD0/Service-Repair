package org.example.servicerepair.service;

import org.example.servicerepair.source.Suppliers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SupplierService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Suppliers> getSupplier(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM suppliers WHERE supplier_id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Suppliers.class)
        );
    }

}
