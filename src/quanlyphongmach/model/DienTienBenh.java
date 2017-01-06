package quanlyphongmach.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Quang Khanh
 */
public class DienTienBenh {
    private SimpleStringProperty MaBN;
    private SimpleStringProperty GhiChu;
    private SimpleStringProperty NgayKham;

    public DienTienBenh() {
        this.MaBN = new SimpleStringProperty("");
        this.GhiChu = new SimpleStringProperty("");
        this.NgayKham = new SimpleStringProperty("");
    }

    public String getMaBN() {
        return MaBN.get();
    }

    public void setMaBN(String MaBN) {
        this.MaBN = new SimpleStringProperty(MaBN);
    }

    public String getGhiChu() {
        return GhiChu.get();
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = new SimpleStringProperty(GhiChu);
    }

    public String getNgayKham() {
        return NgayKham.get();
    }

    public void setNgayKham(String NgayKham) {
        this.NgayKham = new SimpleStringProperty(NgayKham);
    }
    
}
