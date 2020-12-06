package com.ngu.springboot_shiro.dao;

import com.ngu.springboot_shiro.domain.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersDao {
    void save(Users users);

    Users findByUserName(String username);
}
