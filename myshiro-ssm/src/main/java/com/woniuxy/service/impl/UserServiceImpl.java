package com.woniuxy.service.impl;

import com.woniuxy.dao.UserDao;
import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.utils.Contants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUserName(String userName) {
        User u = userDao.getUserByUserName(userName);
        return u;
    }

    @Override
    public User getLogin(String name, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        try {
            subject.login(token);
            Session session = subject.getSession();
            User sessionUser = this.getUserByUserName(name);

            session.setAttribute(Contants.SESSION_USER, sessionUser);
            return sessionUser;
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> getUserByPages(Integer currentPage, Integer lineSize) {
        Integer from = (currentPage-1)*lineSize;
        return userDao.getUserByPages(from,lineSize);
    }

    @Override
    public int getCount() {
        return userDao.getCount();
    }

    @Override
    public int getTotalPage(Integer count, Integer lineSize) {
        return count%lineSize==0?count/lineSize:count/lineSize+1;
    }
}
