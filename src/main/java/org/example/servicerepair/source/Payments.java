package org.example.servicerepair.source;

public class Payments {
    private Integer id_payment;
    private Integer order_id;
    private Double amount;
    private String payment_date;
    private String payment_method;

    public Payments() {

    }

    public Payments(Integer id_payment, Integer order_id, Double amount,  String payment_date, String payment_method) {
        this.id_payment = id_payment;
        this.order_id = order_id;
        this.amount = amount;
        this.payment_date = payment_date;
        this.payment_method = payment_method;
    }

    public Integer getId_payment() {
        return id_payment;
    }

    public void setId_payment(Integer id_payment) {
        this.id_payment = id_payment;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
