package com.example.tmfruitsshop.Service;

import com.example.tmfruitsshop.Model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService implements InUserService {

    @Override
    public User login(HttpServletRequest req, String email, String password) {
        String query = "SELECT * FROM user WHERE LOWER(email) = LOWER(?) AND password = ?";
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("userID");
                String username = rs.getString("username");
                String role = rs.getString("role");
                String address = rs.getString("address");
                String phone = rs.getString("phonenumber");
                String urlAvatar = rs.getString("urlAvatar");
                Boolean status = rs.getBoolean("status");
                User user = new User(userID, username, password, email, phone, role, address, urlAvatar, status);
                HttpSession session = req.getSession(true);
                if(!status){
                    return null;
                }else{
                    session.setAttribute("loggedInUser", user);
                    switch (role) {
                        case "admin":
                            session.setAttribute("isAdmin", true);
                            break;
                        case "user":
                            session.setAttribute("isUser", true);
                            break;
                    }
                }
                return user;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}