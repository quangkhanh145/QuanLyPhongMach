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
public class DichVu {
    private SimpleStringProperty Ma_DV;
    private SimpleStringProperty Ten_DV;
    private SimpleStringProperty Mo_Ta;
    private SimpleStringProperty Nhom;
    private Double Don_Gia;

    public DichVu() {
        this.Ma_DV = new SimpleStringProperty("");
        this.Ten_DV = new SimpleStringProperty("");
        this.Mo_Ta = new SimpleStringProperty("");
        this.Nhom = new SimpleStringProperty("");
        this.Don_Gia = 0.0;
    }

    public String getMa_DV() {
        return Ma_DV.get();
    }

    public void setMa_DV(String Ma_DV) {
        this.Ma_DV = new SimpleStringProperty(Ma_DV);
    }

    public String getTen_DV() {
        return Ten_DV.get();
    }

    public void setTen_DV(String Ten_DV) {
        this.Ten_DV = new SimpleStringProperty(Ten_DV);
    }

    public String getMo_Ta() {
        return Mo_Ta.get();
    }

    public void setMo_Ta(String Mo_Ta) {
        this.Mo_Ta = new SimpleStringProperty(Mo_Ta);
    }

    public String getNhom() {
        return Nhom.get();
    }

    public void setNhom(String Nhom) {
        this.Nhom = new SimpleStringProperty(Nhom);
    }

    public Double getDon_Gia() {
        return Don_Gia;
    }

    public void setDon_Gia(Double Don_Gia) {
        this.Don_Gia = Don_Gia;
    }
    
    
}
