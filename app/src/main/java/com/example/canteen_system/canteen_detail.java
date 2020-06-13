package com.example.canteen_system;

public class canteen_detail {
    String management_detail,handler,address;
    String mobile,no_of_worker;

    public canteen_detail(String management_detail, String handler, String address, String mobile, String no_of_worker) {
        this.management_detail = management_detail;
        this.handler = handler;
        this.address = address;
        this.mobile = mobile;
        this.no_of_worker = no_of_worker;
    }

    public canteen_detail() {
    }

    public String getManagement_detail() {
        return management_detail;
    }

    public void setManagement_detail(String management_detail) {
        this.management_detail = management_detail;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNo_of_worker() {
        return no_of_worker;
    }

    public void setNo_of_worker(String no_of_worker) {
        this.no_of_worker = no_of_worker;
    }
}
