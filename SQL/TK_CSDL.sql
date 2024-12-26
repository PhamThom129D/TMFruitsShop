create
database TMFruitsShop;
use
TMFruitsShop;
CREATE TABLE user
(
    userID      INT PRIMARY KEY AUTO_INCREMENT,
    username    NVARCHAR(255),
    password    VARCHAR(255),
    email       VARCHAR(255) UNIQUE,
    phonenumber VARCHAR(255),
    address     VARCHAR(255),
    status      BOOLEAN      DEFAULT 1,
    role        VARCHAR(255) DEFAULT 'user',
    urlAvatar   VARCHAR(255)
);

CREATE TABLE products
(
    productID   INT PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(255),
    price       INT,
    description NVARCHAR(300),
    type        VARCHAR(255),
    stock       INT,
    urlImage    VARCHAR(255)
);

CREATE TABLE `Order`
(
    orderID     INT PRIMARY KEY AUTO_INCREMENT,
    orderDate   DATETIME,
    userID      INT,
    statusOrder ENUM('Pending', 'Cancel', 'Paid'),
     paymentMethod VARCHAR(255),
    FOREIGN KEY (userID) REFERENCES user (userID)
);

CREATE TABLE Products_Order
(
    orderID   INT,
    productID INT,
    quantity  INT,
    FOREIGN KEY (orderID) REFERENCES `Order` (orderID),
    FOREIGN KEY (productID) REFERENCES products (productID)
);
CREATE TABLE Invoices
(
    invoiceID     INT PRIMARY KEY AUTO_INCREMENT,
    orderID       INT,
    paymentDate   DATETIME,
    total         INT,
    FOREIGN KEY (orderID) REFERENCES `Order` (orderID)
);

INSERT INTO user (username, password, email, phonenumber, address, role, urlAvatar)
VALUES ('Ngọc Thơm', 'Vy1209', 'vy9@gmail.com', '123456789', '123 Street A', 'admin',
        'https://i.pinimg.com/736x/a8/5a/75/a85a7549a2926f1698af2709f40aafed.jpg'),
       ('Minh', 'Minh123', 'Minh@gmail.com', '987654321', '456 Street B', 'admin',
        'https://i.pinimg.com/736x/72/fd/9a/72fd9a62d275b4d9404ccd77a45f0fbd.jpg'),
       ('Tuấn Minh', 'Minh123', 'Minh01@gmail.com', '987654321', '456 Street B', 'user',
        'https://i.pinimg.com/736x/a5/26/aa/a526aad5c1019d247c1034e1d62a961b.jpg'),
       ('Khánh ăn cứt', 'Khanh123', 'Khanh@gmail.com', '123123123', '789 Street C', 'user',
        'https://i.pinimg.com/736x/3d/9d/f8/3d9df862c7324184208e94edbb16e590.jpg');

INSERT INTO products (productName, price, description, type, stock, urlImage)
VALUES ('Táo', 50000, 'Táo tươi ngon, mọng nước, giàu dinh dưỡng, hỗ trợ hệ miễn dịch và giảm cân.', 'Trái cây', 100,
        'https://i.pinimg.com/736x/1b/09/70/1b09709a5436f1c4936ab99d924bf959.jpg'),
       ('Chuối', 30000, 'Chuối chín vàng ngọt ngào, bổ sung năng lượng và tốt cho tiêu hoá.', 'Trái cây', 200,
        'https://dinhduong.online/wp-content/uploads/2016/07/cong-dung-qua-chuoi-voi-suc-khoe.jpg'),
       ('Cam', 40000, 'Cam mọng nước, vị ngọt thanh, giàu vitamin C tốt cho làn da và sức khoẻ.', 'Trái cây', 150,
        'https://product.hstatic.net/200000077081/product/navel-uc-892_48d3110f5f1649548db69b333c4bacbc_1024x1024.jpg'),
       ('Cà rốt', 20000, 'Cà rốt tươi, giòn ngọt, giàu vitamin A tốt cho mắt và làn da.', 'Rau củ', 300,
        'https://www.mamnon.com/ShowTopicSubImage.aspx?id=76224'),
       ('Khoai tây', 25000, 'Khoai tây chất lượng cao, giàu tinh bột, thích hợp cho các món ăn ngon.', 'Rau củ', 400,
        'https://media.istockphoto.com/id/157430678/vi/anh/ba-c%E1%BB%A7-khoai-t%C3%A2y.jpg?s=612x612&w=0&k=20&c=4ETBuHT2Fuv6iFamy11uJruc23Dia5t7YFL2YIC1DMM='),
       ('Cherry', 70000, 'Cherry mọng nước, ngọt lịm, chứa nhiều chất chống oxy hoá tốt cho tim mạch.', 'Trái cây', 150,
        'https://hinh365.com/wp-content/uploads/2020/06/4ab375acf2a0279ddf09e3bc5baa8705.jpg'),
       ('Nho xanh', 50000, 'Nho xanh tươi giòn, vị ngọt thanh, cung cấp năng lượng và tốt cho tiêu hoá.', 'Trái cây',
        150, 'https://www.conngongvang.com/wp-content/uploads/2019/01/nho_xanh_ninh_thuan-431x431.jpg'),
       ('Nho tím', 65000, 'Nho tím ngọt ngào, giàu chất chống oxy hoá giúp tăng cường sức đề kháng.', 'Trái cây', 120,
        'https://www.conngongvang.com/wp-content/uploads/2019/01/nho_den_ninh_thuan-431x431.jpg'),
       ('Nho đỏ', 60000, 'Nho đỏ không hạt, mọng nước, cung cấp nhiều vitamin và khoáng chất.', 'Trái cây', 100,
        'https://nafarm.vn/upload/images/nho-do-tuoi-khong-hat-my-1kg-dac-san-nafarm.jpg'),
       ('Dâu tây', 80000, 'Dâu tây thơm ngon, giàu chất xơ và vitamin C tốt cho hệ miễn dịch.', 'Trái cây', 80,
        'https://suckhoedoisong.qltns.mediacdn.vn/2015/4-loi-ich-tuyet-voi-cua-dau-tay-4-1422241944000.jpg'),
       ('Quả Bơ', 60000, 'Bơ chín mềm, béo ngậy, chứa nhiều axit béo tốt cho tim mạch.', 'Trái cây', 100,
        'https://bizweb.dktcdn.net/100/439/247/products/050fcbf2-b060-487e-88e3-c30611756cd3.jpg?v=1637558598707'),
       ('Quả Na', 40000, 'Na to tròn, chín đều, vị ngọt thanh mát, cung cấp nhiều năng lượng.', 'Trái cây', 100,
        'https://www.lottemart.vn/media/catalog/product/cache/0x0/2/1/2162640000005-1-bb.jpg.webp'),
       ('Chôm chôm', 50000, 'Chôm chôm đỏ mọng, thịt quả ngọt lịm, giàu vitamin và khoáng chất.', 'Trái cây', 100,
        'https://hoptacxathanhbinh.com/wp-content/uploads/2023/08/rambutan.png'),
       ('Quả Ổi', 30000, 'Ổi giòn tươi, vị ngọt nhẹ, giàu vitamin C giúp tăng cường sức đề kháng.', 'Trái cây', 100,
        'https://cdnphoto.dantri.com.vn/EHz1iHLEoP7XhEdij8BcuFgSXD8=/zoom/1200_630/2024/01/03/oi-b-crop-1704254876374.jpeg'),
       ('Quả Thanh Long', 25000, 'Thăng long ruột đỏ, mọng nước, vị thanh mát, tốt cho tiêu hoá.', 'Trái cây', 100,
        'https://www.lottemart.vn/media/catalog/product/cache/0x0/2/2/2274090000006.jpg.webp'),
       ('Quả Dứa', 20000, 'Dứa vàng tươi, vị ngọt chua nhẹ, chứa nhiều enzyme hỗ trợ tiêu hoá.', 'Trái cây', 100,
        'https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2019/11/17/766605/Tac-Dung-Giam-Can-Hi.jpg'),
       ('Quả Vú Sữa', 40000, 'Vú sữa tươi ngon, vị ngọt béo, chứa nhiều vitamin và khoáng chất.', 'Trái cây', 100,
        'https://i.ex-cdn.com/suckhoecongdongonline.vn/files/content/2021/08/04/vu-sua-1443.png'),
       ('Đu đủ', 22000, 'Đu đủ chín, vị ngọt thanh, giàu vitamin A và tốt cho tiêu hoá.', 'Trái cây', 100,
        'https://product.hstatic.net/1000282430/product/du-du-do_a925cf1dca944fa5aea5fafa75c85656_master.jpg'),
       ('Quả Ngô', 50000, 'Ngô ngọt và ngon', 'Trái cây', 100,
        'https://hn.check.net.vn/data/product/mainimages/original/product16160.jpg'),
       ('Hồng xiêm', 50000, 'Hồng xiêm ta ngon ngọt', 'Trái cây', 100,
        'https://hoaquanhapkhau.net/wp-content/uploads/2020/02/hong-xiem.jpg'),
       ('Quả Kiwi', 60000, 'Thơm ngon', 'Trái cây', 100,
        'https://upload.wikimedia.org/wikipedia/commons/b/b8/Kiwi_%28Actinidia_chinensis%29_1_Luc_Viatour.jpg'),
       ('Quả Lê', 25000, 'Có tác dụng tuyệt vời đối với sức khoẻ', 'Trái cây', 100,
        'https://hoangdongfood.com/wp-content/uploads/2018/12/pear.jpg'),
       ('Dưa lưới', 30000, 'Quả nuôi trồng sạch', 'Trái cây', 100,
        'https://storage.vinaseed.com.vn/Data/2020/03/09/1-dua-golden-honey-637193667738402750.jpg'),
       ('Dưa hấu', 35000, 'Dưa hấu nhập khẩu Hàn Quốc', 'Trái cây', 100,
        'https://www.lottemart.vn/media/catalog/product/cache/0x0/2/2/2274080000009-1.jpg.webp'),
       ('Thăng long', 20000, 'Thăng long ruột đỏ', 'Trái cây', 100,
        'https://hoayeuthuong.com/hinh-hoa-tuoi/moingay/11964_thanh-long-ruot-do--traikg.jpg'),
       ('Combo Hoa Quả Tặng 1', 150000,
        'Giá cả hợp lý và đa dạng loại quả đẹp, ngon, được lựa chọn kỹ lưỡng về hình thức và chất lượng', 'Combo', 50,
        'https://shophoaqua.vn/public/media/file/files/trai-cay-nhap-khau/350.12.jpg'),
       ('Combo Hoa Quả Tặng 2', 160000, 'Nhiều loại quả nhập khẩu chất lượng cao, tươi ngon, phù hợp để làm quà tặng',
        'Combo', 45, 'https://shophoaqua.vn/public/media/file/files/lang-trai-cay/420.jpg'),
       ('Combo Hoa Quả Tặng 3', 155000,
        'Hộp quà trái cây đa dạng, được lựa chọn kỹ càng để đảm bảo độ tươi ngon và đẹp mắt', 'Combo', 60,
        'https://shophoaqua.vn/public/media/file/files/lang-trai-cay/600%20.jpg'),
       ('Combo Hoa Quả Tặng 4', 145000, 'Kết hợp các loại trái cây tươi và chất lượng, giá trị hợp lý cho mọi người',
        'Combo', 40, 'https://shophoaqua.vn/public/media/file/files/lang-trai-cay/3590.png'),
       ('Combo Hoa Quả Tặng 5', 170000, 'Trái cây nhập khẩu chất lượng cao, được đóng gói đẹp mắt và sang trọng',
        'Combo', 55, 'https://shophoaqua.vn/public/media/file/files/trai-cay-nhap-khau/350.12.jpg'),
       ('Combo Hoa Quả Tặng 6', 150000, 'Hộp quà trái cây đa dạng, đảm bảo chất lượng và thẩm mỹ cao', 'Combo', 50,
        'https://shophoaqua.vn/public/media/file/files/lang-trai-cay/1650.png'),
       ('Combo Hoa Quả Tặng 7', 165000, 'Bộ quà tặng trái cây tươi ngon, đẹp mắt, lý tưởng để làm quà', 'Combo', 35,
        'https://shophoaqua.vn/public/media/file/files/lang-trai-cay/700.png'),
       ('Combo Hoa Quả Tặng 8', 140000, 'Trái cây được tuyển chọn kỹ lưỡng, hộp quà phù hợp với mọi dịp tặng quà',
        'Combo', 70, 'https://shophoaqua.vn/public/media/file/files/lang-trai-cay/503.jpg'),
       ('Combo Hoa Quả Tặng 9', 150000, 'Hộp quà trái cây với các loại quả ngon, chất lượng và đóng gói tinh tế',
        'Combo', 50, 'https://shophoaqua.vn/public/media/file/files/lang-trai-cay/550.jpg'),
       ('Combo Hoa Quả Tặng 10', 175000,
        'Sự kết hợp hoàn hảo giữa hình thức và chất lượng, phù hợp làm quà tặng cao cấp', 'Combo', 30);
