package com.woniuxy.dao;

import com.woniuxy.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleDao {
    public List<Role> getAllRole();
    public Integer[] getRoleIdByUserId(Integer userId);
    public void save(Map<String,Integer> params);
    public void delete(Integer userId);
    public List<Role> getRolesByPages(@Param("from") Integer from, @Param("lineSize")Integer lineSize);
    public int getCount();
}
