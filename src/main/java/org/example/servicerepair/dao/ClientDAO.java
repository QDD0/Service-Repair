package org.example.servicerepair.dao;

import org.example.servicerepair.source.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> getClientsPage(int page, int size) {
        int offset = (page - 1) * size;

        return jdbcTemplate.query(
                "SELECT * FROM client ORDER BY client_id LIMIT ? OFFSET ?", new BeanPropertyRowMapper<>(Client.class), size, offset
        );
    }

    public int countClients() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM client", Integer.class);
    }

}
