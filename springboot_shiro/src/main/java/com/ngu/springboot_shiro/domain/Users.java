package com.ngu.springboot_shiro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Integer id;
    private String name;
    private String password;
    private String salt;

//    public Users(String name, String password) {
//        this.name = name;
//        this.password = password;
//    }
}
