package quanlyphongmach;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Quang Khanh
 */
public class DonThuoc extends Scene{
    static BorderPane border = new BorderPane();
    public DonThuoc()
    {
        super(border);      
        Label TrangChu = new Label("Trang chủ");
        TrangChu.getStyleClass().add("label-menu");
        TrangChu.setOnMouseClicked(e->
        {
            SceneController.setHomePagePanel();
        });
        Label BenhNhan = new Label("Bệnh nhân");
        BenhNhan.getStyleClass().add("label-menu");
        BenhNhan.setOnMouseClicked(e->
        {
            SceneController.setBenhNhanPanel();
        });
        Label DonThuoc= new Label("Đơn thuốc");
        DonThuoc.setId("donthuoc");
        DonThuoc.getStyleClass().add("label-menu");
        Label HoaDon = new Label("Hoá đơn");
        HoaDon.getStyleClass().add("label-menu");
        HoaDon.setOnMouseClicked(e->
        {
            SceneController.setHoaDonPanel();
        });
        Label Thuoc = new Label("Thuốc");
        Thuoc.setOnMouseClicked(e->
        {
            SceneController.setThuocPanel();
        });
        Thuoc.getStyleClass().add("label-menu");
        Label NhapKho = new Label("Nhập kho");
        NhapKho.getStyleClass().add("label-menu");
        Label BaoCao = new Label("Báo cáo");
        BaoCao.getStyleClass().add("label-menu");
        Label CauHinh = new Label("Cấu hình");
        CauHinh.getStyleClass().add("label-menu");
        
        HBox menu = new HBox(50);
        menu.setId("menu");
        menu.setPadding(new Insets(10,20,10,20));
        menu.setAlignment(Pos.TOP_CENTER);
        menu.getChildren().addAll(TrangChu, BenhNhan, DonThuoc, HoaDon, Thuoc, NhapKho, BaoCao, CauHinh);
        
        BorderPane Bdmenu = new BorderPane();
        Bdmenu.setId("bdmenu");
        
        Label lbDanhSachToaThuoc = new Label("Danh sách toa thuốc");
        lbDanhSachToaThuoc.getStyleClass().add("menu2");
        lbDanhSachToaThuoc.setAlignment(Pos.TOP_LEFT);
        
        Text txtDanhSachToaThuoc = new Text("Danh sách toa thuốc");
        txtDanhSachToaThuoc.setId("txtDanhSachToaThuoc");
        TextField tfTimKiem = new TextField();
        DatePicker fromDay = new DatePicker();
        DatePicker toDay = new DatePicker();
        
        Rectangle iconTimKiem = new Rectangle(50,30);
        iconTimKiem.getStyleClass().add("icon");
        iconTimKiem.setFill(Color.web("#FFCF75"));
        Text txtTimKiem = new Text("Tìm");
        txtTimKiem.setMouseTransparent(true);
        txtTimKiem.setId("txtTimKiem");
        txtTimKiem.setFill(Color.BLACK);
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.getChildren().addAll(txtDanhSachToaThuoc, tfTimKiem, fromDay, toDay, stack);
        
        TableView tbDanhSachToaThuoc = new TableView();
        tbDanhSachToaThuoc.setEditable(false);
        TableColumn maCol = new TableColumn("Mã số");
        maCol.setPrefWidth(120);
        maCol.setResizable(true);
        TableColumn tenCol = new TableColumn("Tên bệnh nhân");
        tenCol.setPrefWidth(150);
        tenCol.setResizable(true);
        TableColumn tuoiCol = new TableColumn("Tuổi");
        tuoiCol.setPrefWidth(50);
        tuoiCol.setResizable(true);
        TableColumn chanDoanCol = new TableColumn("Chẩn đoán");
        chanDoanCol.setPrefWidth(150);
        chanDoanCol.setResizable(true);
        TableColumn tongtienCol = new TableColumn("Tổng tiền thuốc");
        tongtienCol.setPrefWidth(120);
        tongtienCol.setResizable(true);
        TableColumn nguoiLapCol = new TableColumn("Người lập");
        nguoiLapCol.setPrefWidth(120);
        nguoiLapCol.setResizable(true);
        TableColumn ngayLapCol = new TableColumn("Ngày lập");
        ngayLapCol.setPrefWidth(120);
        ngayLapCol.setResizable(true);
        TableColumn chucNangCol = new TableColumn("Chức năng");
        chucNangCol.setPrefWidth(100);
        chucNangCol.setResizable(true);
        tbDanhSachToaThuoc.getColumns().addAll(maCol, tenCol, tuoiCol, chanDoanCol, tongtienCol, nguoiLapCol, ngayLapCol, chucNangCol);
        VBox table = new VBox(10);
        table.setPadding(new Insets(15, 10, 15, 20));
        table.getChildren().add(tbDanhSachToaThuoc);
        
        Bdmenu.setTop(menu);
        Bdmenu.setCenter(lbDanhSachToaThuoc);
        Bdmenu.setBottom(submenu);
        
        border.setTop(Bdmenu);
        border.setCenter(table);
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/donthuoc.css").toExternalForm());
        
        
    }
}
