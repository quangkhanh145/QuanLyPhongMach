/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyphongmach;

import javafx.scene.Scene;
import javafx.stage.Stage;
import quanlyphongmach.model.BenhNhan;

/**
 *
 * @author Quang Khanh
 */
public class SceneController {
    private static final  BenhNhanPanel benhnhan = new BenhNhanPanel();
    private static final HomePage homepage = new HomePage();
    private static final DangNhapPanel dangnhap = new DangNhapPanel();
    private static final DonThuocPanel donthuoc = new DonThuocPanel();
    private static final KeToaPanel ketoa = new KeToaPanel();
    private static final HoaDonPanel hoadon = new HoaDonPanel();
    private static final ThuocPanel thuoc = new ThuocPanel();
    private static final NhapKhoPanel nhapkho = new NhapKhoPanel();
    private static final CauHinhPanel cauhinh = new CauHinhPanel();
    public SceneController()
    {
        
    }
    public static void setBenhNhanPanel()
    {
        QuanLyPhongMach.getstage().setScene(benhnhan);
        QuanLyPhongMach.getstage().setMaximized(true);
    }
    public static void setHomePagePanel()
    {
        QuanLyPhongMach.getstage().setScene(homepage);
        QuanLyPhongMach.getstage().setHeight(446);
        QuanLyPhongMach.getstage().setWidth(514);
    }
    public static void setDangNhapPanel()
    {
        QuanLyPhongMach.getstage().setScene(dangnhap);
        QuanLyPhongMach.getstage().setHeight(335);
        QuanLyPhongMach.getstage().setWidth(450);
    }
    public static void setDonThuocPanel()
    {
        donthuoc.loadDsDonThuoc();
        QuanLyPhongMach.getstage().setScene(donthuoc);
        QuanLyPhongMach.getstage().setMaximized(true);
    }
    public static void setKeToaPanel(BenhNhan benhnhan)
    {
        ketoa.setBenhNhan(benhnhan);
        QuanLyPhongMach.getstage().setScene(ketoa);
        QuanLyPhongMach.getstage().setMaximized(true);
    }
    public static void setHoaDonPanel()
    {
        QuanLyPhongMach.getstage().setScene(hoadon);
        QuanLyPhongMach.getstage().setMaximized(true);
    }
    public static void setThuocPanel()
    {
        QuanLyPhongMach.getstage().setScene(thuoc);
        QuanLyPhongMach.getstage().setMaximized(true);
    }
    public static void setNhapKhoPanel()
    {
        QuanLyPhongMach.getstage().setScene(nhapkho);
        QuanLyPhongMach.getstage().setMaximized(true);
    }
    public static void setCauHinhPanel()
    {
        QuanLyPhongMach.getstage().setScene(cauhinh);
        QuanLyPhongMach.getstage().setMaximized(true);
    }
}
