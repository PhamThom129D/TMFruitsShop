package com.example.tmfruitsshop.Service;

import com.example.tmfruitsshop.Model.User;

import javax.servlet.http.HttpServletRequest;

public interface InUserService {

    public User login(HttpServletRequest req,String email, String password);

}
