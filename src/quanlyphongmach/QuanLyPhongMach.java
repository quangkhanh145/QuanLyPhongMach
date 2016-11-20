package quanlyphongmach;

import javafx.application.Application;
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
