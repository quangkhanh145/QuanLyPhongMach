package quanlyphongmach;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
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
public class BenhNhan extends Scene{
    private static BorderPane border = new BorderPane();
    public  BenhNhan()
    {
        super(border);      
        Label TrangChu = new Label("Trang chủ");
        TrangChu.getStyleClass().add("label-menu");
        TrangChu.setOnMouseClicked(e->
        {
            SceneController.setHomePagePanel();
        });
        Label BenhNhan = new Label("Bệnh nhân");
        BenhNhan.setId("BenhNhan");
        BenhNhan.getStyleClass().add("label-menu");
        Label DonThuoc= new Label("Đơn thuốc");
        DonThuoc.getStyleClass().add("label-menu");
        DonThuoc.setOnMouseClicked(e->
        {
            SceneController.setDonThuocPanel();
        });
        Label HoaDon = new Label("Hoá đơn");
        HoaDon.getStyleClass().add("label-menu");
        HoaDon.setOnMouseClicked(e->
        {
            SceneController.setHoaDonPanel();
        });
        Label Thuoc = new Label("Thuốc");
        Thuoc.getStyleClass().add("label-menu");
        Thuoc.setOnMouseClicked(e->
        {
            SceneController.setThuocPanel();
        });
        Label NhapKho = new Label("Nhập kho");
        NhapKho.getStyleClass().add("label-menu");
        NhapKho.setOnMouseClicked(e->
        {
            SceneController.setNhapKhoPanel();
        });
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
        
        Label DanhSachBenhNhan = new Label("Danh sách bệnh nhân");
        DanhSachBenhNhan.getStyleClass().add("menu2");
        DanhSachBenhNhan.setAlignment(Pos.TOP_LEFT);
        
        Text QuanLyBenhNhan = new Text("Quản lý bệnh nhân");
        QuanLyBenhNhan.setId("QuanLyBenhNhan");
        TextField tfTimKiem = new TextField();
        tfTimKiem.setPromptText("Nhập từ khoá");
        tfTimKiem.setFocusTraversable(false);
        DatePicker fromDay = new DatePicker();
        fromDay.setPromptText("Từ ngày");
        fromDay.setFocusTraversable(false);
        DatePicker toDay = new DatePicker();
        toDay.setPromptText("Đến ngày");
        toDay.setFocusTraversable(false);
        
        Rectangle iconTimKiem = new Rectangle(50,30);
        iconTimKiem.getStyleClass().add("icon");
        iconTimKiem.setFill(Color.web("#FFCF75"));
        Text txtTimKiem = new Text("Tìm");
        txtTimKiem.setMouseTransparent(true);
        txtTimKiem.setId("txtTimKiem");
        txtTimKiem.setFill(Color.BLACK);
        
        Rectangle iconThemBenhNhan = new Rectangle(120,30);
        iconThemBenhNhan.getStyleClass().add("icon");
        iconThemBenhNhan.setFill(Color.web("#FFCF75"));
        Text txtThemBN = new Text("+ Thêm bệnh nhân");
        txtThemBN.setMouseTransparent(true);
        txtThemBN.setId("txtThemBN");
        
        StackPane stackThemBN = new StackPane();
        stackThemBN.getChildren().addAll(iconThemBenhNhan, txtThemBN);
        stackThemBN.setAlignment(Pos.CENTER_RIGHT);
        stackThemBN.setMargin(txtThemBN,new Insets(0,5,0,0));
        stackThemBN.setPadding(new Insets(0,0,5,0));
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemBN,Priority.ALWAYS);
        submenu.getChildren().addAll(QuanLyBenhNhan, tfTimKiem, fromDay, toDay, stack,stackThemBN);
        
        Text group = new Text("Nhóm");
        group.setId("group");
        Text AddGroup = new Text("+");
        AddGroup.setId("AddGroup");
        GridPane grid = new GridPane();
        grid.add(AddGroup, 0, 0);
        grid.setAlignment(Pos.CENTER_RIGHT);
        HBox HBoxGroup = new HBox(10);
        HBoxGroup.setHgrow(grid,Priority.ALWAYS);
        HBoxGroup.getChildren().addAll(group, grid);
        HBoxGroup.setPadding(new Insets(15,5,10,5));
        
        Rectangle rec1 = new Rectangle(250,30);
        rec1.getStyleClass().add("rec");
        Text txt1 = new Text("Tất cả");
        txt1.setMouseTransparent(true);
        txt1.getStyleClass().add("txt");
        StackPane stk1 = new StackPane();
        stk1.getStyleClass().add("stk");
        stk1.setAlignment(Pos.CENTER_LEFT);
        stk1.setMargin(txt1, new Insets(0,0,0,10));
        stk1.getChildren().addAll(rec1, txt1);
        
        VBox vbgroup = new VBox(10);
        vbgroup.getChildren().addAll(HBoxGroup, stk1);
        
        TableView tbDanhSachBN = new TableView();
        tbDanhSachBN.setEditable(false);
        TableColumn maCol = new TableColumn("Mã");
        maCol.setPrefWidth(120);
        TableColumn tenCol = new TableColumn("Tên");
        tenCol.setPrefWidth(150);
        TableColumn gioitinhCol = new TableColumn("Giới tính");
        gioitinhCol.setPrefWidth(70);
        TableColumn tuoiCol = new TableColumn("Tuổi");
        tuoiCol.setPrefWidth(50);
        TableColumn cannangCol = new TableColumn("Cân nặng");
        cannangCol.setPrefWidth(70);
        TableColumn dienthoaiCol = new TableColumn("Điện thoại");
        dienthoaiCol.setPrefWidth(100);
        TableColumn diachiCol = new TableColumn("Địa chỉ");
        diachiCol.setPrefWidth(200);
        TableColumn nhomCol = new TableColumn("Nhóm");
        nhomCol.setPrefWidth(120);
        TableColumn ngaylapCol = new TableColumn("Ngày lập");
        ngaylapCol.setPrefWidth(120);
        TableColumn chucNangCol = new TableColumn("Chức năng");
        chucNangCol.setPrefWidth(80);
        
        tbDanhSachBN.getColumns().addAll(maCol, tenCol, gioitinhCol, tuoiCol, cannangCol, 
                dienthoaiCol, diachiCol, nhomCol, ngaylapCol, chucNangCol);
        VBox table = new VBox(10);
        table.setPadding(new Insets(15, 10, 15, 20));
        table.getChildren().add(tbDanhSachBN);
        
        
        Bdmenu.setTop(menu);
        Bdmenu.setCenter(DanhSachBenhNhan);
        Bdmenu.setBottom(submenu);
        border.setTop(Bdmenu);
        border.setLeft(vbgroup);
        border.setCenter(table);
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/benhnhan.css").toExternalForm());
    }
}
