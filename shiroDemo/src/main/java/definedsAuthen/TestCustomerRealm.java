package definedsAuthen;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class TestCustomerRealm {
    public static void main(String[] args) {

        // 创建 securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 设置自定义realm
        securityManager.setRealm(new CustomerRealm());

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
    }
}
