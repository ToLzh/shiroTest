package definedsAuthen;

import domain.Users;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

// 自定义 realm  将认证、授权的数据来源执行数据库
public class CustomerRealm extends AuthorizingRealm {

    // 授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在token 中获取去用户名
        String username = token.getPrincipal().toString();
        System.out.println("token中获取到的用户名:"+username);

        // 连接数据库 获取用户信息 ，假设下面users就是从数据库拿到的用户信息
        Users users = new Users("zhangsan","123456");
        if (!users.getName().equals(username)) {
            return null;
        }

        // 参数1：数据库用户名   参数2：数据库密码   参数3：当前对象realm名
        // 该方法会 自动 校验密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(users.getName(), users.getPassword(), this.getName());

        return simpleAuthenticationInfo;
    }

}
