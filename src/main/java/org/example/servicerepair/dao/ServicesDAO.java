package org.example.servicerepair.dao;

import org.example.servicerepair.source.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicesDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ServicesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Services> getAllServices(int page, int size) {
        int offset = (page - 1) * size;

        return jdbcTemplate.query("SELECT * FROM services ORDER BY id_service LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Services.class), size, offset);
    }

    public int countServices() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM services", Integer.class);
    }
}
