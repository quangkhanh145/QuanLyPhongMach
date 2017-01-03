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
	`Id` VARCHAR(5) NOT NULL,
	`Ten_Dang_Nhap` VARCHAR(20) NOT NULL,
	`Mat_Khau` VARCHAR(15) NOT NULL,
	`Nhom` VARCHAR(30),
	`Ten` VARCHAR(30) NOT NULL,
	`SDT` VARCHAR(12),
	`Email` VARCHAR(30),
	PRIMARY KEY(`Id`),
	UNIQUE(`Ten_Dang_Nhap`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*===============================================================*/
INSERT INTO `Tai_Khoan` VALUES
('TK001','admin','admin','Admins','Đỗ Quang Khánh','01664202120','quangkhanh@gmail.com'),
('TK002','bacsi','bacsi','Bác sĩ','Đỗ Quang Khánh','01664202120','quangkhanh@gmail.com'),
('TK003','nhaplieu','nhaplieu','Nhập liệu','Đỗ Quang Khánh','01664202120','quangkhanh@gmail.com');

/*===================bảng bệnh nhân===========================*/
CREATE TABLE `Benh_Nhan`
(
	`MaBN` VARCHAR(20) NOT NULL,
	`Ten`  VARCHAR(30) NOT NULL,
	`Ngay_Sinh`  DATE,
	`Gioi_Tinh` VARCHAR(10),
	`SDT` VARCHAR(12),
	`Dia_Chi`  VARCHAR(100),
	`Nhom` VARCHAR(30),
	PRIMARY KEY(`MaBN`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*===============================================================*/
INSERT INTO `Benh_Nhan` VALUES
('BN00000001','Mai Phương Thảo','1995-1-1','Nữ','094912364','TP.HCM',NULL),
('BN00000002','Trần Việt Dũng','1996-2-1','Nam','0918716482','Đồng Nai',NULL),
('BN00000003','Đinh Huy Hoàng','2000-4-3','Nam','0984618237','Cần Thơ',NULL),
('BN00000004','Vũ Phương Thảo','1989-2-2','Nữ','0914618613','An Giang',NULL),
('BN00000005','Mai Phương Lan','1968-12-8','Nữ','0947145612','Long An',NULL);

/*===================bảng đơn thuốc===========================*/
CREATE TABLE `Don_Thuoc`
(
	`Ma_Don_Thuoc` VARCHAR(20) NOT NULL,
	`MaBN` VARCHAR(20) NOT NULL,
	`Chan_Doan` VARCHAR(100),
	`Loi_Dan` VARCHAR(100),
	`Ngay_Lap` DATE,
	PRIMARY KEY(`Ma_Don_Thuoc`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*===============================================================*/

/*===================bảng Chi tiết đơn thuốc===========================*/
CREATE TABLE `Chi_Tiet_Don_Thuoc`
(
	`Ma_Don_Thuoc` VARCHAR(20) NOT NULL,
	`Ma_Thuoc` VARCHAR(20) NOT NULL,
	`So_Luong` INT UNSIGNED,
	`Gia_Ban` DOUBLE UNSIGNED,
	`Cach_Dung` VARCHAR(100),
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
	`Ma_DV` VARCHAR(20) NOT NULL,
	`Ten_DV` VARCHAR(30) NOT NULL,
	`Don_Vi` VARCHAR(15),
	`Don_Gia` FLOAT UNSIGNED,
	`So_Luong_Mac_Dinh` INT UNSIGNED,
	`Mo_Ta`  VARCHAR(100),
	`Nhom` VARCHAR(30),
	PRIMARY KEY(`Ma_DV`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*===============================================================*/
