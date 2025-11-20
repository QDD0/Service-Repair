package org.example.servicerepair.source;

public class Device {
    private Integer device_id;
    private String type;
    private String brand;
    private String model;
    private String description;

    public Device() {

    }

    public Device(Integer device_id, String type, String brand, String model, String description) {
        this.device_id = device_id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.description = description;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
