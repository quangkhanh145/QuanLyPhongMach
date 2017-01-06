package quanlyphongmach;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
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
import javafx.scene.text.Text;
import javafx.util.Callback;
import javax.sql.rowset.CachedRowSet;
import static quanlyphongmach.NhapKhoPanel.Scene;
import quanlyphongmach.model.TaiKhoan;

/**
 *
 * @author Quang Khanh
 */
public class CauHinhPanel extends Scene{
    private static BorderPane Scene = new BorderPane();
    private ObservableList<TaiKhoan> ds_nguoidung = FXCollections.observableArrayList();
    String sql;
    private ConnectToDatabase conn = new ConnectToDatabase();
    private CachedRowSet crs;
    public CauHinhPanel()
    {
        super(Scene);
        CreateScene();
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/main.css").toExternalForm());
    }
    private void CreateScene()
    {
        Scene.setTop(Header());
        Scene.setLeft(GroupNguoiDung());
        Scene.setCenter(tb_NguoiDung());
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
        NhapKho.getStyleClass().add("label-main-menu");
        NhapKho.setOnMouseClicked(e->
        {
            SceneController.setNhapKhoPanel();
        });
        Label BaoCao = new Label("Báo cáo");
        BaoCao.getStyleClass().add("label-main-menu");
        Label CauHinh = new Label("Cấu hình");
        CauHinh.setId("CauHinh");
        CauHinh.getStyleClass().add("label-main-menu");
        
        HBox mainMenu = new HBox(50);
        mainMenu.getStyleClass().add("menu");
        mainMenu.setPadding(new Insets(10,20,10,20));
        mainMenu.setAlignment(Pos.TOP_CENTER);
        mainMenu.getChildren().addAll(TrangChu, BenhNhan, DonThuoc, HoaDon, 
                Thuoc, NhapKho, BaoCao, CauHinh);
                
        Label lb_submenu_1 = new Label("Tài khoản người dùng");
        lb_submenu_1.getStyleClass().add("label_submenu");
        
        HBox label_hbox = new HBox(10);
        label_hbox.setAlignment(Pos.TOP_CENTER);
        label_hbox.getChildren().addAll(lb_submenu_1);
        
        BorderPane header = new BorderPane();
        header.getStyleClass().add("header");
        
        header.setTop(mainMenu);
        header.setCenter(label_hbox);
        header.setBottom(submenuNguoiDung());
        
        lb_submenu_1.setOnMouseClicked(e->
        {
            header.setBottom(submenuNguoiDung());
            Scene.setLeft(GroupNguoiDung());
            Scene.setCenter(tb_NguoiDung());
        });
        
        return header;
    }
    private HBox submenuNguoiDung()
    {
        Text txtThongTinCaiDat = new Text("Thông tin cài đặt");
        txtThongTinCaiDat.getStyleClass().add("text-submenu");
        
        Rectangle iconThemNguoiDung = new Rectangle(125,30);
        iconThemNguoiDung.getStyleClass().add("icon");
        iconThemNguoiDung.setFill(Color.web("#FFCF75"));
        Text txtThemNguoiDung = new Text("+ Thêm người dùng");
        txtThemNguoiDung.setMouseTransparent(true);
        txtThemNguoiDung.getStyleClass().add("txt-button");
        
        StackPane stackThemNguoiDung = new StackPane();
        stackThemNguoiDung.getChildren().addAll(iconThemNguoiDung, txtThemNguoiDung);
        stackThemNguoiDung.setAlignment(Pos.CENTER_RIGHT);
        stackThemNguoiDung.setMargin(txtThemNguoiDung,new Insets(0,5,0,0));
        stackThemNguoiDung.setPadding(new Insets(0,0,5,0));
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemNguoiDung,Priority.ALWAYS);
        submenu.getChildren().addAll(txtThongTinCaiDat, stackThemNguoiDung);
        
        return submenu;
    }
    private VBox tb_NguoiDung()
    {
        load_DSNguoiDung();
        TableView tb = new TableView();
        tb.setEditable(true);
        
        TableColumn stt = new TableColumn("#");
        stt.setPrefWidth(30);
        stt.setSortable(false);
        TableColumn HoTen= new TableColumn("Họ tên");
        HoTen.setPrefWidth(180);
        TableColumn TenDangNhap= new TableColumn("Tên đăng nhập");
        TenDangNhap.setPrefWidth(150);
        TableColumn DienThoai = new TableColumn("Điện thoại");
        DienThoai.setPrefWidth(100);
        TableColumn email= new TableColumn("Email");
        email.setPrefWidth(150);
        TableColumn NhomNguoiDung= new TableColumn("Nhóm người dùng");
        NhomNguoiDung.setPrefWidth(120);
        TableColumn<TaiKhoan,TaiKhoan> ChucNang= new TableColumn("Chức năng");
        ChucNang.setPrefWidth(80);
        
        tb.getColumns().addAll(stt, HoTen, TenDangNhap, DienThoai,
                email, NhomNguoiDung, ChucNang);
        tb.setItems(ds_nguoidung);
        
        HoTen.setCellValueFactory(new PropertyValueFactory<TaiKhoan,String>("Ten"));
        TenDangNhap.setCellValueFactory(new PropertyValueFactory<TaiKhoan,String>("Ten_Dang_Nhap"));
        DienThoai.setCellValueFactory(new PropertyValueFactory<TaiKhoan,String>("SDT"));
        email.setCellValueFactory(new PropertyValueFactory<TaiKhoan,String>("Email"));
        NhomNguoiDung.setCellValueFactory(new PropertyValueFactory<TaiKhoan,String>("Nhom"));
        ChucNang.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
        ChucNang.setCellFactory(column -> new TableCell<TaiKhoan, TaiKhoan>(){
            @Override
            protected void updateItem(TaiKhoan taikhoan, boolean empty)
            {
                super.updateItem(taikhoan, empty);
                if(taikhoan == null){
                    setGraphic(null);
                    return;
                }
                HBox hb_chucnang = new HBox(10);
                hb_chucnang.setAlignment(Pos.CENTER);
                
                Rectangle icon_Xoa = CreateIcon(16,16,"/images/icon-trash.png");
                Tooltip.install(icon_Xoa, new Tooltip("Xoá"));
                
                icon_Xoa.setOnMousePressed(e->{
                    XoaNguoiDung(taikhoan);
                });

                hb_chucnang.getChildren().addAll(icon_Xoa);
                setGraphic(hb_chucnang);
            }    
        });
        stt.setCellValueFactory(new Callback<CellDataFeatures<TaiKhoan, String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<TaiKhoan, String> p){
                return new ReadOnlyObjectWrapper(tb.getItems().indexOf(p.getValue()) + "");
            }
        });
        
        VBox vb = new VBox(10);
        vb.setPadding(new Insets(15, 10, 15, 20));
        vb.getChildren().add(tb);
        return vb;
        
    }
    private VBox GroupNguoiDung()
    {
        Text group = new Text("Nhóm");
        group.setId("group");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_RIGHT);
        HBox HBoxGroup = new HBox(10);
        HBoxGroup.setHgrow(grid,Priority.ALWAYS);
        HBoxGroup.getChildren().addAll(group, grid);
        HBoxGroup.setPadding(new Insets(15,5,10,5));
        
        Rectangle rec1 = new Rectangle(250,30);
        rec1.getStyleClass().add("rec");
        Text TaiKhoanNguoiDung = new Text("Tài khoản người dùng");
        TaiKhoanNguoiDung.setMouseTransparent(true);
        TaiKhoanNguoiDung.getStyleClass().add("txt");
        StackPane stk1 = new StackPane();
        stk1.getStyleClass().add("stk");
        stk1.setAlignment(Pos.CENTER_LEFT);
        stk1.setMargin(TaiKhoanNguoiDung, new Insets(0,0,0,10));
        stk1.getChildren().addAll(rec1, TaiKhoanNguoiDung);
        
        VBox vbgroup = new VBox(10);
        vbgroup.getChildren().addAll(HBoxGroup, stk1);
        
        return vbgroup;
    }

    private void load_DSNguoiDung() {
        ds_nguoidung.clear();
        sql = "SELECT * FROM Tai_Khoan ORDER BY user_id ASC;";
        crs = conn.getCRS(sql);
        try {
            if(crs.isBeforeFirst())
            {
                while(crs.next())                
                {
                    TaiKhoan tk = new TaiKhoan();
                    String tendangnhap = crs.getString("Ten_Dang_Nhap");
                    String userid = crs.getString("user_id");
                    String nhom = crs.getString("Nhom");
                    String ten = crs.getString("Ten");
                    String sdt = crs.getString("SDT");
                    String email = crs.getString("Email");
                    tk.setUser_id(userid);
                    tk.setTen_Dang_Nhap(tendangnhap);
                    tk.setNhom(nhom);
                    tk.setTen(ten);
                    tk.setSDT(sdt);
                    tk.setEmail(email);
                    ds_nguoidung.add(tk);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CauHinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void XoaNguoiDung(TaiKhoan taikhoan)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        alert.setContentText("Bạn chắc chắn muốn xoá Đơn thuốc "+taikhoan.getUser_id()+"?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            ds_nguoidung.remove(taikhoan);
            sql = "DELETE FROM Tai_Khoan WHERE user_id='"+taikhoan.getUser_id()+"';";
            conn.update(sql);
        }
    }
}
