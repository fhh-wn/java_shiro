package com.woniuxy.dao;

import com.woniuxy.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User getUserByUserName(String userName);
    public List<User> getUserByPages(@Param("from") Integer from, @Param("lineSize")Integer lineSize);
    public int getCount();

}
