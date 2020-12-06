package com.ngu.springboot_shiro.service.impl;

import com.ngu.springboot_shiro.dao.UsersDao;
import com.ngu.springboot_shiro.domain.Users;
import com.ngu.springboot_shiro.service.UsersService;
import com.ngu.springboot_shiro.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public Users findByUserName(String username) {
        return usersDao.findByUserName(username);
    }

    @Override
    public void register(Users users) {
        // 生成随机盐
        String salt = SaltUtil.getSalt(8);
        // 保存数据库
        users.setSalt(salt);
        // 明文密码 md5  + salt + hash
        Md5Hash hash = new Md5Hash(users.getPassword(), users.getSalt(), 1024);
        users.setPassword(hash.toHex());

        usersDao.save(users);
    }
}
