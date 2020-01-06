package com.baizhi.realm;

import com.baizhi.entity.Student;
import com.baizhi.service.StudentService;
import com.baizhi.utils.ApplicationUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

import java.util.List;

public class MyRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        // 调用service
        StudentService bean = (StudentService) ApplicationUtil.getBean(StudentService.class);

        Student student = bean.queryByName(principal);
        AuthenticationInfo authenticationInfo = null;
        if(principal.equals(student.getName())){
            authenticationInfo = new SimpleAuthenticationInfo(principal,student.getPassword(),this.getName());
        }
        return authenticationInfo;
    }
}
