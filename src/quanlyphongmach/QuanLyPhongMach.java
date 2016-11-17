package quanlyphongmach;

import com.sun.scenario.effect.Effect;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Quang Khanh
 */

public class QuanLyPhongMach extends Application {
    
    private static Stage window;
    @Override
    public void start(Stage primaryStage) {
        
        window = primaryStage;
        
        Button btnLogin = new Button("Đăng nhập");
        btnLogin.setId("btnLogin");
        Label lb_user = new Label("Username: ");
        Label lb_pass = new Label("Password: ");
        TextField tf_user = new TextField();
        PasswordField pf_pass = new PasswordField();
        Text checkLogin = new Text();
        checkLogin.setId("checklogin");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(0,0,0,0));
        
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
        Scene scene1 = new Scene(grid, 400, 275);
        scene1.getStylesheets().add(QuanLyPhongMach.class.getResource("/css/login.css").toExternalForm());
        primaryStage.setTitle("Quản lý phòng mạch");
        primaryStage.setScene(scene1);
        primaryStage.sizeToScene();
        
        HomePage homepg =new HomePage();
        btnLogin.setDefaultButton(true);
        btnLogin.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(tf_user.getText().equals("admin"))
                {
                    if(pf_pass.getText().equals("admin"))
                    {
                        primaryStage.setScene(homepg);
                    }
                    else
                    {
                        checkLogin.setText("Mật khẩu không đúng ");
                    }
                }
                else
                {
                    checkLogin.setText("Tài khoản không đúng");
                }
            }
            
        });
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    
    public static Stage getstage()
    {
        return window;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
