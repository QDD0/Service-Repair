package org.example.servicerepair.source;

public class Storage {
    private Integer part_id;
    private String part_name;
    private String part_code;
    private Double price;
    private Integer count;
    private Integer supplier_id;

    public Storage() {

    }

    public Storage(Integer part_id, String part_name, Double price, Integer count) {
        this.part_id = part_id;
        this.part_name = part_name;
        this.price = price;
        this.count = count;
    }

    public Integer getPart_id() {
        return part_id;
    }

    public void setPart_id(Integer part_id) {
        this.part_id = part_id;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public String getPart_code() {
        return part_code;
    }

    public void setPart_code(String part_code) {
        this.part_code = part_code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
