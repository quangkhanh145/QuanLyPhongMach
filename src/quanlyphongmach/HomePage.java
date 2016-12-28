package quanlyphongmach;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Quang Khanh
 */
public class HomePage extends Scene{
    static BorderPane border = new BorderPane();
    public HomePage()
    {
        super(border,514,445);
        ImageView img_BenhNhan = new ImageView("/images/medical_black.png");
        Button BenhNhan = new Button("Bệnh nhân", img_BenhNhan);
        BenhNhan.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                SceneController.setBenhNhanPanel();
            }
        });
        
        ImageView img_NguoiDung = new ImageView("/images/hire-me.png");
        Button NguoiDung = new Button("Người dùng", img_NguoiDung);
        
        ImageView img_CauHinh = new ImageView("/images/config.png");
        Button CauHinh = new Button("Cấu hình", img_CauHinh);
        CauHinh.setOnAction(e->
        {
            SceneController.setCauHinhPanel();
        });
        
        ImageView img_Thuoc = new ImageView("/images/pills5.png");
        Button Thuoc = new Button("Thuốc", img_Thuoc);
        Thuoc.setOnAction(e->
        {
            SceneController.setThuocPanel();
        });
        
        ImageView img_QuanLyKho = new ImageView("/images/icon-rate.png");
        Button QuanLyKho = new Button("Quản lý kho", img_QuanLyKho);
        QuanLyKho.setOnAction(e->
        {
            SceneController.setNhapKhoPanel();
        });
        
        ImageView img_BaoCao = new ImageView("/images/icon-chart.png");
        Button BaoCao = new Button("Báo cáo",img_BaoCao);
        
        String name = "Quang Khánh";
        Text txtName = new Text(name);
        txtName.setId("name");
        Text welcome = new Text("Welcome:");
        welcome.setId("welcome");
        Text logout = new Text("  Đăng xuất");
        logout.setOnMousePressed(e->{
            SceneController.setDangNhapPanel();
        });
        logout.setId("logout");
        HBox header = new HBox(10);
        header.setPadding(new Insets(10,5,10,5));
        header.getStyleClass().add("menu");
        
        header.getChildren().add(welcome);
        StackPane stack = new StackPane();
        stack.setAlignment(Pos.CENTER_LEFT);
        stack.getChildren().add(txtName);
        
        header.getChildren().add(stack);
        StackPane stack1 = new StackPane();
        stack1.getChildren().add(logout);
        stack1.setAlignment(Pos.CENTER_RIGHT);
        header.setHgrow(stack1, Priority.ALWAYS);
        header.getChildren().add(stack1);
        
        BenhNhan.setPrefSize(90, 90);
        NguoiDung.setPrefSize(90, 90);
        CauHinh.setPrefSize(90, 90);
        Thuoc.setPrefSize(90, 90);
        QuanLyKho.setPrefSize(90, 90);
        BaoCao.setPrefSize(90, 90);
        
    
        border.setTop(header);
        GridPane grid = new GridPane();
        grid.add(BenhNhan, 0, 0);
        grid.add(NguoiDung, 1, 0);
        grid.add(CauHinh, 2, 0);
        grid.add(Thuoc, 0 , 1);
        grid.add(QuanLyKho, 1 , 1);
        grid.add(BaoCao, 2, 1);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(25);
        grid.setHgap(20);
        grid.setPadding(new Insets(100, 100,100,100));
        border.setCenter(grid);
        border.getStyleClass().add("background");
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/main.css").toExternalForm());
        
    }
}
