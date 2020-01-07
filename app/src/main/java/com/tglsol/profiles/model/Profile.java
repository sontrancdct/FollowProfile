package com.tglsol.profiles.model;

import java.util.ArrayList;

public class Profile {
    private String _id;
    private String name;
    private String phone;
    private String address;
    private ArrayList<Friend> friends;


    public Profile(String _id, String name, String phone, String address, ArrayList<Friend> friends) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.friends = friends;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }
}
