package com.woniuxy.service.impl;

import com.woniuxy.dao.MenuDao;
import com.woniuxy.pojo.Menu;
import com.woniuxy.service.MenuSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuSerice {
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<Menu> getAllMenus() {
        return menuDao.getAllMenus();
    }

    @Override
    public Set<String> getUrlByUserName(String userName) {
        Set<String> urlByUserName = menuDao.getUrlByUserName(userName);

        return urlByUserName;
    }
}
