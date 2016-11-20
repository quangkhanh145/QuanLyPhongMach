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
        window.setTitle("Quản lý phòng mạch");
        window.setResizable(true);
        SceneController.setDangNhapPanel();
        window.sizeToScene();
        window.show();
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
