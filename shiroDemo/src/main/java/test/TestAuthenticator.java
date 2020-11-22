package test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class TestAuthenticator {
    public static void main(String[] args) {

        //1. 创建安全管理器对象
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 2. 给安全管理器设置realm
        defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        //3. defaultSecurityManager 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 4. 获取关键对象主体 subject
        Subject subject = SecurityUtils.getSubject();

        //5. 创建令牌
        // 正确的用户信息
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        // 错误的用户信息
//        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
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


    }
}
