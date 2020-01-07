package com.baizhi.realm;

import com.baizhi.entity.Student;
import com.baizhi.service.StudentService;
import com.baizhi.utils.ApplicationUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm1 extends AuthorizingRealm {
    // 权限
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取主身份
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        // 给用户设置权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");
        info.addRole("adminSuper");
        info.addStringPermission("admin:delete");
        return info;
    }

    // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        // 通过类对象获取类的对象
        StudentService studentSercvice = (StudentService) ApplicationUtil.getBean(StudentService.class);
        // 通过身份信息到数据库进行查询
        Student student = studentSercvice.queryByName(principal);
        // 判断是否有该信息
        AuthenticationInfo authenticationInfo = null;
        if(principal.equals(student.getName())){
            authenticationInfo = new SimpleAuthenticationInfo(principal,"037ec07a0d30c6d8898ade3b698010da", ByteSource.Util.bytes(student.getSalt()),this.getName());
        }
        return authenticationInfo;
    }
}
