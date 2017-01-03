package quanlyphongmach;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sql.rowset.CachedRowSet;
import quanlyphongmach.model.BenhNhan;

/**
 *
 * @author Quang Khanh
 */
public class BenhNhanPanel extends Scene{
    private static final BorderPane Scene = new BorderPane();
    private final ObservableList<BenhNhan> data = FXCollections.observableArrayList();
    private ConnectToDatabase conn = new ConnectToDatabase();;
    private CachedRowSet crs;
    private String sql;
    private Stage stage_Child;
    private Scene Scene_Child;
    private TextField tfTimKiem;
    private TableView<BenhNhan> tbDanhSachBN;
    public  BenhNhanPanel()
    {
        super(Scene);
        CreateScene();
        getStylesheets().add(QuanLyPhongMach.class.getResource("/css/main.css").toExternalForm());
    }
       private void CreateScene()
    {
        Scene.setTop(Header());
        Scene.setLeft(Group());
        Scene.setCenter(tb_DSBenhNhan());
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
        BenhNhan.setId("BenhNhan");
        BenhNhan.getStyleClass().add("label-main-menu");
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
        
        Label lb_DanhSachBenhNhan = new Label("Danh sách bệnh nhân");
        lb_DanhSachBenhNhan.getStyleClass().add("label_submenu");
        lb_DanhSachBenhNhan.setAlignment(Pos.TOP_LEFT);
        
        BorderPane header = new BorderPane();
        header.getStyleClass().add("header");
        
        header.setTop(mainMenu);
        header.setCenter(lb_DanhSachBenhNhan);
        header.setBottom(submenuDSBenhNhan());
        
        return header;
       }
       private HBox submenuDSBenhNhan()
       {
        Text QuanLyBenhNhan = new Text("Quản lý bệnh nhân");
        QuanLyBenhNhan.getStyleClass().add("text-submenu");
        tfTimKiem = new TextField();
        tfTimKiem.setPromptText("Nhập từ khoá");
        tfTimKiem.setFocusTraversable(false);
        
        Rectangle iconThemBenhNhan = new Rectangle(120,30);
        iconThemBenhNhan.getStyleClass().add("icon");
        iconThemBenhNhan.setFill(Color.web("#FFCF75"));
        Text txtThemBN = new Text("+ Thêm bệnh nhân");
        txtThemBN.setMouseTransparent(true);
        txtThemBN.getStyleClass().add("txt-button");
        
        iconThemBenhNhan.setOnMousePressed(e->
        {
            CreateStageThemBN();
            stage_Child.centerOnScreen();
            stage_Child.showAndWait();
        });
        
        StackPane stackThemBN = new StackPane();
        stackThemBN.getChildren().addAll(iconThemBenhNhan, txtThemBN);
        stackThemBN.setAlignment(Pos.CENTER_RIGHT);
        stackThemBN.setMargin(txtThemBN,new Insets(0,5,0,0));
        stackThemBN.setPadding(new Insets(0,0,5,0));
                
        HBox submenu = new HBox(10);
        submenu.setPadding(new Insets(10,10,5,20));
        submenu.setHgrow(stackThemBN,Priority.ALWAYS);
        submenu.getChildren().addAll(QuanLyBenhNhan, tfTimKiem, stackThemBN);
        return submenu;
       }
       private VBox tb_DSBenhNhan()
       {
        loadData();
        tbDanhSachBN = new TableView();
        tbDanhSachBN.setEditable(false);
        TableColumn maCol = new TableColumn("Mã");
        maCol.setPrefWidth(120);
        maCol.setCellValueFactory(new PropertyValueFactory<BenhNhan,String>("MaBN"));
        TableColumn tenCol = new TableColumn("Tên");
        tenCol.setPrefWidth(150);
        tenCol.setCellValueFactory(new PropertyValueFactory<BenhNhan,String>("Ten"));
        TableColumn gioitinhCol = new TableColumn("Giới tính");
        gioitinhCol.setPrefWidth(100);
        gioitinhCol.setCellValueFactory(new PropertyValueFactory<BenhNhan,String>("GioiTinh"));
        TableColumn ngaysinhCol = new TableColumn("Ngày sinh");
        ngaysinhCol.setPrefWidth(120);
        ngaysinhCol.setCellValueFactory(new PropertyValueFactory<BenhNhan,String>("NgaySinh"));
        TableColumn dienthoaiCol = new TableColumn("Điện thoại");
        dienthoaiCol.setPrefWidth(100);
        dienthoaiCol.setCellValueFactory(new PropertyValueFactory<BenhNhan,String>("SDT"));
        TableColumn diachiCol = new TableColumn("Địa chỉ");
        diachiCol.setPrefWidth(150);
        diachiCol.setCellValueFactory(new PropertyValueFactory<BenhNhan,String>("DiaChi"));
        TableColumn<BenhNhan, BenhNhan> chucNangCol = new TableColumn("Chức năng");
        chucNangCol.setPrefWidth(200);
        chucNangCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
        chucNangCol.setCellFactory(column -> new TableCell<BenhNhan, BenhNhan>(){
            @Override
            protected void updateItem(BenhNhan benhnhan, boolean empty)
            {
                super.updateItem(benhnhan, empty);
                if(benhnhan == null){
                    setGraphic(null);
                    return;
                }
                HBox hb_chucnang = new HBox(10);
                hb_chucnang.setAlignment(Pos.CENTER);
                
                Rectangle icon_Xoa = CreateIcon(16,16,"/images/icon-trash.png");
                Tooltip.install(icon_Xoa, new Tooltip("Xoá"));
                
                icon_Xoa.setOnMousePressed(e->{
                    XoaBenhNhan(benhnhan);
                });
                
                Rectangle icon_Sua = CreateIcon(16,16,"/images/action-edit.png");
                Tooltip.install(icon_Sua, new Tooltip("Sửa thông tin"));
                
                icon_Sua.setOnMousePressed(e->{
                    SuaBenhNhan(benhnhan);
                });
                
                
                Rectangle icon_DonThuoc = CreateIcon(16,16,"/images/action-prescript.png");
                Tooltip.install(icon_DonThuoc, new Tooltip("Kê toa"));
                
                icon_DonThuoc.setOnMousePressed(e->{
                    SceneController.setKeToaPanel(benhnhan);
                });
                
                hb_chucnang.getChildren().addAll(icon_DonThuoc, icon_Sua, icon_Xoa);
                setGraphic(hb_chucnang);
            }
        });
        
        FilteredList<BenhNhan> FilteredData = new FilteredList<>(data, p-> true);
        tfTimKiem.textProperty().addListener((observable, oldValue, newValue)->
        {
            FilteredData.setPredicate(benhnhan ->{
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(benhnhan.getMaBN().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                } else if(benhnhan.getTen().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                } else if(benhnhan.getGioiTinh().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else if(benhnhan.getDiaChi().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                } else if(benhnhan.getSDT().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                return false;
            });
        });
        tbDanhSachBN.setItems(FilteredData);
        tbDanhSachBN.getColumns().addAll(maCol, tenCol, gioitinhCol, ngaysinhCol, 
                dienthoaiCol, diachiCol, chucNangCol);
        VBox table = new VBox(10);
        table.setPadding(new Insets(15, 10, 15, 20));
        table.getChildren().add(tbDanhSachBN);
        
        return table;
       }
       
       private VBox Group()
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
        sql = "Select Ten_Nhom FROM `Nhom` WHERE Loai LIKE '%bệnh nhân%'";
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
       
       public void loadData(){
           data.clear();
           sql = "SELECT * FROM benh_nhan ORDER BY MaBN asc";
           crs = conn.getCRS(sql);
        try {
            if(crs.isBeforeFirst()){
                while(crs.next())
                {
                    BenhNhan benhnhan = new BenhNhan();
                    benhnhan.setMaBN(crs.getString("MaBN"));
                    benhnhan.setTen(crs.getString("Ten"));
                    benhnhan.setNgaySinh(crs.getString("Ngay_Sinh"));
                    benhnhan.setGioiTinh(crs.getString("Gioi_Tinh"));
                    benhnhan.setSDT(crs.getString("SDT"));
                    benhnhan.setDiaChi(crs.getString("Dia_Chi"));
                    benhnhan.setNhom(crs.getString("Nhom"));
                    data.add(benhnhan);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BenhNhanPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       public void CreateStageThemBN()
       {
           stage_Child = new Stage();
           stage_Child.setResizable(false);
           CreateSceneThemBN();
           stage_Child.setScene(Scene_Child);
           stage_Child.initModality(Modality.APPLICATION_MODAL);
           stage_Child.setTitle("Thêm bệnh nhân");
       }
       public void CreateSceneThemBN(){
           GridPane TTBenhNhan = new GridPane();
           TTBenhNhan.setHgap(10);
           TTBenhNhan.setVgap(20);
           TTBenhNhan.setAlignment(Pos.CENTER);
           TTBenhNhan.setPadding(new Insets(10,0,10,0));
           
           Label lb_maBN = new Label("Mã bệnh nhân");
           Label lb_ten = new Label("Tên bệnh nhân");
           Label lb_ngaysinh = new Label("Ngày sinh");
           Label lb_gioitinh = new Label("Giới tính");
           Label lb_sdt = new Label("Số điện thoại");
           Label lb_diachi = new Label("Địa chỉ");
           Label lb_nhom = new Label("Nhóm");
           
           TextField tfMaBN = new TextField();
           TextField tfTen = new TextField();
           DatePicker dpNgaysinh = new DatePicker();
           ComboBox cmbGioitinh = new ComboBox();
           cmbGioitinh.getItems().addAll("Nam","Nữ");
           TextField tfSdt = new TextField();
           TextField tfDiachi = new TextField();
           ComboBox cmbNhom = new ComboBox();
           
           Text txt_result = new Text();
           txt_result.setFill(Color.RED);
           txt_result.setFont(Font.font("Arial", FontWeight.BOLD, 13));
           
           Button btnHoanTat = new Button("Hoàn tất");
           btnHoanTat.setOnAction(e->{
               BenhNhan bn = new BenhNhan();
               if(tfMaBN.getText().length() == 0)
               {
                    txt_result.setText("Vui lòng nhập mã bệnh nhân");
                    return;
               }
               if(tfTen.getText().length() == 0)
               {
                    txt_result.setText("Tên bệnh nhân không được trống");
                    return;
               }
               if(!cmbGioitinh.getSelectionModel().isEmpty())
               {
                   bn.setGioiTinh(cmbGioitinh.getSelectionModel().getSelectedItem().toString());
               }
               if(!cmbNhom.getSelectionModel().isEmpty())
               {
                   bn.setNhom(cmbNhom.getSelectionModel().getSelectedItem().toString());
               }
               if(dpNgaysinh.getValue() == null)
               {
                   txt_result.setText("Chọn ngày sinh");
                   return;
               }
               if(tfSdt.getText().matches("\\d*") == false || tfSdt.getText().length() > 12){
                   txt_result.setText("Số điện thoại không hợp lệ");
                   return;
               }
               bn.setMaBN(tfMaBN.getText());
               bn.setTen(tfTen.getText());
               bn.setDiaChi(tfDiachi.getText());
               bn.setNgaySinh(dpNgaysinh.getValue().toString());
               bn.setSDT(tfSdt.getText());
               sql = "INSERT INTO `Benh_Nhan` VALUES('"+bn.getMaBN()+"'"
                       + ",'"+bn.getTen()+"','"+bn.getNgaySinh()+"','"
                       + bn.getGioiTinh()+"','"+bn.getSDT()+"','"+bn.getDiaChi()+"','"
                       + bn.getNhom()+"')";
               if(conn.getPS(sql) == 0)
               {
                   txt_result.setText("Mã bệnh nhân đã tồn tại");
                   return;
               }
               data.add(bn);
               stage_Child.close();
           });
           btnHoanTat.setAlignment(Pos.CENTER);
           btnHoanTat.setDefaultButton(true);
           Button btnBoQua = new Button("Bỏ qua");
           btnBoQua.setAlignment(Pos.CENTER);
           btnBoQua.setOnAction(e->{
               stage_Child.close();
           });
           
           btnHoanTat.setPrefSize(100, 40);
           btnBoQua.setPrefSize(100, 40);
           
           TTBenhNhan.add(lb_maBN, 0, 0);
           TTBenhNhan.add(tfMaBN, 1, 0);
           TTBenhNhan.add(lb_ten, 0, 1);
           TTBenhNhan.add(tfTen, 1, 1);
           TTBenhNhan.add(lb_ngaysinh, 0, 2);
           TTBenhNhan.add(dpNgaysinh, 1, 2);
           TTBenhNhan.add(lb_gioitinh, 0, 3);
           TTBenhNhan.add(cmbGioitinh, 1, 3);
           TTBenhNhan.add(lb_sdt, 0, 4);
           TTBenhNhan.add(tfSdt, 1, 4);
           TTBenhNhan.add(lb_diachi, 0, 5);
           TTBenhNhan.add(tfDiachi, 1, 5);
           TTBenhNhan.add(lb_nhom, 0, 6);
           TTBenhNhan.add(cmbNhom, 1, 6);
           TTBenhNhan.add(txt_result, 0, 7, 2 ,1);
           TTBenhNhan.add(btnHoanTat, 0, 8);
           TTBenhNhan.add(btnBoQua, 1, 8);
           Scene_Child = new Scene(TTBenhNhan,400,500);
       }
       public void XoaBenhNhan(BenhNhan benhnhan)
       {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Xác nhận");
           alert.setHeaderText(null);
           alert.setContentText("Bạn chắc chắn muốn xoá bệnh nhân "+benhnhan.getTen()+"?");
           Optional<ButtonType> result = alert.showAndWait();
           if(result.get() == ButtonType.OK)
                    {
                        data.remove(benhnhan);
                        sql = "DELETE FROM `benh_nhan` WHERE MaBN='"+benhnhan.getMaBN()+"';";
                        conn.update(sql);
                    }
       }
       public void SuaBenhNhan(BenhNhan benhnhan)
       {
           CreateStageSuaBN(benhnhan);
           stage_Child.centerOnScreen();
           stage_Child.showAndWait();
       }
       public void CreateStageSuaBN(BenhNhan benhnhan)
       {
           stage_Child = new Stage();
           stage_Child.setResizable(false);
           CreateSceneSuaBN(benhnhan);
           stage_Child.setScene(Scene_Child);
           stage_Child.initModality(Modality.APPLICATION_MODAL);
           stage_Child.setTitle("Sửa thông tin bệnh nhân");
       }
       public void CreateSceneSuaBN(BenhNhan benhnhan){
           GridPane TTBenhNhan = new GridPane();
           TTBenhNhan.setHgap(10);
           TTBenhNhan.setVgap(20);
           TTBenhNhan.setAlignment(Pos.CENTER);
           TTBenhNhan.setPadding(new Insets(10,0,10,0));
           
           Label lb_maBN = new Label("Mã bệnh nhân");
           Label lb_ten = new Label("Tên bệnh nhân");
           Label lb_ngaysinh = new Label("Ngày sinh");
           Label lb_gioitinh = new Label("Giới tính");
           Label lb_sdt = new Label("Số điện thoại");
           Label lb_diachi = new Label("Địa chỉ");
           Label lb_nhom = new Label("Nhóm");
           
           TextField tfMaBN = new TextField();
           tfMaBN.setText(benhnhan.getMaBN());
           TextField tfTen = new TextField();
           tfTen.setText(benhnhan.getTen());
           DatePicker dpNgaysinh = new DatePicker();
           dpNgaysinh.setValue(LocalDate.parse(benhnhan.getNgaySinh()));
           ComboBox cmbGioitinh = new ComboBox();
           cmbGioitinh.setValue(benhnhan.getGioiTinh());
           cmbGioitinh.getItems().addAll("Nam","Nữ");
           TextField tfSdt = new TextField();
           tfSdt.setText(benhnhan.getSDT());
           TextField tfDiachi = new TextField();
           tfDiachi.setText(benhnhan.getDiaChi());
           ComboBox cmbNhom = new ComboBox();
           cmbNhom.setValue(benhnhan.getNhom());
           
           Text txt_result = new Text();
           txt_result.setFill(Color.RED);
           txt_result.setFont(Font.font("Arial", FontWeight.BOLD, 13));
           
           Button btnHoanTat = new Button("Hoàn tất");
           btnHoanTat.setOnAction(e->{
               BenhNhan bn = new BenhNhan();
               if(tfMaBN.getText().length() == 0)
               {
                    txt_result.setText("Vui lòng nhập mã bệnh nhân");
                    return;
               }
               if(tfTen.getText().length() == 0)
               {
                    txt_result.setText("Tên bệnh nhân không được trống");
                    return;
               }
               if(!cmbGioitinh.getSelectionModel().isEmpty())
               {
                   bn.setGioiTinh(cmbGioitinh.getSelectionModel().getSelectedItem().toString());
               }
               if(!cmbNhom.getSelectionModel().isEmpty())
               {
                   bn.setNhom(cmbNhom.getSelectionModel().getSelectedItem().toString());
               }
               if(dpNgaysinh.getValue() == null)
               {
                   txt_result.setText("Chọn ngày sinh");
                   return;
               }
               if(tfSdt.getText().matches("\\d*") == false || tfSdt.getText().length() > 12){
                   txt_result.setText("Số điện thoại không hợp lệ");
                   return;
               }
               bn.setMaBN(tfMaBN.getText());
               bn.setTen(tfTen.getText());
               bn.setDiaChi(tfDiachi.getText());
               bn.setNgaySinh(dpNgaysinh.getValue().toString());
               bn.setSDT(tfSdt.getText());
               sql = "UPDATE `Benh_Nhan` SET `MaBN`='"+bn.getMaBN()+"',"
                       + "`Ten`='"+bn.getTen()+"',"
                       + "`Ngay_Sinh`='"+bn.getNgaySinh()+"',"
                       + "`Gioi_Tinh`='"+ bn.getGioiTinh()+"',"
                       + "`SDT`='"+bn.getSDT()+"',"
                       + "`Dia_Chi`='"+bn.getDiaChi()+"',"
                       + "`Nhom`='"+ bn.getNhom()+"' WHERE `MaBN`='"+benhnhan.getMaBN()+"'";
               if(conn.getPS(sql) == 0)
               {
                   txt_result.setText("Vui lòng thử lại sau!");
                   return;
               }
               loadData();
               stage_Child.close();
           });
           btnHoanTat.setAlignment(Pos.CENTER);
           btnHoanTat.setDefaultButton(true);
           Button btnBoQua = new Button("Bỏ qua");
           btnBoQua.setAlignment(Pos.CENTER);
           btnBoQua.setOnAction(e->{
               stage_Child.close();
           });
           
           btnHoanTat.setPrefSize(100, 40);
           btnBoQua.setPrefSize(100, 40);
           
           TTBenhNhan.add(lb_maBN, 0, 0);
           TTBenhNhan.add(tfMaBN, 1, 0);
           TTBenhNhan.add(lb_ten, 0, 1);
           TTBenhNhan.add(tfTen, 1, 1);
           TTBenhNhan.add(lb_ngaysinh, 0, 2);
           TTBenhNhan.add(dpNgaysinh, 1, 2);
           TTBenhNhan.add(lb_gioitinh, 0, 3);
           TTBenhNhan.add(cmbGioitinh, 1, 3);
           TTBenhNhan.add(lb_sdt, 0, 4);
           TTBenhNhan.add(tfSdt, 1, 4);
           TTBenhNhan.add(lb_diachi, 0, 5);
           TTBenhNhan.add(tfDiachi, 1, 5);
           TTBenhNhan.add(lb_nhom, 0, 6);
           TTBenhNhan.add(cmbNhom, 1, 6);
           TTBenhNhan.add(txt_result, 0, 7, 2 ,1);
           TTBenhNhan.add(btnHoanTat, 0, 8);
           TTBenhNhan.add(btnBoQua, 1, 8);
           Scene_Child = new Scene(TTBenhNhan,400,500);
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
}
