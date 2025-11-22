package org.example.servicerepair.source;

public class Employ {
    private Integer id_employ;
    private String first_name;
    private String surname;
    private String last_name;
    private String position;
    private String phone;
    private String email;

    public Employ() {
    }

    public Employ(Integer id_employ, String first_name, String surname, String last_name, String position, String phone, String email) {
        this.id_employ = id_employ;
        this.first_name = first_name;
        this.surname = surname;
        this.last_name = last_name;
        this.position = position;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId_employ() {
        return id_employ;
    }

    public void setId_employ(Integer id_employ) {
        this.id_employ = id_employ;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
