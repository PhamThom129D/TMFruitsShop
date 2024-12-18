create database TMFruitsShop;
use TMFruitsShop;
CREATE TABLE user (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phonenumber VARCHAR(255),
    address VARCHAR(255),
    status BOOLEAN DEFAULT 1, -- 1: active, 0: inactive
    role VARCHAR(255) DEFAULT 'user', -- user/admin
    urlAvatar VARCHAR(255)
);

CREATE TABLE products (
    productID INT PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(255),
    price INT,
    description VARCHAR(255),
    type VARCHAR(255), 
    stock INT,
    urlImage VARCHAR(255)
);

CREATE TABLE `Order` (
    orderID INT PRIMARY KEY AUTO_INCREMENT,
    orderDate DATETIME,
    userID INT, 
    statusOrder ENUM('Pending', 'Cancel', 'Paid') DEFAULT 'Pending',
    FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE Products_Order (
    orderID INT, 
    productID INT, 
    quantity INT, 
    FOREIGN KEY (orderID) REFERENCES `Order`(orderID),
    FOREIGN KEY (productID) REFERENCES products(productID)
);
CREATE TABLE Invoices (
    invoiceID INT PRIMARY KEY AUTO_INCREMENT, 
    orderID INT, 
    paymentMethod VARCHAR(255), 
    paymentDate DATETIME, 
    total INT, 
    FOREIGN KEY (orderID) REFERENCES `Order`(orderID)
);
INSERT INTO user (username, password, email, phonenumber, address, status, role, urlAvatar) VALUES
('Alice', 'password123', 'alice@example.com', '123456789', '123 Street A', 1, 'user', 'https://example.com/avatar1.jpg'),
('Bob', 'password456', 'bob@example.com', '987654321', '456 Street B', 1, 'admin', 'https://example.com/avatar2.jpg'),
('Charlie', 'password789', 'charlie@example.com', '123123123', '789 Street C', 0, 'user', 'https://example.com/avatar3.jpg');

INSERT INTO products (productName, price, description, type, stock, urlImage) VALUES
('Apple', 5000, 'Fresh and juicy apple', 'Fruit', 100, 'https://i.pinimg.com/736x/1b/09/70/1b09709a5436f1c4936ab99d924bf959.jpg'),
('Banana', 3000, 'Sweet bananas', 'Fruit', 200, 'https://suckhoedoisong.qltns.mediacdn.vn/324455921873985536/2022/12/26/chuoi-chin-16720695582281654655438.jpg'),
('Orange', 4000, 'Citrus orange', 'Fruit', 150, 'https://duoclienketphattrien.vn/wp-content/uploads/2015/10/cam.jpg'),
('Carrot', 2000, 'Fresh carrot', 'Vegetable', 300, 'https://image.tienphong.vn/w890/Uploaded/2024/rkznae/2021_08_03/ca-rot-5-fvsu-5709.jpg'),
('Potato', 2500, 'Best quality potatoes', 'Vegetable', 400, 'https://media.istockphoto.com/id/157430678/vi/anh/ba-c%E1%BB%A7-khoai-t%C3%A2y.jpg?s=612x612&w=0&k=20&c=4ETBuHT2Fuv6iFamy11uJruc23Dia5t7YFL2YIC1DMM='),
('Cherry', 7000, 'Citrus orange', 'Fruit', 150, 'https://cdn.tgdd.vn/2022/05/CookDishThumb/cherry-la-qua-gi-co-may-loai-phan-biet-cherry-my-voi-cherry-thumb-620x620.jpg'),
('Orange', 4000, 'Citrus orange', 'Fruit', 150, ''),
('Orange', 4000, 'Citrus orange', 'Fruit', 150, ''),
('Orange', 4000, 'Citrus orange', 'Fruit', 150, ''),
('Orange', 4000, 'Citrus orange', 'Fruit', 150, '');

INSERT INTO `Order` (orderDate, userID, statusOrder)VALUES
('2024-12-01 10:00:00', 1, 'Pending'),
('2024-12-06 12:30:00', 2, 'Paid'),
('2024-12-03 15:45:00', 3, 'Cancel'),
('2024-12-04 17:25:00', 4, 'Cancel'),
('2024-12-03 1:45:00', 5, 'Cancel'),
('2024-12-02 9:45:00', 6, 'Cancel');

INSERT INTO Products_Order (orderID, productID, quantity) VALUES
(1, 1, 5), -- Alice mua 5 quả táo
(1, 2, 3), -- Alice mua 3 quả chuối
(2, 3, 10), -- Bob mua 10 quả cam
(2, 4, 7), -- Bob mua 7 củ cà rốt
(3, 5, 20), -- Charlie đặt 20 củ khoai tây (đơn bị hủy)
(1, 1, 5),
(4, 2, 5),
(1, 1, 3),
(4, 1, 7),
(3, 3, 8);

INSERT INTO Invoices (orderID, paymentMethod, paymentDate, total) VALUES
(2, 'Credit Card', '2024-12-02 13:00:00', 58000), -- Đơn hàng của Bob (đã thanh toán)
(1, 'Cash', '2024-12-01 11:00:00', 39000); -- Đơn hàng của Alice (đang chờ thanh toán)


 