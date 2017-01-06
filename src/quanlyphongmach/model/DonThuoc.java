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
public class DonThuoc {
    private SimpleStringProperty Ma_Don_Thuoc;
    private SimpleStringProperty Ten_Benh_Nhan;
    private int Tuoi;
    private SimpleStringProperty Chan_Doan;
    private Double Tong_Tien;
    private SimpleStringProperty Nguoi_Lap;
    private SimpleStringProperty Ngay_Lap;

    public DonThuoc() {
        this.Ma_Don_Thuoc = new SimpleStringProperty("");
        this.Ten_Benh_Nhan = new SimpleStringProperty("");
        this.Tuoi = 0;
        this.Chan_Doan = new SimpleStringProperty("");
        this.Tong_Tien = 0.0;
        this.Nguoi_Lap = new SimpleStringProperty("");
        this.Ngay_Lap = new SimpleStringProperty("");
    }

    public String getMa_Don_Thuoc() {
        return Ma_Don_Thuoc.get();
    }

    public void setMa_Don_Thuoc(String Ma_Don_Thuoc) {
        this.Ma_Don_Thuoc = new SimpleStringProperty(Ma_Don_Thuoc);
    }

    public String getTen_Benh_Nhan() {
        return Ten_Benh_Nhan.get();
    }

    public void setTen_Benh_Nhan(String Ten_Benh_Nhan) {
        this.Ten_Benh_Nhan = new SimpleStringProperty(Ten_Benh_Nhan);
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int Tuoi) {
        this.Tuoi = Tuoi;
    }

    public String getChan_Doan() {
        return Chan_Doan.get();
    }

    public void setChan_Doan(String Chan_Doan) {
        this.Chan_Doan = new SimpleStringProperty(Chan_Doan);
    }

    public Double getTong_Tien() {
        return Tong_Tien;
    }

    public void setTong_Tien(Double Tong_Tien) {
        this.Tong_Tien = Tong_Tien;
    }

    public String getNguoi_Lap() {
        return Nguoi_Lap.get();
    }

    public void setNguoi_Lap(String Nguoi_Lap) {
        this.Nguoi_Lap = new SimpleStringProperty(Nguoi_Lap);
    }

    public String getNgay_Lap() {
        return Ngay_Lap.get();
    }

    public void setNgay_Lap(String Ngay_Lap) {
        this.Ngay_Lap = new SimpleStringProperty(Ngay_Lap);
    }
    
}
