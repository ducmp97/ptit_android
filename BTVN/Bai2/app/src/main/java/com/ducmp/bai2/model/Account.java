package com.ducmp.bai2.model;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String address;
    private int age;
    private String email;
    private String username;
    private String password;

    public Account() {
    }

    public Account( String name, String phone, String address, int age, String email, String username, String password) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Account(int id, String name, String phone, String address, int age, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
