package com.ducmp.bai1;

public class QuanCo {
    private int id;
    private String ten;
    private String img;
    private String moTa;

    public QuanCo() {
    }

    public QuanCo(int id, String ten, String img, String moTa) {
        this.id = id;
        this.ten = ten;
        this.img = img;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
