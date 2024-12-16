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
INSERT INTO user (username, password, email, phonenumber, address, status, role, urlAvatar)
VALUES 
('Alice', 'password123', 'alice@example.com', '123456789', '123 Street A', 1, 'user', 'https://example.com/avatar1.jpg'),
('Bob', 'password456', 'bob@example.com', '987654321', '456 Street B', 1, 'admin', 'https://example.com/avatar2.jpg'),
('Charlie', 'password789', 'charlie@example.com', '123123123', '789 Street C', 0, 'user', 'https://example.com/avatar3.jpg');
INSERT INTO products (productName, price, description, type, stock, urlImage)
VALUES 
('Apple', 5000, 'Fresh and juicy apple', 'Fruit', 100, 'https://example.com/apple.jpg'),
('Banana', 3000, 'Sweet bananas', 'Fruit', 200, 'https://example.com/banana.jpg'),
('Orange', 4000, 'Citrus orange', 'Fruit', 150, 'https://example.com/orange.jpg'),
('Carrot', 2000, 'Fresh carrot', 'Vegetable', 300, 'https://example.com/carrot.jpg'),
('Potato', 2500, 'Best quality potatoes', 'Vegetable', 400, 'https://example.com/potato.jpg');
INSERT INTO `Order` (orderDate, userID, statusOrder)
VALUES 
('2024-12-01 10:00:00', 1, 'Pending'),
('2024-12-02 12:30:00', 2, 'Paid'),
('2024-12-03 15:45:00', 3, 'Cancel');
INSERT INTO Products_Order (orderID, productID, quantity)
VALUES 
(1, 1, 5), -- Alice mua 5 quả táo
(1, 2, 3), -- Alice mua 3 quả chuối
(2, 3, 10), -- Bob mua 10 quả cam
(2, 4, 7), -- Bob mua 7 củ cà rốt
(3, 5, 20); -- Charlie đặt 20 củ khoai tây (đơn bị hủy)
INSERT INTO Invoices (orderID, paymentMethod, paymentDate, total)
VALUES 
(2, 'Credit Card', '2024-12-02 13:00:00', 58000), -- Đơn hàng của Bob (đã thanh toán)
(1, 'Cash', '2024-12-01 11:00:00', 39000); -- Đơn hàng của Alice (đang chờ thanh toán)


 