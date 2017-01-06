package quanlyphongmach;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javax.sql.rowset.CachedRowSet;
import quanlyphongmach.model.DonThuoc;

/**
 *
 * @author Quang Khanh
 */
public class DonThuocPanel extends Scene{
    private static BorderPane Scene = new BorderPane();
    private ObservableList<DonThuoc> ds_DonThuoc = FXCollections.observableArrayList();
    private ConnectToDatabase conn = new ConnectToDatabase();
    private CachedRowSet crs;
    public DonThuocPanel()
    {
        super(Scene);
        CreateScene();
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/main.css").toExternalForm());  
    }
    private void CreateScene()
    {
        
        Scene.setTop(Header());
        Scene.setCenter(tb_DSDonThuoc());
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

        header.setTop(mainMenu);
        header.setCenter(lbDanhSachToaThuoc);
        header.setBottom(submenuDSDonThuoc());
        return header;
    }
    private HBox submenuDSDonThuoc()
    {
        Text txtDanhSachToaThuoc = new Text("Danh sách toa thuốc");
        txtDanhSachToaThuoc.getStyleClass().add("text-submenu");
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
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(iconTimKiem, txtTimKiem);
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(0,0,5,0));
        
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.getChildren().addAll(txtDanhSachToaThuoc, tfTimKiem, fromDay, toDay, stack);
        
        return submenu;
    }
    private VBox tb_DSDonThuoc()
    {
        loadDsDonThuoc();
        TableView tbDanhSachToaThuoc = new TableView();
        tbDanhSachToaThuoc.setEditable(false);
        TableColumn maCol = new TableColumn("Mã số");
        maCol.setPrefWidth(120);
        TableColumn tenCol = new TableColumn("Tên bệnh nhân");
        tenCol.setPrefWidth(150);
        TableColumn tuoiCol = new TableColumn("Tuổi");
        tuoiCol.setPrefWidth(50);
        TableColumn chanDoanCol = new TableColumn("Chẩn đoán");
        chanDoanCol.setPrefWidth(150);
        TableColumn tongtienCol = new TableColumn("Tổng tiền thuốc");
        tongtienCol.setPrefWidth(120);
        TableColumn nguoiLapCol = new TableColumn("Người lập");
        nguoiLapCol.setPrefWidth(120);
        TableColumn ngayLapCol = new TableColumn("Ngày lập");
        ngayLapCol.setPrefWidth(120);
        TableColumn<DonThuoc,DonThuoc> chucNangCol = new TableColumn("Chức năng");
        chucNangCol.setPrefWidth(100);
        
        tbDanhSachToaThuoc.getColumns().addAll(maCol, tenCol, tuoiCol, chanDoanCol, tongtienCol, nguoiLapCol, ngayLapCol, chucNangCol);
        tbDanhSachToaThuoc.setItems(ds_DonThuoc);
        
        maCol.setCellValueFactory(new PropertyValueFactory<DonThuoc,String>("Ma_Don_Thuoc"));
        tenCol.setCellValueFactory(new PropertyValueFactory<DonThuoc,String>("Ten_Benh_Nhan"));
        tuoiCol.setCellValueFactory(new PropertyValueFactory<DonThuoc,Integer>("Tuoi"));
        chanDoanCol.setCellValueFactory(new PropertyValueFactory<DonThuoc,String>("Chan_Doan"));
        tongtienCol.setCellValueFactory(new PropertyValueFactory<DonThuoc,Double>("Tong_Tien"));
        nguoiLapCol.setCellValueFactory(new PropertyValueFactory<DonThuoc,String>("Nguoi_Lap"));
        ngayLapCol.setCellValueFactory(new PropertyValueFactory<DonThuoc,String>("Ngay_Lap"));
        chucNangCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
        chucNangCol.setCellFactory(column -> new TableCell<DonThuoc, DonThuoc>(){
            @Override
            protected void updateItem(DonThuoc donthuoc, boolean empty)
            {
                super.updateItem(donthuoc, empty);
                if(donthuoc == null){
                    setGraphic(null);
                    return;
                }
                HBox hb_chucnang = new HBox(10);
                hb_chucnang.setAlignment(Pos.CENTER);
                
                Rectangle icon_Xoa = CreateIcon(16,16,"/images/icon-trash.png");
                Tooltip.install(icon_Xoa, new Tooltip("Xoá"));
                
                icon_Xoa.setOnMousePressed(e->{
                    XoaDonThuoc(donthuoc);
                });

                hb_chucnang.getChildren().addAll(icon_Xoa);
                setGraphic(hb_chucnang);
            }
        });
        
        VBox table = new VBox(10);
        table.setPadding(new Insets(15, 10, 15, 20));
        table.getChildren().add(tbDanhSachToaThuoc);
        
        
        
        return table;
    }
    public FlowPane ThongTinChuaBenh()
    {
        FlowPane flow_pane = new FlowPane();
        flow_pane.setPadding(new Insets(10,0,0,0));
        flow_pane.setVgap(10);
        flow_pane.setHgap(10);
        
        
        return flow_pane;
    }
    public void loadDsDonThuoc()
    {
        ds_DonThuoc.clear();
        try {
            String sql = "SELECT Ma_Don_Thuoc, don_thuoc.MaBN, Chan_Doan, Ngay_Lap, Nguoi_Lap, Tong_Tien,Ten, Ngay_Sinh FROM don_thuoc "
                    + "INNER JOIN benh_nhan ON don_thuoc.MaBN = benh_nhan.MaBN ORDER BY Ma_Don_Thuoc ASC";
            crs = conn.getCRS(sql);
            while(crs.next())
            {
                DonThuoc ds = new DonThuoc();
                ds.setMa_Don_Thuoc(crs.getString("Ma_Don_Thuoc"));
                ds.setTen_Benh_Nhan(crs.getString("Ten"));
                ds.setChan_Doan(crs.getString("Chan_Doan"));
                ds.setNgay_Lap(crs.getString("Ngay_Lap"));
                ds.setNguoi_Lap(crs.getString("Nguoi_Lap"));
                ds.setTong_Tien(crs.getDouble("Tong_Tien"));
                int tuoi = 0;
                
                Date ngaysinh = crs.getDate("Ngay_Sinh");
                DateFormat dateFormat = new SimpleDateFormat("yyyy");
                int namsinh = Integer.parseInt(dateFormat.format(ngaysinh));
                int namhientai = Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy")));
                tuoi = namhientai - namsinh;
                ds.setTuoi(tuoi);
                ds_DonThuoc.add(ds);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonThuocPanel.class.getName()).log(Level.SEVERE, null, ex);
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
    public void XoaDonThuoc(DonThuoc donthuoc)
       {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Xác nhận");
           alert.setHeaderText(null);
           alert.setContentText("Bạn chắc chắn muốn xoá Đơn thuốc "+donthuoc.getMa_Don_Thuoc()+"?");
           Optional<ButtonType> result = alert.showAndWait();
           if(result.get() == ButtonType.OK)
                    {
                        ds_DonThuoc.remove(donthuoc);
                        String sql = "DELETE FROM Don_Thuoc WHERE Ma_Don_Thuoc='"+donthuoc.getMa_Don_Thuoc()+"';";
                        conn.update(sql);
                    }
       }    
}
