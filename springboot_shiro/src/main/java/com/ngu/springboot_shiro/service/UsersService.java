package com.ngu.springboot_shiro.service;

import com.ngu.springboot_shiro.domain.Users;

public interface UsersService {
    void register(Users users);

    Users findByUserName(String username);
}
