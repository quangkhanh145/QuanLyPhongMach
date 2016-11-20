package quanlyphongmach;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
public class Thuoc extends Scene{
    private static BorderPane border = new BorderPane();
    public Thuoc()
    {
        super(border);
        createMenu();
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/thuoc.css").toExternalForm());
    }
    private void createMenu()
    {
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
        HoaDon.setOnMouseClicked(e->
        {
            SceneController.setHoaDonPanel();
        });
        Label Thuoc = new Label("Thuốc");
        Thuoc.setId("Thuoc");
        Thuoc.getStyleClass().add("label-menu");
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
        menu.getChildren().addAll(TrangChu, BenhNhan, DonThuoc, HoaDon, Thuoc, 
                NhapKho, BaoCao, CauHinh);    
        
        Label sbThuoc = new Label("Thuốc");
        sbThuoc.getStyleClass().add("menu2");
        
        Label sbDichVu = new Label("Dịch vụ");
        sbDichVu.getStyleClass().add("menu2");
        
        Label sbVatTu = new Label("Vật tư y tế");
        sbVatTu.getStyleClass().add("menu2");
        
        HBox menu2 = new HBox(20);
        menu2.getChildren().addAll(sbThuoc, sbDichVu, sbVatTu);
        menu2.setAlignment(Pos.TOP_CENTER);
        
        BorderPane Bdmenu = new BorderPane();
        Bdmenu.setId("bdmenu");        
        
        Bdmenu.setTop(menu);
        Bdmenu.setCenter(menu2);
        Bdmenu.setBottom(submenuThuoc());
        border.setTop(Bdmenu);
        border.setLeft(GroupThuoc());
        border.setCenter(tableThuoc());
        sbThuoc.setOnMouseClicked(e->
        {
            Bdmenu.setBottom(submenuThuoc());
            border.setLeft(GroupThuoc());
            border.setCenter(tableThuoc());
            
        });
        sbDichVu.setOnMouseClicked(e->
        {
            Bdmenu.setBottom(submenuDichVu());
            border.setLeft(GroupDichVu());
            border.setCenter(tableDichVu());
        });
        sbVatTu.setOnMouseClicked(e->
        {
            Bdmenu.setBottom(submenuVatTu());
            border.setLeft(GroupVatTu());
            border.setCenter(tableVatTu());
        });
    }
    private HBox submenuThuoc()
    {
        Text DanhSachThuoc = new Text("Danh sách thuốc");
        DanhSachThuoc.getStyleClass().add("label-submenu");
        TextField tfTimKiem = new TextField();
        tfTimKiem.setPromptText("Nhập từ khoá");
        tfTimKiem.setFocusTraversable(false);        
        Rectangle iconTimKiem = new Rectangle(50,30);
        iconTimKiem.getStyleClass().add("icon");
        iconTimKiem.setFill(Color.web("#FFCF75"));
        Text txtTimKiem = new Text("Tìm");
        txtTimKiem.setMouseTransparent(true);
        txtTimKiem.setId("txtTimKiem");
        txtTimKiem.setFill(Color.BLACK);
        
        Rectangle iconThemThuoc = new Rectangle(90,30);
        iconThemThuoc.getStyleClass().add("icon");
        iconThemThuoc.setFill(Color.web("#FFCF75"));
        Text txtThemThuoc = new Text("+ Thêm thuốc");
        txtThemThuoc.setMouseTransparent(true);
        txtThemThuoc.setId("txtThemThuoc");
        
        StackPane stackThemThuoc = new StackPane();
        stackThemThuoc.getChildren().addAll(iconThemThuoc, txtThemThuoc);
        stackThemThuoc.setAlignment(Pos.CENTER_RIGHT);
        stackThemThuoc.setMargin(txtThemThuoc,new Insets(0,5,0,0));
        stackThemThuoc.setPadding(new Insets(0,0,5,0));
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemThuoc,Priority.ALWAYS);
        submenu.getChildren().addAll(DanhSachThuoc, tfTimKiem, stack,stackThemThuoc);
        return submenu;
    }
    
    private HBox submenuDichVu()
    {
        Text DanhSachDichVu = new Text("Dịch vụ");
        DanhSachDichVu.getStyleClass().add("label-submenu");
        TextField tfTimKiem = new TextField();
        tfTimKiem.setPromptText("Nhập từ khoá");  
        tfTimKiem.setFocusTraversable(false);
        Rectangle iconTimKiem = new Rectangle(50,30);
        iconTimKiem.getStyleClass().add("icon");
        iconTimKiem.setFill(Color.web("#FFCF75"));
        Text txtTimKiem = new Text("Tìm");
        txtTimKiem.setMouseTransparent(true);
        txtTimKiem.setId("txtTimKiem");
        txtTimKiem.setFill(Color.BLACK);
        
        Rectangle iconThemDV = new Rectangle(100,30);
        iconThemDV.getStyleClass().add("icon");
        iconThemDV.setFill(Color.web("#FFCF75"));
        Text txtThemDV = new Text("+ Thêm dịch vụ");
        txtThemDV.setMouseTransparent(true);
        txtThemDV.setId("txtThemDV");
        
        StackPane stackThemDV = new StackPane();
        stackThemDV.getChildren().addAll(iconThemDV, txtThemDV);
        stackThemDV.setAlignment(Pos.CENTER_RIGHT);
        stackThemDV.setMargin(txtThemDV,new Insets(0,5,0,0));
        stackThemDV.setPadding(new Insets(0,0,5,0));
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemDV,Priority.ALWAYS);
        submenu.getChildren().addAll(DanhSachDichVu, tfTimKiem, stack,stackThemDV);
        return submenu;
    }
    
    private HBox submenuVatTu()
    {
        Text DanhSachVatTu = new Text("Danh sách vật tư y tế");
        DanhSachVatTu.getStyleClass().add("label-submenu");
        TextField tfTimKiem = new TextField();
        tfTimKiem.setPromptText("Nhập từ khoá");
        tfTimKiem.setFocusTraversable(false);        
        Rectangle iconTimKiem = new Rectangle(50,30);
        iconTimKiem.getStyleClass().add("icon");
        iconTimKiem.setFill(Color.web("#FFCF75"));
        Text txtTimKiem = new Text("Tìm");
        txtTimKiem.setMouseTransparent(true);
        txtTimKiem.setId("txtTimKiem");
        txtTimKiem.setFill(Color.BLACK);
        
        Rectangle iconThemVT = new Rectangle(138,30);
        iconThemVT.getStyleClass().add("icon");
        iconThemVT.setFill(Color.web("#FFCF75"));
        Text txtThemVT = new Text("+ Thêm mới vật tư y tế");
        txtThemVT.setMouseTransparent(true);
        txtThemVT.setId("txtThemVT");
        
        StackPane stackThemVT = new StackPane();
        stackThemVT.getChildren().addAll(iconThemVT, txtThemVT);
        stackThemVT.setAlignment(Pos.CENTER_RIGHT);
        stackThemVT.setMargin(txtThemVT,new Insets(0,5,0,0));
        stackThemVT.setPadding(new Insets(0,0,5,0));
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemVT,Priority.ALWAYS);
        submenu.getChildren().addAll(DanhSachVatTu, tfTimKiem, stack,stackThemVT);
        return submenu;
    }
    
    private VBox GroupThuoc()
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
        
        VBox vbgroup = new VBox(0);
        vbgroup.getChildren().addAll(HBoxGroup, stk1);
        
        String[]e_group = {"tiêu hoá, gan mật", "Nội tiết", "hô hấp", 
            "kháng sinh", "cơ xương khớp", "tim mạch", "thần kinh", 
            "vitamin & thuốc bổ", "tiết niệu", "dị ứng", 
            "hạ sốt, giảm đau", "kháng viêm"};
        
        Rectangle[] rec = new Rectangle[50];//new Rectangle(250,30);
        //rec.getStyleClass().add("rec");
        Text[] txt = new Text[50];
        //txt2.getStyleClass().add("txt");
        StackPane[] stk = new StackPane[50];        
        for(int i = 0; i < e_group.length; i++)
        {
            txt[i] = new Text(e_group[i].toUpperCase());
            txt[i].getStyleClass().add("txt");
            txt[i].setMouseTransparent(true);
            rec[i] = new Rectangle(250,30);
            rec[i].getStyleClass().add("rec");      
            stk[i] = new StackPane();
            stk[i].setAlignment(Pos.CENTER_LEFT);
            stk[i].setMargin(txt[i], new Insets(0,0,0,10));
            stk[i].getStyleClass().add("stk");
            stk[i].getChildren().addAll(rec[i], txt[i]);
            vbgroup.getChildren().add(stk[i]);
        }
        return vbgroup;
    }
    
    private VBox GroupDichVu()
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
        
        String[]e_group = {};
        
        Rectangle rec2 = new Rectangle(250,30);
        rec2.getStyleClass().add("rec");
        Text txt2 = new Text();
        txt2.getStyleClass().add("txt");
        StackPane stk2 = new StackPane();
        stk2.getStyleClass().add("stk");
        stk2.setAlignment(Pos.CENTER_LEFT);
        stk2.setMargin(txt1, new Insets(0,0,0,10));
        for(int i = 0; i < e_group.length; i++)
        {
            txt2.setText(e_group[i]);
            stk2.getChildren().addAll(rec2, txt2);
        }
        
        return vbgroup;
    }
    
    private VBox GroupVatTu()
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
        
        String[]e_group = {};
        
        Rectangle rec2 = new Rectangle(250,30);
        rec2.getStyleClass().add("rec");
        Text txt2 = new Text();
        txt2.getStyleClass().add("txt");
        StackPane stk2 = new StackPane();
        stk2.getStyleClass().add("stk");
        stk2.setAlignment(Pos.CENTER_LEFT);
        stk2.setMargin(txt1, new Insets(0,0,0,10));
        for(int i = 0; i < e_group.length; i++)
        {
            txt2.setText(e_group[i]);
            stk2.getChildren().addAll(rec2, txt2);
        }
        
        return vbgroup;
    }
    
    private VBox tableThuoc()
    {
        TableView tbThuoc = new TableView();
        tbThuoc.setEditable(false);
        TableColumn maSo = new TableColumn("Mã số");
        maSo.setPrefWidth(100);
        TableColumn Ten = new TableColumn("Tên");
        Ten.setPrefWidth(150);
        TableColumn donViTinh = new TableColumn("Đơn vị tính");
        donViTinh.setPrefWidth(80);
        TableColumn giaBan = new TableColumn("Giá bán");
        giaBan.setPrefWidth(100);
        TableColumn tonKho = new TableColumn("Tồn kho");
        tonKho.setPrefWidth(80);
        TableColumn macDinhMoiNgay = new TableColumn("Mặc định mỗi ngày");
        macDinhMoiNgay.setPrefWidth(60);
        TableColumn cachDung = new TableColumn("Cách dùng");
        cachDung.setPrefWidth(150);
        TableColumn nhomThuoc = new TableColumn("Nhóm thuốc");
        nhomThuoc.setPrefWidth(100);
        TableColumn chucNang = new TableColumn("Chức năng");   
        chucNang.setPrefWidth(80);
        
        tbThuoc.getColumns().addAll(maSo, Ten, donViTinh, giaBan, tonKho,
                macDinhMoiNgay, cachDung, nhomThuoc, chucNang);
        VBox table = new VBox(10);
        table.setPadding(new Insets(15, 10, 15, 20));
        table.getChildren().add(tbThuoc);
        return table;
    }
    
    private VBox tableDichVu()
    {
        TableView tbDichVu = new TableView();
        tbDichVu.setEditable(false);
        TableColumn maSo = new TableColumn("Mã số");
        maSo.setPrefWidth(80);
        TableColumn TenDV = new TableColumn("Tên dịch vụ");
        TenDV.setPrefWidth(120);
        TableColumn donViTinh = new TableColumn("Đơn vị tính");
        donViTinh.setPrefWidth(80);
        TableColumn donGia = new TableColumn("Đơn giá");
        donGia.setPrefWidth(80);
        TableColumn soLuongMacDinh = new TableColumn("Số lượng mặc định");
        soLuongMacDinh.setPrefWidth(120);
        TableColumn moTa = new TableColumn("Mô tả");
        moTa.setPrefWidth(120);
        TableColumn nhom = new TableColumn("Nhóm");
        nhom.setPrefWidth(80);
        TableColumn chucNang = new TableColumn("Chức năng");
        chucNang.setPrefWidth(80);
        
        tbDichVu.getColumns().addAll(maSo, TenDV, donViTinh, donGia,
                soLuongMacDinh, moTa, nhom, chucNang);
        VBox table = new VBox(10);
        table.setPadding(new Insets(15, 10, 15, 20));
        table.getChildren().add(tbDichVu);
        return table;
    }
    
    private VBox tableVatTu()
    {
        TableView tbVatTu = new TableView();
        tbVatTu.setEditable(false);
        TableColumn maSo = new TableColumn("Mã số");
        maSo.setPrefWidth(100);
        TableColumn TenVTYT = new TableColumn("Tên VTYT");
        TenVTYT.setPrefWidth(150);
        TableColumn donViTinh = new TableColumn("Đơn vị tính");
        donViTinh.setPrefWidth(80);
        TableColumn giaBan = new TableColumn("Giá bán");
        giaBan.setPrefWidth(100);
        TableColumn giaNhap = new TableColumn("Giá nhập");
        giaNhap.setPrefWidth(100);
        TableColumn tonKho = new TableColumn("Tồn kho");
        tonKho.setPrefWidth(80);
        TableColumn nhom = new TableColumn("Nhóm");
        nhom.setPrefWidth(100);
        TableColumn chucNang = new TableColumn("Chức năng"); 
        chucNang.setPrefWidth(80);
        
        tbVatTu.getColumns().addAll(maSo, TenVTYT, donViTinh, giaBan,
                giaNhap, tonKho, nhom, chucNang);
        VBox table = new VBox(10);
        table.setPadding(new Insets(15, 10, 15, 20));
        table.getChildren().add(tbVatTu);
        return table;
    }
    
    
}
