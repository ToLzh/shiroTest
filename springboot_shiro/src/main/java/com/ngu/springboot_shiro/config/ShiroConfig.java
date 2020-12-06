package com.ngu.springboot_shiro.config;

import com.ngu.springboot_shiro.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 创建shiroFilter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // filter设置阿暖管理
        bean.setSecurityManager(defaultSecurityManager);

        // 配置受限资源
        Map<String, String> map = new HashMap<>();
        // anon 代表放行
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        map.put("/register.jsp", "anon");
        // authc 代表要 授权 和 认证
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);

        // 设置默认页面
        bean.setLoginUrl("/login.jsp");

        return bean;
    }

    // 创建安全管理
    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager(Realm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager(realm);
        return manager;
    }

    // 自定义realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        // 修改密码校验凭证
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置加密算法
        matcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        matcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(matcher);
        return customerRealm;
    }
}
