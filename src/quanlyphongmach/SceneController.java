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
    public SceneController()
    {
        
    }
    public static void setBenhNhanPanel()
    {
        QuanLyPhongMach.getstage().setScene(benhnhan);
    }
    public static void setHomePagePanel()
    {
        QuanLyPhongMach.getstage().setScene(homepage);
    }
    public static void setDangNhapPanel()
    {
        QuanLyPhongMach.getstage().setScene(dangnhap);
    }
}
