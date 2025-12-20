package org.example.servicerepair.source;

import java.time.LocalDate;

public class Orders {
    private Integer order_id;
    private Integer client_id;
    private Integer device_id;
    private String serial_number;
    private String problem;
    private LocalDate date_in;
    private LocalDate date_out;
    private String warranty;

    public Orders() {

    }

    public Orders(Integer order_id, Integer client_id, Integer device_id,
                  String serial_number, String problem, LocalDate date_in, LocalDate date_out, String warranty) {
        this.order_id = order_id;
        this.client_id = client_id;
        this.device_id = device_id;
        this.serial_number = serial_number;
        this.problem = problem;
        this.date_in = date_in;
        this.date_out = date_out;
        this.warranty = warranty;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public LocalDate getDate_in() {
        return date_in;
    }

    public void setDate_in(LocalDate date_in) {
        this.date_in = date_in;
    }

    public LocalDate getDate_out() {
        return date_out;
    }

    public void setDate_out(LocalDate date_out) {
        this.date_out = date_out;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}
