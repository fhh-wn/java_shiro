package com.woniuxy.service.impl;

import com.woniuxy.bean.RoleBean;
import com.woniuxy.dao.RoleDao;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;


    @Override
    public List<RoleBean> getAllRoleBeans(Integer uid) {
        List<RoleBean> roleBeans = new ArrayList<RoleBean>();
        List<Role> allRole = roleDao.getAllRole();
        Integer[] roleIdByUserId = roleDao.getRoleIdByUserId(uid);
        if(allRole!=null&&!allRole.isEmpty()){

            for (Role r:allRole){
                RoleBean rb=new RoleBean();
                rb.setRole(r);
                for (int i=0;i<roleIdByUserId.length;i++){
                    if(roleIdByUserId[i]==r.getId()){
                        rb.setCheck("checked='checked'");
                    }
                }
                roleBeans.add(rb);
            }
        }

        return roleBeans;
    }

    @Override
    public boolean assignRoleByUid(Integer userId, String roleArray) {
        System.out.println(roleArray);
        boolean flag=false;
        try {
            this.roleDao.delete(userId);
            String[] roleIds=roleArray.split(",");
            if(roleIds!=null&&roleIds.length>0){
                for (String roleId:roleIds){
                    int roleIdInt = Integer.parseInt(roleId);
                    Map<String,Integer> params = new HashMap<String,Integer>();
                    params.put("roleId",roleIdInt);
                    params.put("userId",userId);
                    this.roleDao.save(params);
                }
            }
            flag = true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public List<Role> getUserByPages(Integer currentPage, Integer lineSize) {
        Integer from = (currentPage-1)*lineSize;
        return roleDao.getRolesByPages(from,lineSize);
    }

    @Override
    public int getCount() {
        return roleDao.getCount();
    }

    @Override
    public int getTotalPage(Integer count, Integer lineSize) {
        return count%lineSize==0?count/lineSize:count/lineSize+1;
    }


}
