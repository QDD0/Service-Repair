package org.example.servicerepair.dao;

import org.example.servicerepair.source.Suppliers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuppliesDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SuppliesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Suppliers> getAllSuppliers() {
        return jdbcTemplate.query("SELECT * FROM suppliers", new BeanPropertyRowMapper<>(Suppliers.class));
    }

    public List<Suppliers> getAllSuppliers(int page, int size) {
        int offset = (page - 1) * size;

        return jdbcTemplate.query("SELECT * FROM suppliers ORDER BY supplier_id LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Suppliers.class), size, offset);
    }

    public int countAllSuppliers() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM suppliers", Integer.class);
    }

    public int addSupplier(Suppliers supplier) {
        return jdbcTemplate.update("INSERT INTO suppliers(name_company, first_name, surname, last_name, phone, address, email) VALUES  ( ?,  ?, ? , ?, ?, ? , ?)",
                supplier.getName_company(), supplier.getFirst_name(), supplier.getSurname(), supplier.getLast_name(), supplier.getPhone(), supplier.getAddress(), supplier.getEmail());
    }

    public Suppliers getByIdSupplier(int id) {
        return jdbcTemplate.query("SELECT * FROM suppliers WHERE supplier_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Suppliers.class)).stream().findFirst().orElse(null);
    }

    public void editSupplier(int id, Suppliers supplier) {
        jdbcTemplate.update("UPDATE suppliers SET name_company =?, first_name= ?, surname = ?, last_name = ?, phone = ?, address = ?, email = ? WHERE supplier_id = ?",
                supplier.getName_company(), supplier.getFirst_name(), supplier.getSurname(), supplier.getLast_name(), supplier.getPhone(), supplier.getAddress(), supplier.getEmail(), id);
    }

    public void deleteSupplier(int id) {
        jdbcTemplate.update("DELETE FROM suppliers WHERE supplier_id = ?", id);
    }
}
