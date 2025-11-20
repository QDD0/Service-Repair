package org.example.servicerepair.dao;

import org.example.servicerepair.source.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DeviceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Device> getDevicesPage(int page, int size) {
        int offset = (page - 1) * size;

        return jdbcTemplate.query("SELECT * FROM device ORDER BY device_id LIMIT ? OFFSET ?",
                new BeanPropertyRowMapper<>(Device.class), size, offset);
    }

    public int countDevices() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM device", Integer.class);
    }
}
