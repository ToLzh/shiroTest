package md5Authen;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class TestShiroMD5 {
    public static void main(String[] args) {

//        Md5Hash md5Hash = new Md5Hash("123456","abc123",1024);
//        System.out.println(md5Hash.toHex());
//
        // 创建 securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        CustomerRealmMD5 customerRealmMD5 = new CustomerRealmMD5();
        /// 设置realm 使用hash凭证匹配
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");  // md5 加密
        hashedCredentialsMatcher.setHashIterations(1024);    // 散列次数 1024
        customerRealmMD5.setCredentialsMatcher(hashedCredentialsMatcher);
        // 设置自定义realm
        securityManager.setRealm(customerRealmMD5);

        // 设置安全工具类
        SecurityUtils.setSecurityManager(securityManager);

        // 获取object
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");

        try {
            // 6. 用户认证
            subject.login(token);
            System.out.println("登录状态：" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败，密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出错");
        }

        // 授权
        if (subject.isAuthenticated()) {
            // 单角色
            System.out.println("单角色："+subject.hasRole("admin"));

            // 多角色
            System.out.println("多角色："+subject.hasAllRoles(Arrays.asList("admin","user")));

            // 是否否具备其中一个角色
            subject.hasRoles(Arrays.asList("admin","manager"));

            System.out.println("判断是否拥有某个资源权限"+subject.isPermitted("user:add"));
        }
    }
}
