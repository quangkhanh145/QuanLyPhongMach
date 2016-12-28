/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyphongmach;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import static quanlyphongmach.DonThuocPanel.Scene;
import quanlyphongmach.model.Thuoc;

/**
 *
 * @author Quang Khanh
 */
public class KeToaPanel extends Scene{
    static BorderPane Scene = new BorderPane();
    private ConnectToDatabase conn = new ConnectToDatabase();
    private CachedRowSet crs;
    public KeToaPanel()
    {
        super(Scene);      
        CreateScene();
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/main.css").toExternalForm());  
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
//        VBox QuaTrinhDieuTri = CreateBox("Quá trình điều trị","Ở đây sẽ hiển thị các toa cũ (bệnh sử) của bệnh nhân để bác sĩ chọn làm mẫu khi kê toa mới.","","");
//        VBox DienTienBenh = CreateBox("Diễn tiến bệnh", "Ở đây sẽ hiển thị các thông tin diễn tiến bệnh của bệnh nhân. Nhấp chuột vào nút `Thêm` để thêm mới diễn tiến bệnh.","","Thêm");
//        VBox CacNhomThuoc = CreateBox("Các nhóm thuốc","",sql_thuoc,"");
//        VBox Thuoc = CreateBox("Thuốc","","","");

//        flow_pane.getChildren().addAll(QuaTrinhDieuTri, DienTienBenh, CacNhomThuoc, Thuoc);
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
        txt_content1.setText("Ở đây sẽ hiển thị các toa cũ (bệnh sử) của bệnh nhân để bác sĩ chọn làm mẫu khi kê toa mới.");
        content_QuaTrinhDieuTri.getChildren().add(txt_content1);
        QuaTrinhDieuTri.getChildren().addAll(CreateTaskbar("Quá trình điều trị",""),content_QuaTrinhDieuTri);
        
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
        txt_content2.setText("Ở đây sẽ hiển thị các thông tin diễn tiến bệnh của bệnh nhân. Nhấp chuột vào nút `Thêm` để thêm mới diễn tiến bệnh.");
        content_DienTienBenh.getChildren().add(txt_content2);
        DienTienBenh.getChildren().addAll(CreateTaskbar("Diễn tiến bệnh","Thêm"),content_DienTienBenh);
        
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
                            String query = "Select Ten_Thuoc FROM `Thuoc` WHERE"
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
                                    Text txt1 = new Text(tenthuoc.toUpperCase());
                                    txt1.setFill(Color.web("#000000"));
                                    txt1.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, 12));
                                    txt1.setMouseTransparent(true);
                                    txt1.setWrappingWidth(160);
                                    
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
        Label lb_cannang = new Label("Cân nặng: ");
        lb_cannang.getStyleClass().add("label-donthuoc");
        Label lb_chandoan = new Label("Chẩn đoán : ");
        lb_chandoan.getStyleClass().add("label-donthuoc");
        
        Text txt_ten = new Text();
        Text txt_namsinh = new Text();
        Text txt_dt = new Text();
        Text txt_diachi = new Text();
        Text txt_gioitinh = new Text();
        Text txt_cannang = new Text();
        TextField tf_chandoan = new TextField();
        tf_chandoan.getStyleClass().add("textfield-donthuoc");
        tf_chandoan.setPrefWidth(350);
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
        info.add(lb_cannang, 0, 5);
        info.add(txt_cannang, 1, 5);
        info.add(lb_chandoan, 0, 6);
        info.add(tf_chandoan, 1, 6);
        
        VBox vb_tb_thongtinthuoc = new VBox(0);
        TableView tb_thongtinthuoc = new TableView();
        tb_thongtinthuoc.setPrefSize(500, 200);
        TableColumn tenthuocCol = new TableColumn("Tên thuốc");
        tenthuocCol.setPrefWidth(100);
        TableColumn soluongCol = new TableColumn("Số lượng");
        soluongCol.setPrefWidth(70);
        TableColumn donviCol = new TableColumn("Đơn vị");
        donviCol.setPrefWidth(50);
        TableColumn cachdungCol = new TableColumn("Cách dùng");
        cachdungCol.setPrefWidth(280);
        
        tb_thongtinthuoc.getColumns().addAll(tenthuocCol, soluongCol, donviCol, cachdungCol);
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
        Text txt_total = new Text("100000");
        //txt_total.set(Color.web("#FF8000"));
        HBox hb_total = new HBox(10);
        hb_total.getChildren().add(txt_total);
        hb_total.setAlignment(Pos.CENTER_RIGHT);
        hb_total.setStyle("-fx-background-color:#FF8000;");
        hb_total.setPrefWidth(80);
        bang_dieu_khien.getChildren().addAll(save, save_print, total, hb_total);
        bang_dieu_khien.setPadding(new Insets(0,0,30,10));
        bang_dieu_khien.setHgrow(total, Priority.ALWAYS);
        
        wrap_content.getChildren().addAll(donthuoc, bang_dieu_khien);
        wrap.setContent(wrap_content);
        return wrap;
    }
}
