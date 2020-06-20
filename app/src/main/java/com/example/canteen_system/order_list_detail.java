package com.example.canteen_system;

public class order_list_detail {
    String order_name;
     String   order_price;

    public order_list_detail(String order_name, String order_price) {
        this.order_name = order_name;
        this.order_price = order_price;
    }

    public order_list_detail() {
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }
}
