package com.codeup.adlister.models;
import java.io.Serializable;

public class Profile implements Serializable{
    private String first_name;
    private String last_name;
    private String phone;
    private String address;
    private long user_id;

    public Profile(){
    }

    public Profile(String first_name, String last_name, String phone, String address, long user_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
