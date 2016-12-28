package quanlyphongmach;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sql.rowset.CachedRowSet;
import quanlyphongmach.model.BenhNhan;
import quanlyphongmach.model.Thuoc;

/**
 *
 * @author Quang Khanh
 */
public class ThuocPanel extends Scene{
    private static BorderPane Scene = new BorderPane();
    private String sql;
    private CachedRowSet crs;
    private ConnectToDatabase conn = new ConnectToDatabase();
    private Stage stage_Child;
    private Scene Scene_Child;
    private final ObservableList<Thuoc> ds_thuoc = FXCollections.observableArrayList();
    private final ObservableList<String> ds_nhom = FXCollections.observableArrayList();
    public ThuocPanel()
    {
        super(Scene);
        createScene();
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/main.css").toExternalForm());
    }
    private void createScene()
    {
        Scene.setTop(Header());
        Scene.setLeft(GroupThuoc());
        Scene.setCenter(tableThuoc());
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
        Thuoc.setId("Thuoc");
        Thuoc.getStyleClass().add("label-main-menu");
        Label NhapKho = new Label("Nhập kho");
        NhapKho.getStyleClass().add("label-main-menu");
        NhapKho.setOnMouseClicked(e->
        {
            SceneController.setNhapKhoPanel();
        });
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
        mainMenu.getChildren().addAll(TrangChu, BenhNhan, DonThuoc, HoaDon, Thuoc, 
                NhapKho, BaoCao, CauHinh);    
        
        Label sbThuoc = new Label("Thuốc");
        sbThuoc.getStyleClass().add("label_submenu");
        
        Label sbDichVu = new Label("Dịch vụ");
        sbDichVu.getStyleClass().add("label_submenu");
        
        Label sbVatTu = new Label("Vật tư y tế");
        sbVatTu.getStyleClass().add("label_submenu");
        
        HBox menu = new HBox(20);
        menu.getChildren().addAll(sbThuoc, sbDichVu, sbVatTu);
        menu.setAlignment(Pos.TOP_CENTER);
        
        BorderPane header = new BorderPane();
        header.getStyleClass().add("header");       
        
        header.setTop(mainMenu);
        header.setCenter(menu);
        header.setBottom(submenuThuoc());
        sbThuoc.setOnMouseClicked(e->
        {
            header.setBottom(submenuThuoc());
            Scene.setLeft(GroupThuoc());
            Scene.setCenter(tableThuoc());
            
        });
        sbDichVu.setOnMouseClicked(e->
        {
            header.setBottom(submenuDichVu());
            Scene.setLeft(GroupDichVu());
            Scene.setCenter(tableDichVu());
        });
        sbVatTu.setOnMouseClicked(e->
        {
            header.setBottom(submenuVatTu());
            Scene.setLeft(GroupVatTu());
            Scene.setCenter(tableVatTu());
        });
        
        return header;
    }
    private HBox submenuThuoc()
    {
        Text DanhSachThuoc = new Text("Danh sách thuốc");
        DanhSachThuoc.getStyleClass().add("text-submenu");
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
        
        Rectangle iconThemThuoc = new Rectangle(90,30);
        iconThemThuoc.getStyleClass().add("icon");
        iconThemThuoc.setFill(Color.web("#FFCF75"));
        Text txtThemThuoc = new Text("+ Thêm thuốc");
        txtThemThuoc.setMouseTransparent(true);
        txtThemThuoc.getStyleClass().add("txt-button");
        iconThemThuoc.setOnMouseClicked(e->{
            CreateStageThemThuoc();
            stage_Child.centerOnScreen();
            stage_Child.showAndWait();
        });
        iconTimKiem.setOnMouseClicked(e->{
            actionTimKiemThuoc(tfTimKiem.getText());
        });
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
        DanhSachDichVu.getStyleClass().add("text-submenu");
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
        
        Rectangle iconThemDV = new Rectangle(100,30);
        iconThemDV.getStyleClass().add("icon");
        iconThemDV.setFill(Color.web("#FFCF75"));
        Text txtThemDV = new Text("+ Thêm dịch vụ");
        txtThemDV.setMouseTransparent(true);
        txtThemDV.getStyleClass().add("txt-button");
        
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
        DanhSachVatTu.getStyleClass().add("text-submenu");
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
        
        Rectangle iconThemVT = new Rectangle(138,30);
        iconThemVT.getStyleClass().add("icon");
        iconThemVT.setFill(Color.web("#FFCF75"));
        Text txtThemVT = new Text("+ Thêm mới vật tư y tế");
        txtThemVT.setMouseTransparent(true);
        txtThemVT.getStyleClass().add("txt-button");
        
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
        Rectangle AddGroup = CreateIcon(10,10,"/images/add.png");
        GridPane grid = new GridPane();
        grid.add(AddGroup, 0, 0);
        grid.setAlignment(Pos.CENTER_RIGHT);
        HBox HBoxGroup = new HBox(10);
        HBoxGroup.setHgrow(grid,Priority.ALWAYS);
        HBoxGroup.getChildren().addAll(group, grid);
        HBoxGroup.setPadding(new Insets(15,5,10,5));
                
        VBox vbgroup = new VBox(0);
        vbgroup.getChildren().add(HBoxGroup);
        
        vbgroup.getChildren().add(CreateCatalogItem("Tất cả"));
        sql = "Select Ten_Nhom FROM `Nhom` WHERE Loai LIKE '%thuốc%'";
        crs = conn.getCRS(sql);
        try {
            if(crs.isBeforeFirst())
            {
                while(crs.next())
                {
                    vbgroup.getChildren().add(CreateCatalogItem(crs.getString("Ten_Nhom").toUpperCase()));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vbgroup;
        
    }
    
    private VBox GroupDichVu()
    {
        Text group = new Text("Nhóm");
        group.setId("group");
        Rectangle AddGroup = CreateIcon(10,10,"/images/add.png");
        GridPane grid = new GridPane();
        grid.add(AddGroup, 0, 0);
        grid.setAlignment(Pos.CENTER_RIGHT);
        HBox HBoxGroup = new HBox(10);
        HBoxGroup.setHgrow(grid,Priority.ALWAYS);
        HBoxGroup.getChildren().addAll(group, grid);
        HBoxGroup.setPadding(new Insets(15,5,10,5));

        VBox vbgroup = new VBox(0);
        vbgroup.getChildren().addAll(HBoxGroup);
        
        vbgroup.getChildren().add(CreateCatalogItem("Tất cả"));
        sql = "Select Ten_Nhom FROM `Nhom` WHERE Loai LIKE '%dịch vụ%'";
        crs = conn.getCRS(sql);
        try {
            if(crs.isBeforeFirst())
            {
                while(crs.next())
                {
                    vbgroup.getChildren().add(CreateCatalogItem(crs.getString("Ten_Nhom").toUpperCase()));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return vbgroup;
    }
    
    private VBox GroupVatTu()
    {
        Text group = new Text("Nhóm");
        group.setId("group");
        Rectangle AddGroup = CreateIcon(10,10,"/images/add.png");
        GridPane grid = new GridPane();
        grid.add(AddGroup, 0, 0);
        grid.setAlignment(Pos.CENTER_RIGHT);
        HBox HBoxGroup = new HBox(10);
        HBoxGroup.setHgrow(grid,Priority.ALWAYS);
        HBoxGroup.getChildren().addAll(group, grid);
        HBoxGroup.setPadding(new Insets(15,5,10,5));
        
        VBox vbgroup = new VBox(10);
        vbgroup.getChildren().addAll(HBoxGroup);
        
        vbgroup.getChildren().add(CreateCatalogItem("Tất cả"));
        sql = "Select Ten_Nhom FROM `Nhom` WHERE Loai LIKE '%vật tư%'";
        crs = conn.getCRS(sql);
        try {
            if(crs.isBeforeFirst())
            {
                while(crs.next())
                {
                    vbgroup.getChildren().add(CreateCatalogItem(crs.getString("Ten_Nhom").toUpperCase()));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vbgroup;
    }
    
    private VBox tableThuoc()
    {
        load_DSThuoc();
        TableView<Thuoc> tbThuoc = new TableView();
        tbThuoc.setEditable(false);
        TableColumn maSo = new TableColumn("Mã số");
        maSo.setPrefWidth(100);
        maSo.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("MaThuoc"));
        TableColumn Ten = new TableColumn("Tên");
        Ten.setPrefWidth(150);
        Ten.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("TenThuoc"));
        TableColumn donViTinh = new TableColumn("Đơn vị tính");
        donViTinh.setPrefWidth(110);
        donViTinh.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("DonVi"));
        TableColumn giaBan = new TableColumn("Giá bán");
        giaBan.setPrefWidth(100);
        giaBan.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("GiaBan"));
        TableColumn tonKho = new TableColumn("Tồn kho");
        tonKho.setPrefWidth(80);
        tonKho.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("TonKho"));
        TableColumn macDinhMoiNgay = new TableColumn("Mặc định mỗi ngày");
        macDinhMoiNgay.setPrefWidth(100);
        macDinhMoiNgay.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("LieuLuong"));
        TableColumn cachDung = new TableColumn("Cách dùng");
        cachDung.setPrefWidth(150);
        cachDung.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("CachDung"));
        TableColumn nhomThuoc = new TableColumn("Nhóm thuốc");
        nhomThuoc.setPrefWidth(120);
        nhomThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("Nhom"));
        TableColumn<Thuoc, Thuoc> chucNang = new TableColumn("Chức năng");
        chucNang.setPrefWidth(80);
        chucNang.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
        chucNang.setCellFactory(column -> new TableCell<Thuoc, Thuoc>(){
            @Override
            protected void updateItem(Thuoc thuoc, boolean empty)
            {
                super.updateItem(thuoc, empty);
                if(thuoc == null){
                    setGraphic(null);
                    return;
                }
                HBox hb_chucnang = new HBox(10);
                hb_chucnang.setAlignment(Pos.CENTER);
                Rectangle icon_Xoa = CreateIcon(16,16,"/images/icon-trash.png");
                
                icon_Xoa.setOnMousePressed(e->{
                    XoaThuoc(thuoc);
                });
                Rectangle icon_Sua = CreateIcon(16,16,"/images/action-edit.png");
                icon_Sua.setOnMousePressed(e->{
                    SuaThuoc(thuoc);
                });
                hb_chucnang.getChildren().addAll(icon_Sua, icon_Xoa);
                setGraphic(hb_chucnang);
            }
        });
        
        tbThuoc.setItems(ds_thuoc);
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
    public void CreateStageThemThuoc()
    {
        stage_Child = new Stage();
        stage_Child.setResizable(false);
        CreateSceneThemThuoc();
        stage_Child.setScene(Scene_Child);
        stage_Child.initModality(Modality.APPLICATION_MODAL);
        stage_Child.setTitle("Thêm thuốc");
    }
    public void CreateSceneThemThuoc()
    {
        GridPane TTThuoc = new GridPane();
        TTThuoc.setHgap(10);
        TTThuoc.setVgap(20);
        TTThuoc.setAlignment(Pos.CENTER);
        TTThuoc.setPadding(new Insets(10,0,10,0));
        
        Label lb_maThuoc = new Label("Mã thuốc");
        Label lb_ten = new Label("Tên thuốc");
        Label lb_giaBan = new Label("Giá bán");
        Label lb_cachDung = new Label("Cách dùng");
        Label lb_soluong = new Label("Số lượng mặc định ");
        Label lb_donvi = new Label("Đơn vị tính");
        Label lb_tonKho = new Label("Tồn kho");
        Label lb_nhom = new Label("Nhóm");
        
        TextField tfmaThuoc = new TextField();
        TextField tften = new TextField();
        TextField tfgiaBan = new TextField();
        TextField tfcachDung = new TextField();
        TextField tfsoluong = new TextField();
        TextField tfdonvi = new TextField();
        TextField tftonKho = new TextField();
        ComboBox cmbNhom = new ComboBox();
        cmbNhom.setMaxWidth(Double.MAX_VALUE);
        load_DSNhom();
        cmbNhom.setItems(ds_nhom);
        Text txt_result = new Text();
        txt_result.setTextAlignment(TextAlignment.CENTER);
        txt_result.setFill(Color.RED);
           txt_result.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        Button btnHoanTat = new Button("Hoàn tất");
        btnHoanTat.setAlignment(Pos.CENTER);
        btnHoanTat.setDefaultButton(true);
        btnHoanTat.setOnAction(e->{
            actionThemThuoc(tfmaThuoc, tften, tfgiaBan
            , tfcachDung, tfsoluong, tfdonvi, tftonKho, 
            cmbNhom, txt_result);
        });
        Button btnBoQua = new Button("Bỏ qua");
        btnBoQua.setAlignment(Pos.CENTER);
        btnBoQua.setOnAction(e->{
            stage_Child.close();
        });
        btnHoanTat.setPrefSize(100, 40);
        btnBoQua.setPrefSize(100, 40);
        
        TTThuoc.add(lb_maThuoc, 0, 0);
        TTThuoc.add(tfmaThuoc, 1, 0);
        TTThuoc.add(lb_giaBan, 2, 0);
        TTThuoc.add(tfgiaBan, 3, 0);
        TTThuoc.add(lb_ten, 0, 1);
        TTThuoc.add(tften, 1, 1);
        TTThuoc.add(lb_tonKho, 2, 1);
        TTThuoc.add(tftonKho, 3, 1);
        TTThuoc.add(lb_nhom, 0, 2);
        TTThuoc.add(cmbNhom, 1, 2);
        TTThuoc.add(lb_cachDung, 2, 2);
        TTThuoc.add(tfcachDung, 3, 2);
        TTThuoc.add(lb_donvi, 0, 3);
        TTThuoc.add(tfdonvi, 1, 3);
        TTThuoc.add(lb_soluong, 2, 3);
        TTThuoc.add(tfsoluong, 3, 3);
        TTThuoc.add(txt_result, 0, 4,4,1);
        TTThuoc.add(btnHoanTat, 1, 5);
        TTThuoc.add(btnBoQua, 2, 5);
        Scene_Child = new Scene(TTThuoc,600,400);
        
    }
    public StackPane CreateCatalogItem(String content)
    {
        Rectangle rec = new Rectangle(250,30);
        rec.getStyleClass().add("rec");
        
        Text txt = new Text(content);
        txt.setMouseTransparent(true);
        txt.getStyleClass().add("txt");
        
        Rectangle icon_arrow_right = CreateIcon(9,9,"/images/arrow-right.png");
        Rectangle icon_arrow_down = CreateIcon(9,9,"/images/arrow-down.png");
        Rectangle icon_edit = CreateIcon(16,16,"/images/action-edit.png");
        icon_edit.setMouseTransparent(true);
        
        icon_edit.setVisible(false);
        icon_arrow_right.setVisible(false);
        icon_arrow_down.setVisible(false);
        
        icon_arrow_right.setOnMousePressed(e->{
            icon_arrow_right.setVisible(false);
            icon_arrow_down.setVisible(true);
        });
        icon_arrow_down.setOnMouseClicked(e->{
            icon_arrow_right.setVisible(true);
            icon_arrow_down.setVisible(false);
        });
        rec.setOnMouseMoved(e->{
            icon_edit.setVisible(true);
        });
        rec.setOnMouseExited(e->{
            icon_edit.setVisible(false);
        });
        
        StackPane stk = new StackPane();
        stk.getStyleClass().add("stk");
        stk.setAlignment(Pos.CENTER_LEFT);
        stk.setMargin(txt, new Insets(0,0,0,14));
        stk.setMargin(icon_arrow_right, new Insets(0,0,0,2));
        stk.setMargin(icon_arrow_down, new Insets(0,0,0,2));
        stk.setMargin(icon_edit, new Insets(0,0,0,220));
        
        stk.getChildren().addAll(rec, icon_arrow_right, icon_arrow_down, txt, icon_edit);
                
        return stk;
    }
    public Rectangle CreateIcon(int width, int height, String path)
    {
        Rectangle rec = new Rectangle(width,height);
        rec.getStyleClass().add("icon");
        Image img = new Image(path);
        ImagePattern imgpn = new ImagePattern(img);
        rec.setFill(imgpn);
        return rec;
    }
    public void load_DSThuoc()
    {
        ds_thuoc.clear();
        sql = "SELECT * FROM `thuoc` ORDER BY Ma_Thuoc asc";
        crs = conn.getCRS(sql);
        try {
            if(crs.isBeforeFirst()){
                while(crs.next())
                {
                    Thuoc thuoc = new Thuoc();
                    thuoc.setMaThuoc(crs.getString("Ma_Thuoc"));
                    thuoc.setTenThuoc(crs.getString("Ten_Thuoc"));
                    thuoc.setDonVi(crs.getString("Don_Vi"));
                    thuoc.setGiaBan(crs.getFloat("Gia_Ban"));
                    thuoc.setLieuLuong(crs.getInt("Lieu_Luong_Mac_Dinh"));
                    thuoc.setTonKho(crs.getInt("Ton_Kho"));
                    thuoc.setNhom(crs.getString("Nhom"));
                    thuoc.setCachDung(crs.getString("Cach_Dung"));
                    ds_thuoc.add(thuoc);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void actionThemThuoc(TextField tfmaThuoc, TextField tften, TextField tfgiaBan
            , TextField tfcachDung, TextField tfsoluong, TextField tfdonvi, TextField tftonKho, 
            ComboBox cmbNhom, Text txt_result)
    {
        Thuoc thuoc = new Thuoc();
        if(tfmaThuoc.getText().length() == 0)
        {
            txt_result.setText("Vui lòng nhập mã thuốc ");
            return;
        }
        if(tften.getText().length() == 0)
        {
            txt_result.setText("Tên thuốc không được trống");
            return;
        }
        if(!cmbNhom.getSelectionModel().isEmpty())
        {
            thuoc.setNhom(cmbNhom.getSelectionModel().getSelectedItem().toString());
        }
        if(tfgiaBan.getText().matches("\\d*") == false)
        {
            txt_result.setText("Giá bán không hợp lệ");
            return;
        }
        if(tfsoluong.getText().matches("\\d*") == false)
        {
            txt_result.setText("Số lượng không hợp lệ");
            return;
        }
        if(tftonKho.getText().matches("\\d*") == false)
        {
            txt_result.setText("Số lượng tồn kho không hợp lệ");
            return;
        }
        thuoc.setMaThuoc(tfmaThuoc.getText());
        thuoc.setTenThuoc(tften.getText());
        thuoc.setGiaBan(Double.parseDouble(tfgiaBan.getText()));
        thuoc.setCachDung(tfcachDung.getText());
        thuoc.setTonKho(Integer.parseInt(tftonKho.getText()));
        thuoc.setLieuLuong(Integer.parseInt(tfsoluong.getText()));
        thuoc.setDonVi(tfdonvi.getText());
        sql = "INSERT INTO `Thuoc` VALUES ('"+thuoc.getMaThuoc()+"','"
                +thuoc.getTenThuoc()+"','"+thuoc.getDonVi()+"','"
                +thuoc.getGiaBan()+"','"+thuoc.getTonKho()+"','"
                +thuoc.getLieuLuong()+"','"+thuoc.getCachDung()+"','"+thuoc.getNhom()+"')";
        if(conn.getPS(sql) == 0)
        {
            txt_result.setText("Mã thuốc đã tồn tại");
            return;
        }
        ds_thuoc.add(thuoc);
        stage_Child.close();
        
    }
    public void load_DSNhom()
    {
        ds_nhom.clear();
        sql = "SELECT Ten_Nhom FROM `Nhom` WHERE Loai='Thuốc' ORDER BY id asc";
        crs = conn.getCRS(sql);
        try {
            if(crs.isBeforeFirst())
            {
                while(crs.next())
                {
                    ds_nhom.add(crs.getString("Ten_Nhom").toUpperCase());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void XoaThuoc(Thuoc thuoc)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        alert.setContentText("Bạn chắc chắn muốn xoá "+thuoc.getTenThuoc()+"?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            ds_thuoc.remove(thuoc);
            sql = "DELETE FROM `Thuoc` WHERE Ma_Thuoc='"+thuoc.getMaThuoc()+"';";
            conn.update(sql);
        }
    }
    public void SuaThuoc(Thuoc thuoc)
    {
        CreateStageSuaThuoc(thuoc);
        stage_Child.centerOnScreen();
        stage_Child.showAndWait();
    }
    public void CreateStageSuaThuoc(Thuoc thuoc)
    {
        stage_Child = new Stage();
        stage_Child.setResizable(true);
        CreateSceneSuaThuoc(thuoc);
        stage_Child.setScene(Scene_Child);
        stage_Child.initModality(Modality.APPLICATION_MODAL);
        stage_Child.setTitle("Sửa thông tin thuốc");
    }
    public void CreateSceneSuaThuoc(Thuoc thuoc)
    {
        GridPane TTThuoc = new GridPane();
        TTThuoc.setHgap(10);
        TTThuoc.setVgap(20);
        TTThuoc.setAlignment(Pos.CENTER);
        TTThuoc.setPadding(new Insets(10,0,10,0));
        
        Label lb_maThuoc = new Label("Mã thuốc");
        Label lb_ten = new Label("Tên thuốc");
        Label lb_giaBan = new Label("Giá bán");
        Label lb_cachDung = new Label("Cách dùng");
        Label lb_soluong = new Label("Số lượng mặc định ");
        Label lb_donvi = new Label("Đơn vị tính");
        Label lb_tonKho = new Label("Tồn kho");
        Label lb_nhom = new Label("Nhóm");
        
        TextField tfmaThuoc = new TextField();
        tfmaThuoc.setText(thuoc.getMaThuoc());
        TextField tften = new TextField();
        tften.setText(thuoc.getTenThuoc());
        TextField tfgiaBan = new TextField();
        tfgiaBan.setText(Double.toString(thuoc.getGiaBan()));
        TextField tfcachDung = new TextField();
        tfcachDung.setText(thuoc.getCachDung());
        TextField tfsoluong = new TextField();
        tfsoluong.setText(Integer.toString(thuoc.getLieuLuong()));
        TextField tfdonvi = new TextField();
        tfdonvi.setText(thuoc.getDonVi());
        TextField tftonKho = new TextField();
        tftonKho.setText(Integer.toString(thuoc.getTonKho()));
        ComboBox cmbNhom = new ComboBox();
        cmbNhom.setMaxWidth(Double.MAX_VALUE);
        cmbNhom.setValue(thuoc.getNhom());
        load_DSNhom();
        cmbNhom.setItems(ds_nhom);
        Text txt_result = new Text();
        txt_result.setTextAlignment(TextAlignment.CENTER);
        txt_result.setFill(Color.RED);
        txt_result.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        Button btnHoanTat = new Button("Hoàn tất");
        btnHoanTat.setAlignment(Pos.CENTER);
        btnHoanTat.setDefaultButton(true);
        btnHoanTat.setOnAction(e->{
            actionSuaThuoc(tfmaThuoc, tften, tfgiaBan
            , tfcachDung, tfsoluong, tfdonvi, tftonKho, 
            cmbNhom, txt_result,thuoc.getMaThuoc());
        });
        Button btnBoQua = new Button("Bỏ qua");
        btnBoQua.setAlignment(Pos.CENTER);
        btnBoQua.setOnAction(e->{
            stage_Child.close();
        });
        btnHoanTat.setPrefSize(100, 40);
        btnBoQua.setPrefSize(100, 40);
        
        TTThuoc.add(lb_maThuoc, 0, 0);
        TTThuoc.add(tfmaThuoc, 1, 0);
        TTThuoc.add(lb_giaBan, 2, 0);
        TTThuoc.add(tfgiaBan, 3, 0);
        TTThuoc.add(lb_ten, 0, 1);
        TTThuoc.add(tften, 1, 1);
        TTThuoc.add(lb_tonKho, 2, 1);
        TTThuoc.add(tftonKho, 3, 1);
        TTThuoc.add(lb_nhom, 0, 2);
        TTThuoc.add(cmbNhom, 1, 2);
        TTThuoc.add(lb_cachDung, 2, 2);
        TTThuoc.add(tfcachDung, 3, 2);
        TTThuoc.add(lb_donvi, 0, 3);
        TTThuoc.add(tfdonvi, 1, 3);
        TTThuoc.add(lb_soluong, 2, 3);
        TTThuoc.add(tfsoluong, 3, 3);
        TTThuoc.add(txt_result, 0, 4,4,1);
        TTThuoc.add(btnHoanTat, 1, 5);
        TTThuoc.add(btnBoQua, 2, 5);
        Scene_Child = new Scene(TTThuoc,600,400);
    }
    public void actionSuaThuoc(TextField tfmaThuoc, TextField tften, TextField tfgiaBan
            , TextField tfcachDung, TextField tfsoluong, TextField tfdonvi, TextField tftonKho, 
            ComboBox cmbNhom, Text txt_result, String mathuoc)
    {
        sql = "UPDATE `thuoc` SET `Ma_Thuoc`='"+tfmaThuoc.getText()
                +"', `Ten_Thuoc`='"+tften.getText()
                +"', `Don_Vi`='"+tfdonvi.getText()
                +"', `Gia_Ban`='"+Double.parseDouble(tfgiaBan.getText())
                +"', `Ton_Kho`='"+Integer.parseInt(tftonKho.getText())
                +"', `Lieu_Luong_Mac_Dinh`='"+Integer.parseInt(tfsoluong.getText())
                +"', `Cach_Dung`='"+tfcachDung.getText()
                +"', `Nhom`='"+cmbNhom.getSelectionModel().getSelectedItem().toString()+"'"
                +" WHERE `Ma_Thuoc`='"+mathuoc+"'";
        if(conn.getPS(sql) == 0)
        {
            //txt_result.setText("Vui lòng thử lại sau!");
            txt_result.setText(sql);
            return;
        }
        load_DSThuoc();
        stage_Child.close();
    }
    public void actionTimKiemThuoc(String keyword)
    {
        ds_thuoc.clear();
        sql = "SELECT * FROM `thuoc` WHERE `Ma_Thuoc` LIKE '%"+keyword+"%' OR `Ten_Thuoc` LIKE '%"+keyword+"%' ORDER BY Ma_Thuoc asc";
        crs = conn.getCRS(sql);
        try {
            if(crs.isBeforeFirst())
            {
                while(crs.next())
                {
                    Thuoc thuoc = new Thuoc();
                    thuoc.setMaThuoc(crs.getString("Ma_Thuoc"));
                    thuoc.setTenThuoc(crs.getString("Ten_Thuoc"));
                    thuoc.setDonVi(crs.getString("Don_Vi"));
                    thuoc.setGiaBan(crs.getFloat("Gia_Ban"));
                    thuoc.setLieuLuong(crs.getInt("Lieu_Luong_Mac_Dinh"));
                    thuoc.setTonKho(crs.getInt("Ton_Kho"));
                    thuoc.setNhom(crs.getString("Nhom"));
                    thuoc.setCachDung(crs.getString("Cach_Dung"));
                    ds_thuoc.add(thuoc);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
