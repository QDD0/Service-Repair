package org.example.servicerepair.source;

public class Services {
    private Integer id_service;
    private String name_service;
    private String description;
    private Integer price;

    public Services() {

    }

    public  Services(Integer id_service, String name_service, String description, Integer price) {
        this.id_service = id_service;
        this.name_service = name_service;
        this.description = description;
        this.price = price;
    }

    public Integer getId_service() {
        return id_service;
    }

    public void setId_service(Integer id_service) {
        this.id_service = id_service;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
