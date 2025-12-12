package org.example.servicerepair.dao;

import org.example.servicerepair.source.Services;
import org.example.servicerepair.source.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StorageDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StorageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Storage> getAllStorage(int page, int size) {
        int offset = (page - 1) * size;

        return jdbcTemplate.query("SELECT * FROM storage ORDER BY part_id LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Storage.class), size, offset);
    }

    public int countAllStorage() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM storage", Integer.class);
    }

    public void addStorage(Storage storage) {
        jdbcTemplate.update(
                "INSERT INTO storage(part_name, part_code, price, count, supplier_id) VALUES(?, ?, ?, ?, ?)",
                storage.getPart_name(),
                storage.getPart_code(),
                storage.getPrice(),
                storage.getCount(),
                storage.getSupplier_id()
        );
    }
}
