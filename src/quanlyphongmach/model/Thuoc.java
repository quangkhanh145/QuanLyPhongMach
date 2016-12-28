/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyphongmach.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Quang Khanh
 */
public class Thuoc {
    private SimpleStringProperty MaThuoc;
    private SimpleStringProperty TenThuoc;
    private SimpleStringProperty DonVi;
    private double GiaBan;
    private int TonKho;
    private int LieuLuong;
    private SimpleStringProperty CachDung;
    private SimpleStringProperty Nhom;

    public Thuoc() {
        this.MaThuoc = new SimpleStringProperty(null);
        this.TenThuoc = new SimpleStringProperty(null);
        this.DonVi = new SimpleStringProperty(null);
        this.GiaBan = 0;
        this.TonKho = 0;
        this.LieuLuong = 0;
        this.CachDung = new SimpleStringProperty(null);
        this.Nhom = new SimpleStringProperty(null);
    }

    
    public Thuoc(String MaThuoc, String TenThuoc, String DonVi, float GiaBan, int TonKho, int LieuLuong, String CachDung, String Nhom) {
        this.MaThuoc = new SimpleStringProperty(MaThuoc);
        this.TenThuoc = new SimpleStringProperty(TenThuoc);
        this.DonVi = new SimpleStringProperty(DonVi);
        this.GiaBan = GiaBan;
        this.TonKho = TonKho;
        this.LieuLuong = LieuLuong;
        this.CachDung = new SimpleStringProperty(CachDung);
        this.Nhom = new SimpleStringProperty(Nhom);
    }

    public String getMaThuoc() {
        return MaThuoc.getValue();
    }

    public void setMaThuoc(String MaThuoc) {
        this.MaThuoc.set(MaThuoc);
    }

    public String getTenThuoc() {
        return TenThuoc.getValue();
    }

    public void setTenThuoc(String TenThuoc) {
        this.TenThuoc.set(TenThuoc);
    }

    public String getDonVi() {
        return DonVi.getValue();
    }

    public void setDonVi(String DonVi) {
        this.DonVi.set(DonVi);
    }

    public double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getTonKho() {
        return TonKho;
    }

    public void setTonKho(int TonKho) {
        this.TonKho = TonKho;
    }

    public int getLieuLuong() {
        return LieuLuong;
    }

    public void setLieuLuong(int LieuLuong) {
        this.LieuLuong = LieuLuong;
    }

    public String getCachDung() {
        return this.CachDung.getValue();
    }

    public void setCachDung(String CachDung) {
        this.CachDung.set(CachDung);
    }

    public String getNhom() {
        return Nhom.getValue();
    }

    public void setNhom(String Nhom) {
        this.Nhom.set(Nhom);
    }
    
    
}
