package com.example.tmfruitsshop.Model;

public class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String role;
    private String address;
    private String urlAvatar;
    private Boolean status;

    public User() {}

    public User(int userID, String username, String password, String email, String phonenumber, String role, String address, String urlAvatar, Boolean status) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
        this.address = address;
        this.urlAvatar = urlAvatar;
        this.status = status;
    }
    public User(String username, String password, String email, String phonenumber, String address, String urlAvatar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.urlAvatar = urlAvatar;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                ", urlAvatar='" + urlAvatar + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
