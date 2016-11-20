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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import static quanlyphongmach.DonThuoc.border;

/**
 *
 * @author Quang Khanh
 */
public class HoaDon extends Scene{
    private static BorderPane border = new BorderPane();
    public HoaDon()
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
        DonThuoc.getStyleClass().add("label-menu");
        DonThuoc.setOnMouseClicked(e->
        {
            SceneController.setDonThuocPanel();
        });
        Label HoaDon = new Label("Hoá đơn");
        HoaDon.getStyleClass().add("label-menu");
        HoaDon.setId("hoadon");
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
        
        Label lbHoaDon = new Label("Danh sách hoá đơn");
        lbHoaDon.getStyleClass().add("menu2");
        lbHoaDon.setAlignment(Pos.TOP_LEFT);
        
        Text txtDanhSachHoaDon = new Text("Danh sách hoá đơn");
        txtDanhSachHoaDon.setId("txtDanhSachHoaDon");
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
        
        Rectangle iconThemHoaDon = new Rectangle(112,30);
        iconThemHoaDon.getStyleClass().add("icon");
        iconThemHoaDon.setFill(Color.web("#FFCF75"));
        Text txtThemHD = new Text("+ Thêm hoá đơn");
        txtThemHD.setMouseTransparent(true);
        txtThemHD.setId("txtThemHD");
        
        StackPane stackThemHD = new StackPane();
        stackThemHD.getChildren().addAll(iconThemHoaDon, txtThemHD);
        stackThemHD.setAlignment(Pos.CENTER_RIGHT);
        stackThemHD.setMargin(txtThemHD,new Insets(0,5,0,0));
        stackThemHD.setPadding(new Insets(0,0,5,0));
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemHD,Priority.ALWAYS);
        submenu.getChildren().addAll(txtDanhSachHoaDon, tfTimKiem, fromDay, toDay,
                stack, stackThemHD);
        
         TableView tbDanhSachHoaDon = new TableView();
        tbDanhSachHoaDon.setEditable(false);
        TableColumn maCol = new TableColumn("Mã số");
        maCol.setPrefWidth(120);
        maCol.setResizable(true);
        TableColumn tenCol = new TableColumn("Tên bệnh nhân");
        tenCol.setPrefWidth(150);
        tenCol.setResizable(true);
        TableColumn tuoiCol = new TableColumn("Tuổi");
        tuoiCol.setPrefWidth(50);
        tuoiCol.setResizable(true);
        TableColumn nguoiLapCol = new TableColumn("Người lập");
        nguoiLapCol.setPrefWidth(120);
        nguoiLapCol.setResizable(true);
        TableColumn ngayLapCol = new TableColumn("Ngày lập");
        ngayLapCol.setPrefWidth(120);
        ngayLapCol.setResizable(true);
        TableColumn tongHoaDonCol = new TableColumn("Tổng hoá đơn");
        tongHoaDonCol.setPrefWidth(120);
        tongHoaDonCol.setResizable(true);
        TableColumn chucNangCol = new TableColumn("Chức năng");
        chucNangCol.setPrefWidth(100);
        chucNangCol.setResizable(true);
        tbDanhSachHoaDon.getColumns().addAll(maCol, tenCol, tuoiCol, nguoiLapCol,
                ngayLapCol, tongHoaDonCol, chucNangCol);
        VBox table = new VBox(10);
        table.setPadding(new Insets(15, 10, 15, 20));
        table.getChildren().add(tbDanhSachHoaDon);
        
        Bdmenu.setTop(menu);
        Bdmenu.setCenter(lbHoaDon);
        Bdmenu.setBottom(submenu);
        
        border.setTop(Bdmenu);
        border.setCenter(table);
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/hoadon.css").toExternalForm());
    }
}
