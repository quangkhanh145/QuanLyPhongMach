/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyphongmach;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Quang Khanh
 */
public class DangNhapPanel extends Scene{
    private static GridPane grid = new GridPane();
    TextField tf_user;
    PasswordField pf_pass;
    ConnectToDatabase conn = new ConnectToDatabase();
    public DangNhapPanel()
    {
        super(grid,400, 275);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(0,0,0,0));
        
        Button btnLogin = new Button("Đăng nhập");
        btnLogin.setFocusTraversable(true);
        btnLogin.setId("btnLogin");
        Label lb_user = new Label("Username: ");
        Label lb_pass = new Label("Password: ");
        tf_user = new TextField();
        tf_user.setPromptText("Tên đăng nhập");
        pf_pass = new PasswordField();
        pf_pass.setPromptText("Mật khẩu");
        Text checkLogin = new Text();
        checkLogin.setId("checklogin");
        
        Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0 , 2, 1);
        grid.add(lb_user, 0, 1);
        grid.add(lb_pass , 0, 2);
        grid.add(tf_user, 1, 1);
        grid.add(pf_pass, 1 , 2);
        grid.add(checkLogin, 1,4);
        
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnLogin);
        grid.add(hbBtn, 1, 5);
       
        btnLogin.setDefaultButton(true);
        btnLogin.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                String sql = "SELECT * FROM tai_khoan WHERE "
                        + "Ten_Dang_Nhap = '"+tf_user.getText()
                        +"' AND Mat_Khau = '"+pf_pass.getText()+"'";
                CachedRowSet crs = conn.getCRS(sql);
                try {
                    if(crs.isBeforeFirst()){
                        tf_user.clear();
                        pf_pass.clear();
                        tf_user.requestFocus();
                        SceneController.setHomePagePanel();
                    }
                    else{
                        checkLogin.setText("Mật khẩu không đúng ");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DangNhapPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/login.css").toExternalForm());
    }
}
