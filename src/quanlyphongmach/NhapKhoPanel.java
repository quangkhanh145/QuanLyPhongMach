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
public class NhapKhoPanel extends Scene{
    static BorderPane Scene = new BorderPane();
    public NhapKhoPanel()
    {
        super(Scene);
        CreateScene();
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/main.css").toExternalForm());
    }
    
    private void CreateScene()
    {
        Scene.setTop(Header());
        Scene.setLeft(Group());
        Scene.setCenter(tb_DSNhapKho());
    }
    private BorderPane Header()
    {
        Label TrangChu = new Label("Trang chủ");
        TrangChu.getStyleClass().add("label-main-menu");
        TrangChu.setOnMouseClicked(e->
        {
            SceneController.setHomePagePanel();
        });
        Label BenhNhan = new Label("Bệnh nhân");
        BenhNhan.getStyleClass().add("label-main-menu");
        BenhNhan.setOnMouseClicked(e->
        {
            SceneController.setBenhNhanPanel();
        });
        Label DonThuoc= new Label("Đơn thuốc");
        DonThuoc.getStyleClass().add("label-main-menu");
        DonThuoc.setOnMouseClicked(e->
        {
            SceneController.setDonThuocPanel();
        });
        Label HoaDon = new Label("Hoá đơn");
        HoaDon.getStyleClass().add("label-main-menu");
        HoaDon.setOnMouseClicked(e->
        {
            SceneController.setHoaDonPanel();
        });
        Label Thuoc = new Label("Thuốc");
        Thuoc.getStyleClass().add("label-main-menu");
        Thuoc.setOnMouseClicked(e->
        {
            SceneController.setThuocPanel();
        });
        Label NhapKho = new Label("Nhập kho");
        NhapKho.setId("NhapKho");
        NhapKho.getStyleClass().add("label-main-menu");
        Label BaoCao = new Label("Báo cáo");
        BaoCao.getStyleClass().add("label-main-menu");
        Label CauHinh = new Label("Cấu hình");
        CauHinh.getStyleClass().add("label-main-menu");
        CauHinh.setOnMouseClicked(e->
        {
            SceneController.setCauHinhPanel();
        });
        
        HBox mainMenu = new HBox(50);
        mainMenu.getStyleClass().add("menu");
        mainMenu.setPadding(new Insets(10,20,10,20));
        mainMenu.setAlignment(Pos.TOP_CENTER);
        mainMenu.getChildren().addAll(TrangChu, BenhNhan, DonThuoc, HoaDon, 
                Thuoc, NhapKho, BaoCao, CauHinh);
                
        Label lb_submenu_1 = new Label("Danh sách nhập kho");
        lb_submenu_1.getStyleClass().add("label_submenu");
        
        Label  lb_submenu_2 = new Label("Nhà cung cấp");
         lb_submenu_2.getStyleClass().add("label_submenu");
        
        HBox label_hbox = new HBox(10);
        label_hbox.setAlignment(Pos.TOP_CENTER);
        label_hbox.getChildren().addAll(lb_submenu_1,  lb_submenu_2);
        
        BorderPane header = new BorderPane();
        header.getStyleClass().add("header");
        
        header.setTop(mainMenu);
        header.setCenter(label_hbox);
        header.setBottom(submenuDSNhapKho());
        
        lb_submenu_1.setOnMouseClicked(e->
        {
            header.setBottom(submenuDSNhapKho());
            Scene.setLeft(Group());
            Scene.setCenter(tb_DSNhapKho());
        });
        
        lb_submenu_2.setOnMouseClicked(e->
        {
            header.setBottom(submenuNhaCungCap());
            Scene.setCenter(tb_NhaCungCap());
        });
        
        return header;
    }
    private HBox submenuDSNhapKho()
    {
        Text DanhSachNhapKho = new Text("Danh sách nhập kho");
        DanhSachNhapKho.getStyleClass().add("text-submenu");
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
        txtTimKiem.getStyleClass().add("txt-button");
        txtTimKiem.setFill(Color.BLACK);
        
        Rectangle iconThemPhieuNhap = new Rectangle(120,30);
        iconThemPhieuNhap.getStyleClass().add("icon");
        iconThemPhieuNhap.setFill(Color.web("#FFCF75"));
        Text txtThemPN = new Text("+ Thêm phiếu nhập");
        txtThemPN.setMouseTransparent(true);
        txtThemPN.getStyleClass().add("txt-button");
        
        StackPane stackThemPN = new StackPane();
        stackThemPN.getChildren().addAll(iconThemPhieuNhap, txtThemPN);
        stackThemPN.setAlignment(Pos.CENTER_RIGHT);
        stackThemPN.setMargin(txtThemPN,new Insets(0,5,0,0));
        stackThemPN.setPadding(new Insets(0,0,5,0));
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemPN,Priority.ALWAYS);
        submenu.getChildren().addAll(DanhSachNhapKho, tfTimKiem, fromDay, toDay, stack,stackThemPN);
        
        return submenu;
    }
    
    private VBox tb_DSNhapKho()
    {
        TableView tb = new TableView();
        tb.setEditable(true);
        TableColumn maSoCol = new TableColumn("Mã số");
        maSoCol.setPrefWidth(100);
        TableColumn nhaCungCapCol = new TableColumn("Nhà cung cấp");
        nhaCungCapCol.setPrefWidth(150);
        TableColumn nguoiLapCol = new TableColumn("Người lập");
        nguoiLapCol.setPrefWidth(150);
        TableColumn ngayLapCol = new TableColumn("Ngày lập");
        ngayLapCol.setPrefWidth(100);
        TableColumn tongHoaDon = new TableColumn("Tổng hóa đơn");
        tongHoaDon.setPrefWidth(100);
        TableColumn chucNangCol = new TableColumn("Chức năng");
        chucNangCol.setPrefWidth(80);
        
        tb.getColumns().addAll(maSoCol, nhaCungCapCol, nguoiLapCol, ngayLapCol,
                tongHoaDon, chucNangCol);
        VBox vb = new VBox(10);
        vb.setPadding(new Insets(15, 10, 15, 20));
        vb.getChildren().add(tb);
        return vb;
    }
    
    private HBox submenuNhaCungCap()
    {
        Text QLNhaCungCap = new Text("Quản lý nhà cung cấp");
        QLNhaCungCap.getStyleClass().add("text-submenu");
        TextField tfTimKiem = new TextField();
        tfTimKiem.setPromptText("Nhập từ khoá");
        tfTimKiem.setFocusTraversable(false);
        Rectangle iconTimKiem = new Rectangle(50,30);
        iconTimKiem.getStyleClass().add("icon");
        iconTimKiem.setFill(Color.web("#FFCF75"));
        Text txtTimKiem = new Text("Tìm");
        txtTimKiem.setMouseTransparent(true);
        txtTimKiem.getStyleClass().add("txt-button");
        txtTimKiem.setFill(Color.BLACK);
        
        Rectangle iconThemNhaCC = new Rectangle(130,30);
        iconThemNhaCC.getStyleClass().add("icon");
        iconThemNhaCC.setFill(Color.web("#FFCF75"));
        Text txtThemNhaCC = new Text("+ Thêm nhà cung cấp");
        txtThemNhaCC.setMouseTransparent(true);
        txtThemNhaCC.getStyleClass().add("txt-button");
        
        StackPane stackThemNhaCC = new StackPane();
        stackThemNhaCC.getChildren().addAll(iconThemNhaCC, txtThemNhaCC);
        stackThemNhaCC.setAlignment(Pos.CENTER_RIGHT);
        stackThemNhaCC.setMargin(txtThemNhaCC,new Insets(0,5,0,0));
        stackThemNhaCC.setPadding(new Insets(0,0,5,0));
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemNhaCC,Priority.ALWAYS);
        submenu.getChildren().addAll(QLNhaCungCap, tfTimKiem, stack,stackThemNhaCC);
        
        return submenu;
    }
    
    private VBox tb_NhaCungCap()
    {
        TableView tb = new TableView();
        tb.setEditable(true);
        TableColumn maCol = new TableColumn("Mã");
        maCol.setPrefWidth(100);
        TableColumn tenCol = new TableColumn("Tên");
        tenCol.setPrefWidth(100);
        TableColumn dienThoaiCol = new TableColumn("Điện thoại");
        dienThoaiCol.setPrefWidth(100);
        TableColumn diaChiCol = new TableColumn("Địa chỉ");
        diaChiCol.setPrefWidth(150);
        TableColumn nhomCol = new TableColumn("Nhóm");
        nhomCol.setPrefWidth(100);
        TableColumn ngayLapCol = new TableColumn("Ngày lập");
        ngayLapCol.setPrefWidth(120);
        TableColumn chucNangCol = new TableColumn("Chức năng");
        chucNangCol.setPrefWidth(80);
        
        tb.getColumns().addAll(maCol, tenCol, dienThoaiCol, diaChiCol,
                nhomCol, ngayLapCol,chucNangCol);
        VBox vb = new VBox(10);
        vb.setPadding(new Insets(15, 10, 15, 20));
        vb.getChildren().add(tb);
        return vb;
    }
    
    private VBox Group()
    {
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
        
        return vbgroup;
    }
    
}
