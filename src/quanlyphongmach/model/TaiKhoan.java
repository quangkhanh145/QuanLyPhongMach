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
public class TaiKhoan {
    private SimpleStringProperty user_id;
    private SimpleStringProperty Ten_Dang_Nhap;
    private SimpleStringProperty Mat_Khau;
    private SimpleStringProperty Nhom;
    private SimpleStringProperty Ten;
    private SimpleStringProperty SDT;
    private SimpleStringProperty Email;

    public TaiKhoan() {
    }
    
    public String getUser_id() {
        return user_id.get();
    }

    public void setUser_id(String user_id) {
        this.user_id = new SimpleStringProperty(user_id);
    }

    public String getTen_Dang_Nhap() {
        return Ten_Dang_Nhap.get();
    }

    public void setTen_Dang_Nhap(String Ten_Dang_Nhap) {
        this.Ten_Dang_Nhap = new SimpleStringProperty(Ten_Dang_Nhap);
    }

    public String getMat_Khau() {
        return Mat_Khau.get();
    }

    public void setMat_Khau(String Mat_Khau) {
        this.Mat_Khau = new SimpleStringProperty(Mat_Khau);
    }

    public String getNhom() {
        return Nhom.get();
    }

    public void setNhom(String Nhom) {
        this.Nhom = new SimpleStringProperty(Nhom);
    }

    public String getTen() {
        return Ten.get();
    }

    public void setTen(String Ten) {
        this.Ten = new SimpleStringProperty(Ten);
    }

    public String getSDT() {
        return SDT.get();
    }

    public void setSDT(String SDT) {
        this.SDT = new SimpleStringProperty(SDT);
    }

    public String getEmail() {
        return Email.get();
    }

    public void setEmail(String Email) {
        this.Email = new SimpleStringProperty(Email);
    }
    
}
