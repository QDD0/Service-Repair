package org.example.servicerepair.dao;

import org.example.servicerepair.source.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Orders> getAllOrders(int page, int size) {
        int offset = (page - 1) * size;

        return jdbcTemplate.query("SELECT * FROM orders ORDER BY order_id LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Orders.class), size, offset);
    }

    public List<Orders> getAllOrders() {
        return jdbcTemplate.query("SELECT * FROM orders", new BeanPropertyRowMapper<>(Orders.class));
    }

    public int countOrders() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM orders", Integer.class);
    }

    public void addOrder(Orders order) {
        jdbcTemplate.update(
                "INSERT INTO orders(client_id, device_id, serial_number, problem, date_in, date_out, warranty) VALUES(?,?,?,?,?,?,?)",
                order.getClient_id(), order.getDevice_id(), order.getSerial_number(), order.getProblem(), order.getDate_in(), order.getDate_out(), order.getWarranty()
        );
    }

    public Orders showOrder(int id) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE order_id = ? ", new Object[]{id},
                new BeanPropertyRowMapper<>(Orders.class)).stream().findAny().orElse(null);
    }

    public void updateOrder(Orders order, int id) {
        jdbcTemplate.update("UPDATE orders SET client_id=?, device_id=?, serial_number=?, problem=?, date_in=?, date_out=?, warranty=? WHERE order_id =?",
                order.getClient_id(), order.getDevice_id(), order.getSerial_number(), order.getProblem(), order.getDate_in(), order.getDate_out(), order.getWarranty(), id);
    }

    public void deleteOrder(int id) {
        jdbcTemplate.update("DELETE FROM orders WHERE order_id = ?", id);
    }
}
