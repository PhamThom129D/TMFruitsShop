package com.example.tmfruitsshop.Service.Admin;

import com.example.tmfruitsshop.Model.CartItem;
import com.example.tmfruitsshop.Model.Product;
import com.example.tmfruitsshop.Service.ConnectDatabase;
import com.example.tmfruitsshop.Model.Order;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdminService implements InAdminService {
    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products ORDER BY productID DESC";
        try (Connection conn = ConnectDatabase.getConnection();
             Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        String query = "SELECT * FROM products WHERE productID = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        String query = "UPDATE products SET productName = ?, stock = ?, price = ?, urlImage = ?, type = ?, description = ? WHERE productID = ?";

        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, product.getProductName());
            prep.setInt(2, product.getQuantity());
            prep.setInt(3, product.getPrice());
            prep.setString(4, product.getUrlImage());
            prep.setString(5, product.getType());
            prep.setString(6, product.getDescription());
            prep.setInt(7, product.getProductID());
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product product) {
        String query = "INSERT INTO products(productName, stock, price, urlImage, type, description) VALUES (?,?,?,?,?,?)";

        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, product.getProductName());
            prep.setInt(2, product.getQuantity());
            prep.setInt(3, product.getPrice());
            prep.setString(4, product.getUrlImage());
            prep.setString(5, product.getType());
            prep.setString(6, product.getDescription());
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> searchProductWithName(String keyword) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE productName LIKE ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, "%" + keyword + "%");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public List<Product> searchProductByType(String type) {
        List<Product> productList = new ArrayList<>();
        String category = "";

        if ("fruits".equalsIgnoreCase(type)) {
            category = "Trái cây";
        } else if ("vegetables".equalsIgnoreCase(type)) {
            category = "Rau củ";
        } else if ("combo".equalsIgnoreCase(type)) {
            category = "Combo";
        }

        String query = "SELECT * FROM products WHERE type = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, category);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }


    @Override
    public List<Product> searchProductByPrice(int min, int max) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, min);
            prep.setInt(2, max);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public List<Product> searchProductByName(String name) {
        return searchProductWithName(name);
    }

    @Override
    public int addOrder(int userID) {
        String query = "INSERT INTO `order`(orderDate, userID) VALUES (?, ?);";
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            prep.setTimestamp(1, timestamp);
            prep.setInt(2, userID);
            prep.executeUpdate();

            try (ResultSet generatedKeys = prep.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOrderDetail(int orderID, int productID, int quantity) {
        String query = "INSERT INTO `products_order` (orderID, productID, quantity) VALUES (?,?,?)";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, orderID);
            prep.setInt(2, productID);
            prep.setInt(3, quantity);
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<CartItem> getOrderDetail(int orderID) {
        String query = "SELECT \n" +
                "    p.productID,\n" +
                "    p.productName,\n" +
                "    p.price,\n" +
                "    po.quantity, p.urlImage \n" +
                "FROM \n" +
                "    Products_Order po\n" +
                "JOIN \n" +
                "    products p ON po.productID = p.productID\n" +
                "JOIN \n" +
                "    `Order` o ON po.orderID = o.orderID\n" +
                "WHERE \n" +
                "    o.orderID = ?";
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, orderID);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String urlImage = rs.getString("urlImage");
                CartItem cartItem = new CartItem(productName,price, quantity, urlImage);
                cartItems.add(cartItem);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }

    @Override
    public void updateStatusOrder(int orderID,String status,String paymentMethod) {
        String query = "UPDATE `order` SET statusOrder = ?, paymentMethod=? WHERE orderID = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, status);
            prep.setString(2, paymentMethod);
            prep.setInt(3, orderID);
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addInvoice(int orderID,double total) {
        String query = "INSERT INTO invoice (orderID,paymentDate, total) VALUES (?,?,?)";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, orderID);
            prep.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            prep.setDouble(3, total);
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getOrderByUserID(int userID) {
        String query = "SELECT \n" +
                "    o.orderID,\n" +
                "    o.orderDate,\n" +
                "    o.statusOrder,\n" +
                "    u.username,\n" +
                "    u.address,\n" +
                "    o.paymentMethod,\n" +
                "    p.productID,\n" +
                "    p.productName,\n" +
                "    po.quantity,\n" +
                "    p.price,\n" +
                "    p.urlImage,\n" +
                "    p.type,\n" +
                "    p.description\n" +
                "FROM `Order` o\n" +
                "JOIN user u ON o.userID = u.userID\n" +
                "JOIN Products_Order po ON o.orderID = po.orderID\n" +
                "JOIN products p ON po.productID = p.productID\n" +
                "WHERE u.userID = ?";
        List<Order> orders;
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, userID);
            ResultSet rs = prep.executeQuery();
            Map<Integer, Order> orderMap = new HashMap<>();

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                Order order = orderMap.get(orderID);

                if (order == null) {
                    order = new Order();
                    order.setOrderID(orderID);
                    order.setOrderDate(rs.getTimestamp("orderDate"));
                    order.setStatusOrder(rs.getString("statusOrder"));
                    order.setUsername(rs.getString("username"));
                    order.setAddress(rs.getString("address"));
                    order.setPaymentMethod(rs.getString("paymentMethod"));
                    order.setProductList(new ArrayList<>());
                    orderMap.put(orderID, order);
                }

                Product product = new Product();
                product.setProductID(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getInt("price"));
                product.setUrlImage(rs.getString("urlImage"));
                product.setType(rs.getString("type"));
                product.setDescription(rs.getString("description"));

                order.getProductList().add(product);
            }

            orders = new ArrayList<>(orderMap.values());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error fetching orders for userID " + userID, e);
        }
        return orders;
    }


    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        int productID = rs.getInt("productID");
        String productName = rs.getString("productName");
        int quantity = rs.getInt("stock");
        int price = rs.getInt("price");
        String urlImage = rs.getString("urlImage");
        String description = rs.getString("description");
        String type = rs.getString("type");
        return new Product(productID, productName, quantity, price, urlImage, type, description);
    }
}
