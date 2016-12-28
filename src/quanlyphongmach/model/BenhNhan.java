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
public class BenhNhan {
    private SimpleStringProperty MaBN;
    private SimpleStringProperty Ten;
    private SimpleStringProperty Ngay_Sinh;
    private SimpleStringProperty Gioi_Tinh;
    private SimpleStringProperty SDT;
    private SimpleStringProperty Dia_Chi;
    private SimpleStringProperty Nhom;
    
    public BenhNhan(){
        this.Ten = new SimpleStringProperty(null);
        this.Gioi_Tinh = new SimpleStringProperty(null);
        this.MaBN = new SimpleStringProperty(null);
        this.Ngay_Sinh = new SimpleStringProperty(null);
        this.SDT = new SimpleStringProperty(null);
        this.Dia_Chi = new SimpleStringProperty(null);
        this.Nhom = new SimpleStringProperty(null);
    }
    public BenhNhan(String Ten, String Gioi_Tinh)
    {
        this.Ten = new SimpleStringProperty(Ten);
        this.Gioi_Tinh = new SimpleStringProperty(Gioi_Tinh);
        this.MaBN = new SimpleStringProperty(null);
        this.Ngay_Sinh = new SimpleStringProperty(null);
        this.SDT = new SimpleStringProperty(null);
        this.Dia_Chi = new SimpleStringProperty(null);
        this.Nhom = new SimpleStringProperty(null);
    }
    public BenhNhan(String MaBN, String Ten,String Ngay_Sinh, String Gioi_Tinh, String SDT, String Dia_Chi, String Nhom){
        this.Ten = new SimpleStringProperty(Ten);
        this.Gioi_Tinh = new SimpleStringProperty(Gioi_Tinh);
        this.MaBN = new SimpleStringProperty(MaBN);
        this.Ngay_Sinh = new SimpleStringProperty(Ngay_Sinh);
        this.SDT = new SimpleStringProperty(SDT);
        this.Dia_Chi = new SimpleStringProperty(Dia_Chi);
        this.Nhom = new SimpleStringProperty(Nhom);
    }
    
    public String getMaBN()
    {
        return MaBN.get();
    }
    public String getTen()
    {
        return Ten.get();
    }
    public String getNgaySinh()
    {
        return Ngay_Sinh.get();
    }
    public String getGioiTinh()
    {
        return Gioi_Tinh.get();
    }
    public String getSDT()
    {
        return SDT.get();
    }
    public String getDiaChi()
    {
        return Dia_Chi.get();
    }
    public String getNhom()
    {
        return Nhom.get();
    }
    
    public void setMaBN(String MaBN)
    {
        this.MaBN.setValue(MaBN);
    }
    public void setTen(String Ten)
    {
        this.Ten.setValue(Ten);
    }
    public void setNgaySinh(String Ngay_Sinh)
    {
        this.Ngay_Sinh.setValue(Ngay_Sinh);
    }
    public void setGioiTinh(String Gioi_Tinh)
    {
        this.Gioi_Tinh.setValue(Gioi_Tinh);
    }
    public void setSDT(String SDT)
    {
        this.SDT.setValue(SDT);
    }
    public void setDiaChi(String Dia_Chi)
    {
        this.Dia_Chi.setValue(Dia_Chi);
    }
    public void setNhom(String Nhom)
    {
        this.Nhom.setValue(Nhom);
    }
}
