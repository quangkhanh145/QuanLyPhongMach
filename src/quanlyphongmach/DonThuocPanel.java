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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Quang Khanh
 */
public class DonThuocPanel extends Scene{
    static BorderPane Scene = new BorderPane();
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
}
