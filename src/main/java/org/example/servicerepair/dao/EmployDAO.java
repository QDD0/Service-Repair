package org.example.servicerepair.dao;

import org.example.servicerepair.source.Device;
import org.example.servicerepair.source.Employ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employ> getAllEmploys(int page, int size) {
        int offset = (page - 1) * size;

        return jdbcTemplate.query("SELECT * FROM employ ORDER BY id_employ LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Employ.class), size, offset);
    }

    public int countEmploys() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employ", Integer.class);
    }
}
