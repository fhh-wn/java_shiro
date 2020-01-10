package com.woniuxy.service;

import com.woniuxy.pojo.User;

import java.util.List;

public interface UserService {
    public User getUserByUserName(String userName);
    public User getLogin(String name,String password);
    public List<User> getUserByPages(Integer currentPage,Integer lineSize);
    public int getCount();
    public int getTotalPage(Integer count,Integer lineSize);
}
