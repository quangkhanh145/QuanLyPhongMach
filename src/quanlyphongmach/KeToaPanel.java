/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyphongmach;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
import javax.sql.rowset.CachedRowSet;
import quanlyphongmach.model.BenhNhan;
import quanlyphongmach.model.ChiTietDonThuoc;
import quanlyphongmach.model.Thuoc;

/**
 *
 * @author Quang Khanh
 */
public class KeToaPanel extends Scene{
    private static BorderPane Scene = new BorderPane();;
    private ConnectToDatabase conn = new ConnectToDatabase();
    private CachedRowSet crs;
    private BenhNhan benhnhan;
    private TableView tb_thongtinthuoc;
    private Text txt_total;
    private final ObservableList<Thuoc> thuoc = FXCollections.observableArrayList();
    private final ObservableList<ChiTietDonThuoc> ct_donthuoc = FXCollections.observableArrayList();
    public KeToaPanel()
    {
        super(Scene);
        CreateScene();
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/main.css").toExternalForm());
        
    }
    public void setBenhNhan(BenhNhan benhnhan)
    {
        this.benhnhan = benhnhan;
        ct_donthuoc.clear();
        CreateScene();
    }
    private void CreateScene()
    {
        Scene.setTop(Header());
        Scene.setCenter(ThongTinChuaBenh());
        Scene.setLeft(DonThuoc());
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
        DonThuoc.setId("DonThuoc");
        DonThuoc.getStyleClass().add("label-main-menu");
        Label HoaDon = new Label("Hoá đơn");
        HoaDon.getStyleClass().add("label-main-menu");
        HoaDon.setOnMouseClicked(e->
        {
            SceneController.setHoaDonPanel();
        });
        Label Thuoc = new Label("Thuốc");
        Thuoc.setOnMouseClicked(e->
        {
            SceneController.setThuocPanel();
        });
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
        mainMenu.getChildren().addAll(TrangChu, BenhNhan, DonThuoc, HoaDon, 
                Thuoc, NhapKho, BaoCao, CauHinh);
        
        BorderPane header = new BorderPane();
        header.getStyleClass().add("header");
        
        Label lbDanhSachToaThuoc = new Label("Danh sách toa thuốc");
        lbDanhSachToaThuoc.getStyleClass().add("label_submenu");
        lbDanhSachToaThuoc.setAlignment(Pos.TOP_LEFT);
        lbDanhSachToaThuoc.setOnMouseClicked(e->{
            SceneController.setDonThuocPanel();
        });
        
        header.setTop(mainMenu);
        header.setCenter(lbDanhSachToaThuoc);
        header.setBottom(submenuVietToaThuoc());
        
        return header;
    }
    private HBox submenuVietToaThuoc()
    {
        Text txtDanhSachToaThuoc = new Text("Viết toa thuốc");
        txtDanhSachToaThuoc.getStyleClass().add("text-submenu");
        
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.getChildren().addAll(txtDanhSachToaThuoc);
        
        return submenu;
    }
    public FlowPane ThongTinChuaBenh()
    {
        FlowPane flow_pane = new FlowPane();
        flow_pane.setPadding(new Insets(10,10,0,10));
        flow_pane.setVgap(20);
        flow_pane.setHgap(20);
        String sql_thuoc = "SELECT Ten_Nhom FROM `Nhom` WHERE Loai='Thuốc' ORDER BY id asc";
        Text txt_content1 = new Text();
        txt_content1.setFont(Font.font(13));
        txt_content1.setWrappingWidth(350);
        Text txt_content2 = new Text();
        txt_content2.setFont(Font.font(13));
        txt_content2.setWrappingWidth(350);
        //box quá trình điều trị
        VBox QuaTrinhDieuTri = new VBox(0);
        QuaTrinhDieuTri.setPrefWidth(350);
        FlowPane content_QuaTrinhDieuTri = new FlowPane();
        content_QuaTrinhDieuTri.getStyleClass().add("content");
        content_QuaTrinhDieuTri.setPrefHeight(200);
        content_QuaTrinhDieuTri.setPrefWrapLength(350);
        content_QuaTrinhDieuTri.setPadding(new Insets(10,0,10,7));
        content_QuaTrinhDieuTri.setHgap(5);
        content_QuaTrinhDieuTri.setVgap(5);
        
        QuaTrinhDieuTri.getChildren().addAll(CreateTaskbar("Quá trình điều trị",""),content_QuaTrinhDieuTri);
        if(benhnhan != null)
        {
            String sql_lichsubenh = "SELECT Chan_Doan, Loi_Dan, Ngay_Lap FROM don_thuoc WHERE MaBN = '"+benhnhan.getMaBN()+"' ORDER BY Ma_Don_Thuoc ASC";
            crs = conn.getCRS(sql_lichsubenh);
            try {
                if(crs.isBeforeFirst())
                {
                    while(crs.next())
                    {
                        Rectangle rec = new Rectangle(83,30);
                        rec.getStyleClass().add("icon-2");
                        rec.setFill(Color.web("#C0C0C0"));
                        Date ngaykham = crs.getDate("Ngay_Lap");
                        DateFormat dateFormat = new SimpleDateFormat("DD/MM/yyyy");
                        String str_ngaykham = dateFormat.format(ngaykham).toString();
                        String str_chandoan = crs.getString("Chan_Doan");
                        String str_loidan = crs.getString("Loi_Dan");
                        Text txt = new Text(str_ngaykham);
                        txt.setFill(Color.web("#000000"));
                        txt.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, 12));
                        txt.setMouseTransparent(true);
                        txt.setWrappingWidth(83);

                        StackPane stack1 = new StackPane();
                        
                        stack1.getChildren().addAll(rec, txt);
                        stack1.setMargin(txt,new Insets(3,0,0,5));
                        stack1.setAlignment(Pos.CENTER_LEFT);
                        content_QuaTrinhDieuTri.getChildren().add(stack1);
                        
                        rec.setOnMouseClicked(e->{
                            
                        });
                        
                    }
                }else{
                    content_QuaTrinhDieuTri.getChildren().add(txt_content1);
                    txt_content1.setText("Ở đây sẽ hiển thị các toa cũ (bệnh sử) của bệnh nhân để bác sĩ chọn làm mẫu khi kê toa mới.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(KeToaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //box diễn tiến bệnh
        VBox DienTienBenh = new VBox(0);
        DienTienBenh.setPrefWidth(350);
        FlowPane content_DienTienBenh = new FlowPane();
        content_DienTienBenh.getStyleClass().add("content");
        content_DienTienBenh.setPrefHeight(200);
        content_DienTienBenh.setPrefWrapLength(350);
        content_DienTienBenh.setPadding(new Insets(10,0,10,7));
        content_DienTienBenh.setHgap(5);
        content_DienTienBenh.setVgap(5);
        content_DienTienBenh.getChildren().add(txt_content2);
        DienTienBenh.getChildren().addAll(CreateTaskbar("Diễn tiến bệnh","Thêm"),content_DienTienBenh);
        txt_content2.setText("Ở đây sẽ hiển thị các thông tin diễn tiến bệnh của bệnh nhân. Nhấp chuột vào nút `Thêm` để thêm mới diễn tiến bệnh.");
        
        //box thuốc
        VBox Thuoc = new VBox(0);
        Thuoc.setPrefWidth(355);
        FlowPane content_Thuoc = new FlowPane();
        content_Thuoc.getStyleClass().add("content");
        content_Thuoc.setPrefHeight(200);
        content_Thuoc.setPrefWrapLength(355);
        content_Thuoc.setPadding(new Insets(10,0,10,7));
        content_Thuoc.setHgap(5);
        content_Thuoc.setVgap(5);
        Thuoc.getChildren().addAll(CreateTaskbar("Thuốc",""),content_Thuoc);
        
        
        //box các nhóm thuốc
        VBox CacNhomThuoc = new VBox(0);
        CacNhomThuoc.setPrefWidth(350);
        FlowPane content_CacNhomThuoc = new FlowPane();
        content_CacNhomThuoc.getStyleClass().add("content");
        content_CacNhomThuoc.setPrefHeight(200);
        content_CacNhomThuoc.setPrefWrapLength(350);
        content_CacNhomThuoc.setPadding(new Insets(10,0,10,7));
        content_CacNhomThuoc.setHgap(5);
        content_CacNhomThuoc.setVgap(5);
        CacNhomThuoc.getChildren().addAll(CreateTaskbar("Các nhóm thuốc",""),content_CacNhomThuoc);
        crs = conn.getCRS(sql_thuoc);
        try {
            if(crs.isBeforeFirst())
            {
                while(crs.next())
                {
                    Rectangle rec = new Rectangle(165,30);
                    rec.getStyleClass().add("icon-2");
                    rec.setFill(Color.web("#C0C0C0"));
                    String tennhom = crs.getString("Ten_Nhom");
                    Text txt = new Text(tennhom.toUpperCase());
                    txt.setFill(Color.web("#000000"));
                    txt.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, 13.5));
                    txt.setMouseTransparent(true);

                    StackPane stack = new StackPane();
                    stack.getChildren().addAll(rec, txt);
                    stack.setAlignment(Pos.CENTER_LEFT);
                    stack.setMargin(txt,new Insets(3,0,0,5));
                    content_CacNhomThuoc.getChildren().add(stack);
                    rec.setOnMouseClicked(e->{
                        try {
                            content_Thuoc.getChildren().clear();
                            String query = "Select Ma_Thuoc, Ten_Thuoc, Don_Vi, Cach_Dung, Gia_Ban FROM `Thuoc` WHERE"
                                    + " `Nhom` LIKE '%"+tennhom+"%'";
                            CachedRowSet crs_thuoc = conn.getCRS(query);
                            if(crs_thuoc.isBeforeFirst())
                            {
                                while(crs_thuoc.next())
                                {
                                    Rectangle rec1 = new Rectangle(165,30);
                                    rec1.getStyleClass().add("icon-2");
                                    rec1.setFill(Color.web("#C0C0C0"));
                                    String tenthuoc = crs_thuoc.getString("Ten_Thuoc");
                                    String mathuoc = crs_thuoc.getString("Ma_Thuoc");
                                    String donvi = crs_thuoc.getString("Don_Vi");
                                    String cachdung = crs_thuoc.getString("Cach_Dung");
                                    Double giaban = crs_thuoc.getDouble("Gia_Ban");
                                    Text txt1 = new Text(tenthuoc.toUpperCase());
                                    txt1.setFill(Color.web("#000000"));
                                    txt1.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, 12));
                                    txt1.setMouseTransparent(true);
                                    txt1.setWrappingWidth(160);
                                    
                                    rec1.setOnMouseClicked(event->{
                                        ChiTietDonThuoc ct = new ChiTietDonThuoc();
                                        ct.setMa_Thuoc(mathuoc);
                                        ct.setTen_Thuoc(tenthuoc);
                                        ct.setSo_Luong(1);
                                        ct.setDon_Vi(donvi);
                                        ct.setCach_Dung(cachdung);
                                        ct.setGia_Ban(giaban);
                                        for(ChiTietDonThuoc thuoc : ct_donthuoc)
                                        {
                                            if(thuoc.getMa_Thuoc().equalsIgnoreCase(ct.getMa_Thuoc()))
                                            {
                                                thuoc.setSo_Luong(thuoc.getSo_Luong() + 1);
                                                tb_thongtinthuoc.refresh();
                                                txt_total.setText(updateTienThuoc().toString());
                                                return;
                                            }
                                        }
                                        ct_donthuoc.add(ct);
                                        txt_total.setText(updateTienThuoc().toString());
                                    });
                                    
                                    StackPane stack1 = new StackPane();
                                    stack1.getChildren().addAll(rec1, txt1);
                                    stack1.setMargin(txt1,new Insets(3,0,0,5));
                                    content_Thuoc.getChildren().add(stack1);
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(KeToaPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KeToaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        flow_pane.getChildren().addAll(QuaTrinhDieuTri, DienTienBenh, CacNhomThuoc, Thuoc);
        return flow_pane;
    }
    public HBox CreateTaskbar(String title, String button)
    {
        HBox hbox = new HBox(10);
        hbox.setPrefHeight(30);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(0,10,0,5));
        hbox.getStyleClass().add("taskbar");
        Rectangle icon = CreateIcon(16,16,"/images/action-sebill.png");
        Text txt_title = new Text(title);
        txt_title.getStyleClass().add("title");
        hbox.getChildren().addAll(icon, txt_title);
        if(button.length()!=0)
        {
            Text btn = new Text(button);
            btn.getStyleClass().add("txt-button-tt");
            StackPane stack = new StackPane();
            stack.getChildren().add(btn);
            stack.setAlignment(Pos.CENTER_RIGHT);
            hbox.getChildren().add(stack);
            hbox.setHgrow(stack, Priority.ALWAYS);
        }
        return hbox;
    }
    public Rectangle CreateIcon(int width, int height, String path)
    {
        Rectangle rec = new Rectangle(width,height);
        
        Image img = new Image(path);
        ImagePattern imgpn = new ImagePattern(img);
        rec.setFill(imgpn);
        return rec;
    }
    public ScrollPane DonThuoc()
    {
        VBox donthuoc = new VBox();
        donthuoc.setPrefWidth(490);
        donthuoc.setPadding(new Insets(10,10,10,10));
        donthuoc.getStyleClass().add("don-thuoc");
        
        HBox hb_label = new HBox();
        Label label = new Label("ĐƠN THUỐC");
        label.setFont(Font.font("San Francisco", FontWeight.BOLD, 15));
        hb_label.setAlignment(Pos.CENTER);
        hb_label.getChildren().add(label);
        
        GridPane info = new GridPane();
        info.setVgap(10);
        Label lb_ten = new Label("Họ tên: ");
        lb_ten.getStyleClass().add("label-donthuoc");
        Label lb_namsinh = new Label("Năm sinh: ");
        lb_namsinh.getStyleClass().add("label-donthuoc");
        Label lb_dt = new Label("Điện thoại: ");
        lb_dt.getStyleClass().add("label-donthuoc");
        Label lb_diachi = new Label("Địa chỉ: ");
        lb_diachi.getStyleClass().add("label-donthuoc");
        Label lb_gioitinh = new Label("Giới tính : ");
        lb_gioitinh.getStyleClass().add("label-donthuoc");
        Label lb_chandoan = new Label("Chẩn đoán : ");
        lb_chandoan.getStyleClass().add("label-donthuoc");
        
        Text txt_ten = new Text();
        txt_ten.setStyle("-fx-font-weight: bold;");
        Text txt_namsinh = new Text();
        Text txt_dt = new Text();
        Text txt_diachi = new Text();
        Text txt_gioitinh = new Text();
        TextField tf_chandoan = new TextField();
        tf_chandoan.getStyleClass().add("textfield-donthuoc");
        tf_chandoan.setPrefWidth(350);
        
        if(benhnhan != null)
        {
            txt_ten.setText(benhnhan.getTen());
            txt_namsinh.setText(benhnhan.getNgaySinh());
            txt_dt.setText(benhnhan.getSDT());
            txt_diachi.setText(benhnhan.getDiaChi());
            txt_gioitinh.setText(benhnhan.getGioiTinh());
        }
        
        info.add(lb_ten, 0, 0);
        info.add(txt_ten, 1, 0);
        info.add(lb_namsinh, 0, 1);
        info.add(txt_namsinh, 1, 1);
        info.add(lb_dt, 0, 2);
        info.add(txt_dt, 1, 2);
        info.add(lb_gioitinh, 0, 3);
        info.add(txt_gioitinh, 1, 3);
        info.add(lb_diachi, 0, 4);
        info.add(txt_diachi, 1, 4);
        info.add(lb_chandoan, 0, 5);
        info.add(tf_chandoan, 1, 5);
        
        VBox vb_tb_thongtinthuoc = new VBox(0);
        tb_thongtinthuoc = new TableView();
        tb_thongtinthuoc.setPrefSize(500, 200);
        TableColumn tenthuocCol = new TableColumn("Tên thuốc");
        tenthuocCol.setPrefWidth(100);
        TableColumn soluongCol = new TableColumn("Số lượng");
        soluongCol.setPrefWidth(70);
        TableColumn donviCol = new TableColumn("Đơn vị");
        donviCol.setPrefWidth(50);
        TableColumn dongiaCol = new TableColumn("Đơn giá");
        dongiaCol.setPrefWidth(100);
        TableColumn cachdungCol = new TableColumn("Cách dùng");
        cachdungCol.setPrefWidth(202);
        TableColumn<ChiTietDonThuoc, ChiTietDonThuoc> chucnangCol = new TableColumn();
        chucnangCol.setPrefWidth(35);
        
        tenthuocCol.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("Ten_Thuoc"));
        soluongCol.setCellValueFactory(new PropertyValueFactory<Thuoc,Integer>("So_Luong"));
        donviCol.setCellValueFactory(new PropertyValueFactory<Thuoc,Integer>("Don_Vi"));
        dongiaCol.setCellValueFactory(new PropertyValueFactory<Thuoc,Double>("Gia_Ban"));
        cachdungCol.setCellValueFactory(new PropertyValueFactory<Thuoc,String>("Cach_Dung"));
        chucnangCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
        chucnangCol.setCellFactory(column -> new TableCell<ChiTietDonThuoc, ChiTietDonThuoc>(){
            @Override
            protected void updateItem(ChiTietDonThuoc ct, boolean empty)
            {
                super.updateItem(ct, empty);
                if(ct == null){
                    setGraphic(null);
                    return;
                }
                HBox hb_chucnang = new HBox(10);
                hb_chucnang.setAlignment(Pos.CENTER);
                
                Rectangle icon_Xoa = CreateIcon(16,16,"/images/icon-trash.png");
                icon_Xoa.getStyleClass().add("icon");
                Tooltip.install(icon_Xoa, new Tooltip("Xoá"));
                
                icon_Xoa.setOnMousePressed(e->{
                    ct_donthuoc.remove(ct);
                    txt_total.setText(updateTienThuoc().toString());
                });
                hb_chucnang.getChildren().addAll(icon_Xoa);
                setGraphic(hb_chucnang);
            }
        });
        
        tb_thongtinthuoc.getColumns().addAll(tenthuocCol, soluongCol, donviCol, dongiaCol, cachdungCol, chucnangCol);
        tb_thongtinthuoc.setItems(ct_donthuoc);
        vb_tb_thongtinthuoc.getChildren().add(tb_thongtinthuoc);
        vb_tb_thongtinthuoc.setPadding(new Insets(10,0,0,0));
        
        GridPane grid_footer = new GridPane();
        grid_footer.setPadding(new Insets(10,0,0,10));
        grid_footer.setHgap(80);
        Text txt_loidan = new Text("Lời dặn");
        txt_loidan.getStyleClass().add("label-donthuoc");
        TextArea ta_loidan = new TextArea();
        ta_loidan.setWrapText(true);
        ta_loidan.setFont(Font.font(13.5));
        ta_loidan.setPrefSize(200, 100);
        ta_loidan.getStyleClass().add("text-area-loidan");
        
        String str_date = "Ngày "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"))
                +" tháng "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM"))
                +" năm "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
        
        Text date = new Text(str_date);
        date.getStyleClass().add("label-donthuoc");
        date.setWrappingWidth(180);
        
        Text txt_chucvi = new Text("Bác Sĩ");
        txt_chucvi.setFont(Font.font("San Francisco", FontWeight.BOLD, 14.5));
        Text txt_tenbs = new Text("Quang Khánh");
        txt_tenbs.setFont(Font.font("San Francisco", FontWeight.BOLD, 14.5));
        
        grid_footer.add(txt_loidan, 0, 0, 1,2);
        grid_footer.add(ta_loidan, 0, 2);
        grid_footer.add(date, 1, 0,1,2);
        grid_footer.add(txt_chucvi, 1, 2);
        grid_footer.add(txt_tenbs, 1, 2);
        grid_footer.setValignment(txt_chucvi,VPos.TOP);
        grid_footer.setHalignment(txt_chucvi,HPos.CENTER);
        grid_footer.setValignment(txt_tenbs,VPos.BOTTOM);
        grid_footer.setHalignment(txt_tenbs,HPos.CENTER);
        
        donthuoc.getChildren().addAll(hb_label, info, vb_tb_thongtinthuoc, grid_footer);
        
        ScrollPane wrap = new ScrollPane();
        wrap.setPrefWidth(520);
        wrap.setPadding(new Insets(10,0,0,10));
        wrap.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        VBox wrap_content = new VBox(10);
        
        HBox bang_dieu_khien = new HBox(10);
        Button save = new Button("Lưu");
        Button save_print = new Button("Lưu và in");
        Label total = new Label("Tổng tiền thuốc");
        total.setFont(Font.font(13.5));
        txt_total = new Text("0");
        HBox hb_total = new HBox(10);
        hb_total.getChildren().add(txt_total);
        hb_total.setAlignment(Pos.CENTER_RIGHT);
        hb_total.setStyle("-fx-background-color:#FF8000;");
        hb_total.setPrefWidth(80);
        bang_dieu_khien.getChildren().addAll(save, save_print, total, hb_total);
        bang_dieu_khien.setPadding(new Insets(0,0,30,10));
        bang_dieu_khien.setHgrow(total, Priority.ALWAYS);
        
        save.setOnAction(e->{
            try {
                String sql = "INSERT INTO `don_thuoc` VALUES ('',"
                        + "'"+benhnhan.getMaBN()+"',"
                        + "'"+tf_chandoan.getText()+"',"
                        + "'"+ta_loidan.getText()+"',"
                        + "'"+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)+"',"
                        +"'"+"',"
                        + "'"+updateTienThuoc()+"');";
                conn.getPS(sql);
                sql = "SELECT `Ma_Don_Thuoc` FROM `don_thuoc` ORDER BY `Ma_Don_Thuoc` DESC LIMIT 1;";
                crs = conn.getCRS(sql);
                if(crs.next())
                {
                    String madonthuoc = crs.getString("Ma_Don_Thuoc");
                    String insert_sql = "INSERT INTO `chi_tiet_don_thuoc` VALUES ";
                    for(ChiTietDonThuoc thuoc: ct_donthuoc)
                    {
                        
                        insert_sql += "("
                                +"'"+madonthuoc+"',"
                                + "'"+thuoc.getMa_Thuoc()+"',"
                                + "'"+thuoc.getSo_Luong()+"',"
                                + "'"+thuoc.getGia_Ban()+"',"
                                + "'"+thuoc.getCach_Dung()+"'),";
                    }
                    insert_sql = insert_sql.substring(0, insert_sql.length() - 1);
                    if(conn.getPS(insert_sql) != 0)
                    {
                        SceneController.setDonThuocPanel();
                    } else{
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Ooops, there was an error!");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(KeToaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        wrap_content.getChildren().addAll(donthuoc, bang_dieu_khien);
        wrap.setContent(wrap_content);
        return wrap;
    }
    public Double updateTienThuoc()
    {
        double tienthuoc = 0.0;
        for(ChiTietDonThuoc thuoc: ct_donthuoc)
        {
            tienthuoc += thuoc.getGia_Ban() * thuoc.getSo_Luong();
        }
        return tienthuoc;
    }
}
