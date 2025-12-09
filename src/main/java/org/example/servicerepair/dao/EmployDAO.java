package org.example.servicerepair.dao;

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

    public void addEmploy(Employ employ) {
        jdbcTemplate.update("INSERT INTO employ(first_name,surname, last_name, position, phone, email) VALUES (?, ?, ?, ?, ?, ?)",
                employ.getFirst_name(), employ.getSurname(), employ.getLast_name(), employ.getPosition(), employ.getPhone(), employ.getEmail());
    }

    public void editEmploy(int id, Employ employ) {
        jdbcTemplate.update("UPDATE employ SET first_name=?, surname=?, last_name=?, position=?, phone=?, email=? WHERE id_employ=?",
                employ.getFirst_name(), employ.getSurname(), employ.getLast_name(), employ.getPosition(), employ.getPhone(), employ.getEmail(), id);
    }

    public Employ getById(int id) {
        return jdbcTemplate.query("SELECT * FROM employ WHERE id_employ = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Employ.class)).stream().findAny().orElse(null);
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM employ WHERE id_employ = ?", id);
    }
}
