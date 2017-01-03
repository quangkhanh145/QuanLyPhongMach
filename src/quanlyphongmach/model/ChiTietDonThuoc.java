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
public class ChiTietDonThuoc {
//    private SimpleStringProperty Ma_Don_Thuoc;
    private SimpleStringProperty Ma_Thuoc;
    private int So_Luong;
    private Double Gia_Ban;
    private SimpleStringProperty Don_Vi;
    private SimpleStringProperty Cach_Dung;
    private SimpleStringProperty Ten_Thuoc;
    public ChiTietDonThuoc(String Ma_Don_Thuoc, String Ma_Thuoc, int So_Luong, String DonVi,Double Gia_Ban, String Cach_Dung) {
//        this.Ma_Don_Thuoc = new SimpleStringProperty(Ma_Don_Thuoc);
        this.Ma_Thuoc = new SimpleStringProperty(Ma_Thuoc);
        this.So_Luong = So_Luong;
        this.Gia_Ban = Gia_Ban;
        this.Cach_Dung = new SimpleStringProperty(Cach_Dung);
        this.Don_Vi = new SimpleStringProperty(DonVi);
    }
    public ChiTietDonThuoc()
    {
//        this.Ma_Don_Thuoc = new SimpleStringProperty(null);
        this.Ma_Thuoc = new SimpleStringProperty(null);
        this.So_Luong = 0;
        this.Gia_Ban = 0.0;
        this.Cach_Dung = new SimpleStringProperty(null);
        this.Ten_Thuoc = new SimpleStringProperty(null);
        this.Don_Vi = new SimpleStringProperty(null);
    }

//    public String getMa_Don_Thuoc() {
//        return Ma_Don_Thuoc.get();
//    }

//    public void setMa_Don_Thuoc(String Ma_Don_Thuoc) {
//        this.Ma_Don_Thuoc = new SimpleStringProperty(Ma_Don_Thuoc);
//    }

    public String getMa_Thuoc() {
        return Ma_Thuoc.get();
    }

    public void setMa_Thuoc(String Ma_Thuoc) {
        this.Ma_Thuoc = new SimpleStringProperty(Ma_Thuoc);
    }

    public int getSo_Luong() {
        return So_Luong;
    }

    public void setSo_Luong(int So_Luong) {
        this.So_Luong = So_Luong;
    }

    public Double getGia_Ban() {
        return Gia_Ban;
    }

    public void setGia_Ban(Double Gia_Ban) {
        this.Gia_Ban = Gia_Ban;
    }

    public String getCach_Dung() {
        return Cach_Dung.get();
    }

    public void setCach_Dung(String Cach_Dung) {
        this.Cach_Dung = new SimpleStringProperty(Cach_Dung);
    }
    public String getTen_Thuoc() {
        return Ten_Thuoc.get();
    }

    public void setTen_Thuoc(String Ten_Thuoc) {
        this.Ten_Thuoc = new SimpleStringProperty(Ten_Thuoc);
    }    
    public String getDon_Vi() {
        return Don_Vi.get();
    }

    public void setDon_Vi(String DonVi) {
        this.Don_Vi = new SimpleStringProperty(DonVi);
    }    
}
