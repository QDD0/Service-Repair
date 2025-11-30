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

    public void addService(Services service) {
        jdbcTemplate.update("INSERT INTO services(name_service, description, price) VALUES (?, ?, ?)",
                service.getName_service(), service.getDescription(), service.getPrice());
    }

    public Services getByIdService(int id) {
        return jdbcTemplate.query("SELECT * FROM services WHERE id_service = ?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Services.class))
                .stream().findFirst().orElse(null);
    }

    public void editService(int id, Services service) {
        jdbcTemplate.update("UPDATE services SET name_service = ?,description = ?, price = ? WHERE id_service = ?",
                service.getName_service(), service.getDescription(), service.getPrice(), id);
    }

    public void deleteService(int id) {
        jdbcTemplate.update("DELETE FROM services WHERE id_service = ?", id);
    }
}
