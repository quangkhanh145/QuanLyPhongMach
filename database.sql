SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+07:00";

DROP DATABASE IF EXISTS phongkhamuit;
CREATE DATABASE IF NOT EXISTS phongkhamuit;
USE phongkhamuit;

/*===================bảng nhóm===========================*/
CREATE TABLE `Nhom`
(
	`id` VARCHAR(5) NOT NULL,
	`Ten_Nhom` VARCHAR(30) NOT NULL,
	`Loai`	VARCHAR(30),
	`id_Nhom_Cha` VARCHAR(5),
	PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*===============================================================*/
INSERT INTO `Nhom` VALUES
('00001','tiêu hoá,gan mật','thuốc',NULL),
('00002','nội tiết','thuốc',NULL),
('00003','Hô hấp','thuốc',NULL),
('00004','Kháng sinh','thuốc',NULL),
('00005','Cơ xương khớp','thuốc',NULL),
('00006','Tim mạch','thuốc',NULL),
('00007','Thần kinh','thuốc',NULL),
('00008','Vitamin & Thuốc bổ','thuốc',NULL),
('00009','Tiết niệu','thuốc',NULL),
('00010','Dị ứng','thuốc',NULL),
('00011','Hạ sốt','thuốc',NULL),
('00012','Giảm đau','thuốc',NULL),
('00013','Admins','Người dùng',NULL),
('00014','Bác sĩ','Người dùng',NULL),
('00015','Nhập liệu','Người dùng',NULL);

/*===================bảng tài khoản===========================*/
CREATE TABLE `Tai_Khoan`
(
	`user_id` VARCHAR(5) NOT NULL DEFAULT '0',
	`Ten_Dang_Nhap` VARCHAR(20) NOT NULL,
	`Mat_Khau` VARCHAR(15) NOT NULL,
	`Nhom` VARCHAR(30),
	`Ten` VARCHAR(30) NOT NULL,
	`SDT` VARCHAR(12),
	`Email` VARCHAR(30),
	PRIMARY KEY(`user_id`),
	UNIQUE(`Ten_Dang_Nhap`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Tai_Khoan_seq`
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);
/*===================Trigger tự động tăng id cho tài khoản===========================*/
DELIMITER $$
CREATE TRIGGER `Tai_Khoan_insert`
BEFORE INSERT ON `Tai_Khoan`
FOR EACH ROW
BEGIN
  INSERT INTO `Tai_Khoan_seq` VALUES (NULL);
  SET NEW.user_id = CONCAT('TK', LPAD(LAST_INSERT_ID(), 3, '0'));
END$$
DELIMITER ;
/*===============================================================*/
INSERT INTO `Tai_Khoan` VALUES
('','admin','admin','Admins','Đỗ Quang Khánh','01664202120','quangkhanh@gmail.com'),
('','bacsi','bacsi','Bác sĩ','Đỗ Quang Khánh','01664202120','quangkhanh@gmail.com'),
('','nhaplieu','nhaplieu','Nhập liệu','Đỗ Quang Khánh','01664202120','quangkhanh@gmail.com');

/*===================bảng bệnh nhân===========================*/
CREATE TABLE `Benh_Nhan`
(
	`MaBN` VARCHAR(20) NOT NULL DEFAULT '0',
	`Ten`  VARCHAR(30) NOT NULL,
	`Ngay_Sinh`  DATE,
	`Gioi_Tinh` VARCHAR(10),
	`SDT` VARCHAR(12),
	`Dia_Chi`  VARCHAR(100),
	`Nhom` VARCHAR(30),
	PRIMARY KEY(`MaBN`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Benh_Nhan_seq`
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);
/*===================Trigger tự động tăng id cho đơn thuốc===========================*/
DELIMITER $$
CREATE TRIGGER `Benh_Nhan_insert`
BEFORE INSERT ON `Benh_Nhan`
FOR EACH ROW
BEGIN
  INSERT INTO `Benh_Nhan_seq` VALUES (NULL);
  SET NEW.MaBN = CONCAT('BN', LPAD(LAST_INSERT_ID(), 8, '0'));
END$$
DELIMITER ;
/*===============================================================*/
INSERT INTO `Benh_Nhan` VALUES
('','Mai Phương Thảo','1995-1-1','Nữ','094912364','TP.HCM',NULL),
('','Trần Việt Dũng','1996-2-1','Nam','0918716482','Đồng Nai',NULL),
('','Đinh Huy Hoàng','2000-4-3','Nam','0984618237','Cần Thơ',NULL),
('','Vũ Phương Thảo','1989-2-2','Nữ','0914618613','An Giang',NULL),
('','Mai Phương Lan','1968-12-8','Nữ','0947145612','Long An',NULL);

/*===================bảng đơn thuốc===========================*/
CREATE TABLE `Don_Thuoc`
(
	`Ma_Don_Thuoc` VARCHAR(20) NOT NULL DEFAULT '0',
	`MaBN` VARCHAR(20) NOT NULL,
	`Chan_Doan` VARCHAR(100),
	`Loi_Dan` VARCHAR(100),
	`Ngay_Lap` DATE,
	`Nguoi_Lap` VARCHAR(100),
	`Tong_Tien` DOUBLE UNSIGNED,
	PRIMARY KEY(`Ma_Don_Thuoc`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Don_Thuoc_seq`
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);
/*===================Trigger tự động tăng id cho đơn thuốc===========================*/
DELIMITER $$
CREATE TRIGGER `Don_Thuoc_insert`
BEFORE INSERT ON `Don_Thuoc`
FOR EACH ROW
BEGIN
  INSERT INTO `Don_Thuoc_seq` VALUES (NULL);
  SET NEW.Ma_Don_Thuoc = CONCAT('DT', LPAD(LAST_INSERT_ID(), 6, '0'));
END$$
DELIMITER ;
/*===============================================================*/

/*===================bảng Chi tiết đơn thuốc===========================*/
CREATE TABLE `Chi_Tiet_Don_Thuoc`
(
	`Ma_Don_Thuoc` VARCHAR(20) NOT NULL,
	`Ma_Thuoc` VARCHAR(20) NOT NULL,
	`So_Luong` INT UNSIGNED,
	`Gia_Ban` DOUBLE UNSIGNED,
	PRIMARY KEY(`Ma_Don_Thuoc`,`Ma_Thuoc`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*===============================================================*/

/*===================bảng Diễn tiến bệnh===========================*/
CREATE TABLE `Dien_Tien_Benh`
(
	`Ma` VARCHAR(20) NOT NULL,
	`MaBN` VARCHAR(20) NOT NULL,
	`GhiChu` VARCHAR(255) NOT NULL,
	`NgayKham` DATE,
	PRIMARY KEY(`Ma`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `Dien_Tien_Benh_seq`
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);
/*===============================================================*/
/*===================Trigger tự động tăng id cho Diễn tiến bệnh===========================*/
DELIMITER $$
CREATE TRIGGER `Dien_Tien_Benh_insert`
BEFORE INSERT ON `Dien_Tien_Benh`
FOR EACH ROW
BEGIN
  INSERT INTO `Dien_Tien_Benh_seq` VALUES (NULL);
  SET NEW.Ma = CONCAT('DTB', LPAD(LAST_INSERT_ID(), 10, '0'));
END$$
DELIMITER ;
/*===============================================================*/
/*===================bảng thuốc===========================*/

CREATE TABLE `Thuoc`
(
	`Ma_Thuoc` VARCHAR(20) NOT NULL,
	`Ten_Thuoc` VARCHAR(30) NOT NULL,
	`Don_Vi` VARCHAR(15),
	`Gia_Ban` DOUBLE UNSIGNED,
	`Ton_Kho` INT,
	`Lieu_Luong_Mac_Dinh` INT UNSIGNED,
	`Cach_Dung` VARCHAR(100),
	`Nhom` VARCHAR(30),
	PRIMARY KEY(`Ma_Thuoc`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*===============================================================*/
INSERT INTO `Thuoc` VALUES ("TH00000238","ZOVIRAX Eye Ointment 3 %; 4,5g", "Ống","660000","100","3","Ngày tra mắt 3 - 4 lần","Kháng sinh");
INSERT INTO `Thuoc` VALUES ("TH00000182","VOSFAREL 20 mg", "Viên","2500","100","2","Ngày 1v x 2 lần","Tim mạch");
INSERT INTO `Thuoc` VALUES ("TH00000001","Vitamin B1 100mg", "Viên","4000","100","2","Ngày 1v x 2 lần","Vitamin & Thuốc bổ");
INSERT INTO `Thuoc` VALUES ("TH00000613","STILUX 60MG", "Viên","1000","100","1","1 viên tối","Thần kinh");
INSERT INTO `Thuoc` VALUES ("TH00000283","PARA ngọt 900 mg chia 4", "Gói","1000","100","1","chia 4","Hạ sốt, Giảm đau");
/*===================bảng dịch vụ===========================*/
CREATE TABLE `Dich_Vu`
(
	`Ma_DV` INT NOT NULL AUTO_INCREMENT,
	`Ten_DV` VARCHAR(50) NOT NULL,
	`Don_Gia` DOUBLE UNSIGNED,
	`Mo_Ta`  VARCHAR(100),
	`Nhom` VARCHAR(30),
	PRIMARY KEY(`Ma_DV`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*===============================================================*/
INSERT INTO `Dich_Vu`(Ten_DV, Don_Gia, Mo_Ta, Nhom) VALUES
('Khám sức khoẻ tổng quát định kỳ','90000','Khám sức khoẻ tổng quát định kỳ',''),
('Khám bệnh thông thường','70000','Khám bệnh thông thường',''),
('Khám tư vấn Bác sỹ','20000','Khám tư vấn Bác sỹ','');
/*===================bảng hoá đơn===========================*/
CREATE TABLE `Hoa_Don`
(
	`Ma_Hoa_Don` VARCHAR(20) NOT NULL DEFAULT '0',
	`MaBN` VARCHAR(20) NOT NULL,
	`Ngay_Lap` DATE,
	`Nguoi_Lap` VARCHAR(20),
	`Tong_Tien` DOUBLE UNSIGNED,
	PRIMARY KEY(Ma_Hoa_Don)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `Hoa_Don_seq`
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);
/*===============================================================*/
/*===================Trigger tự động tăng id cho hoá đơn===========================*/
DELIMITER $$
CREATE TRIGGER `Hoa_Don_insert`
BEFORE INSERT ON `Hoa_Don`
FOR EACH ROW
BEGIN
  INSERT INTO `Hoa_Don_seq` VALUES (NULL);
  SET NEW.Ma_Hoa_Don = CONCAT('HD', LPAD(LAST_INSERT_ID(), 10, '0'));
END$$
DELIMITER ;
/*==================================================================================*/
/*===================bảng Chi tiết đơn thuốc===========================*/
CREATE TABLE `Chi_Tiet_Hoa_Don_Don_Thuoc`
(
	`Ma_Hoa_Don` VARCHAR(20) NOT NULL,
	`Ma_Thuoc` VARCHAR(20) NOT NULL,
	`So_Luong` INT UNSIGNED,
	`Gia_Ban` DOUBLE UNSIGNED,
	PRIMARY KEY(`Ma_Hoa_Don`,`Ma_Thuoc`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*=====================================================================*/
/*===================bảng Chi tiết đơn thuốc===========================*/
CREATE TABLE `Chi_Tiet_Hoa_Don_Dich_Vu`
(
	`Ma_Hoa_Don` VARCHAR(20) NOT NULL,
	`Ma_DV` VARCHAR(20) NOT NULL,
	`Don_Gia` DOUBLE UNSIGNED,
	PRIMARY KEY(`Ma_Hoa_Don`,`Ma_DV`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*=====================================================================*/