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
                "SELECT * FROM client ORDER BY client_id LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Client.class), size, offset
        );
    }

    public List<Client> getAllClients() {
        return jdbcTemplate.query("SELECT * FROM client", new BeanPropertyRowMapper<>(Client.class));
    }

    public void addClient(Client client) {
        jdbcTemplate.update("INSERT INTO client(first_name, surname, last_name, phone, email, address) VALUES  (?, ?, ?, ?, ?, ?)",
                client.getFirst_name(), client.getSurname(), client.getLast_name(), client.getPhone(), client.getEmail(), client.getAddress());
    }

    public int countClients() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM client", Integer.class);
    }

    public Client getById(int id) {
        return jdbcTemplate.query("SELECT * FROM client WHERE client_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Client.class)).stream().findAny().orElse(null);
    }

    public void editClient(int id, Client client) {
        jdbcTemplate.update("UPDATE client SET first_name = ?, surname = ?, last_name = ?, phone = ?, email = ?, address = ? WHERE client_id = ? ",
                client.getFirst_name(), client.getSurname(), client.getLast_name(), client.getPhone(), client.getEmail(), client.getAddress(), id);
    }

    public void deleteClient(int id) {
        jdbcTemplate.update("DELETE FROM client WHERE client_id = ?", id);
    }
}
