/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyphongmach;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Quang Khanh
 */
public class SceneController {
    private static final  BenhNhan benhnhan = new BenhNhan();
    private static final HomePage homepage = new HomePage();
    private static final DangNhap dangnhap = new DangNhap();
    private static final DonThuoc donthuoc = new DonThuoc();
    private static final HoaDon hoadon = new HoaDon();
    private static final Thuoc thuoc = new Thuoc();
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
    }
    public static void setDonThuocPanel()
    {
        QuanLyPhongMach.getstage().setScene(donthuoc);
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
}
