package md5Authen;

import domain.Users;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

// 自定义 realm  将认证、授权的数据来源执行数据库
public class CustomerRealmMD5 extends AuthorizingRealm {

    // 授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = principalCollection.getPrimaryPrincipal().toString();
        System.out.println("用户：" + name);

        // 从数据库中找用户信息，赋予角色
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(Arrays.asList("user","admin"));

        // 赋予资源权限
        authorizationInfo.addStringPermission("user:*");
        authorizationInfo.addStringPermission("product:find:01");
        return authorizationInfo;
    }

    // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在token 中获取去用户名
        String username = token.getPrincipal().toString();
        System.out.println("token中获取到的用户名:"+username);

        // 连接数据库 获取用户信息 ，假设下面users就是从数据库拿到的用户信息
        Users users = new Users("zhangsan","ebb0ce60d9b8cd19f6908c4c3a1fbe2f");
        if (!users.getName().equals(username)) {
            return null;
        }

        // 参数1：数据库用户名   参数2：数据库密码  参数3：盐 参数4：当前对象realm名
        // 该方法会 自动 校验密码
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(
                        users.getName(),
                        users.getPassword(),
                        ByteSource.Util.bytes("abc123"),
                        this.getName());

        return simpleAuthenticationInfo;
    }

}
