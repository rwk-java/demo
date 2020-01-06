package com.baizhi.demo;

import com.baizhi.App;
import com.baizhi.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class ShiroTest {
    @Test
    public void select1(){
        Realm realm = new IniRealm("classpath:shiro.ini");
        // 1. 获取securitymanager
        SecurityManager securityManager = new DefaultSecurityManager(realm);

        SecurityUtils.setSecurityManager(securityManager);
        // 2. 获取主体
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("liuTao","111111");

        // 3. 登陆验证
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("账户输入不正确");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码输入不正确");
        } finally {
            boolean authenticated = subject.isAuthenticated();
            System.out.println("主体认证："+authenticated);
        }
    }
    @Test
    public void testMyShiro(){
        MyRealm myRealm = new MyRealm();
        SecurityManager securityManager = new DefaultSecurityManager(myRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("刘涛","111111");
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("账户输入不正确");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码输入不正确");
        } finally {
            boolean authenticated = subject.isAuthenticated();
            System.out.println("主体认证："+authenticated);
        }
    }
}
