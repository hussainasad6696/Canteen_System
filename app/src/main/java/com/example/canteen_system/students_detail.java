package com.example.canteen_system;

public class students_detail {
    String fname,semester,address;
    String mobile,dob;

    public students_detail(String fname, String semester, String address, String mobile, String dob) {
        this.fname = fname;
        this.semester = semester;
        this.address = address;
        this.mobile = mobile;
        this.dob = dob;
    }

    public students_detail() {
    }

    public String getfName() {
        return fname;
    }

    public void setfName(String fname) {
        this.fname = fname;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
