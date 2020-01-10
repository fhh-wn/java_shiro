package com.woniuxy.service;

import com.woniuxy.bean.RoleBean;
import com.woniuxy.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleService {
    public List<RoleBean> getAllRoleBeans(Integer uid);
    public boolean assignRoleByUid(Integer userId,String roleArray);

    public List<Role> getUserByPages(Integer currentPage, Integer lineSize);
    public int getCount();
    public int getTotalPage(Integer count,Integer lineSize);
}
