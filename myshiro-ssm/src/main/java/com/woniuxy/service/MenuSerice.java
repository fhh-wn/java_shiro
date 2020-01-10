package com.woniuxy.service;


import com.woniuxy.pojo.Menu;

import java.util.List;
import java.util.Set;

public interface MenuSerice {
    public List<Menu> getAllMenus();
    public Set<String> getUrlByUserName(String userName);

}
