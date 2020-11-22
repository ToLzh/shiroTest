package com.ngu.springboot_shiro.shiro.realms;

import com.ngu.springboot_shiro.domain.Users;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("进入授权");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在token 中获取去用户名
        String username = token.getPrincipal().toString();
        System.out.println("token中获取到的用户名:"+username);

        // 连接数据库 获取用户信息 ，假设下面users就是从数据库拿到的用户信息
        Users users = new Users("zhangsan","123456");
        if (!users.getName().equals(username)) {
            return null;
        }

        // 参数1：数据库用户名   参数2：数据库密码  参数3：盐 参数4：当前对象realm名
        // 该方法会 自动 校验密码
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(
                        users.getName(),
                        users.getPassword(),
//                        ByteSource.Util.bytes("abc123"),
                        this.getName());

        return simpleAuthenticationInfo;
    }
}
