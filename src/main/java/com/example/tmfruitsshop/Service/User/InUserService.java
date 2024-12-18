package com.example.tmfruitsshop.Service.User;

import com.example.tmfruitsshop.Model.User;

import javax.servlet.http.HttpServletRequest;

public interface InUserService {

    User login(HttpServletRequest req, String email, String password);

    void register(User user);

}
