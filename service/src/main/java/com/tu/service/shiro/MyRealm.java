package com.tu.service.shiro;

import com.tu.common.util.MD5Util;
import com.tu.curd.model.ShiroUser;
import com.tu.curd.service.IShiroUserService;
import com.tu.service.service.IShiroAccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by len on 2019/1/24.
 */
public class MyRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private IShiroAccountService shiroAccountService;

    @Autowired
    private IShiroUserService shiroUserService;

    /***
     * 获取授权信息
     */
    /**
     * 授权:
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Object principal = principalCollection.getPrimaryPrincipal();//获取登录的用户名
        if("tuyongjian".equals(principal)){               //两个if根据判断赋予登录用户权限
            info.addRole("admin");
        }
        if("user".equals(principal)){
            info.addRole("list");
        }

        info.addRole("admin");

        return info;
    }

    /*
     * 用户验证
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. token 中获取登录的 username! 注意不需要获取password.
        Object principal = token.getPrincipal();

        //2. 利用 username 查询数据库得到用户的信息.
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setUserName((String) principal);
        ShiroUser user=shiroUserService.queryModel(shiroUser);
        String pass="";
        if(user!=null){
            pass=user.getPassword();
        }
        String credentials = pass;
        logger.info("--------------------credentials---------"+credentials);
        //3.设置盐值 ，（加密的调料，让加密出来的东西更具安全性，一般是通过数据库查询出来的。 简单的说，就是把密码根据特定的东西而进行动态加密，如果别人不知道你的盐值，就解不出你的密码）
        String source = "tyj";
        ByteSource credentialsSalt = new Md5Hash(source);
        logger.info("-------------------credentials-------------"+credentialsSalt);

        //当前 Realm 的name
        String realmName = getName();
        //返回值实例化
        SimpleAuthenticationInfo info =new SimpleAuthenticationInfo(principal, credentials,credentialsSalt, realmName);

        return info;
    }


    //init-method 配置.
    public void setCredentialMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");//MD5算法加密
        credentialsMatcher.setHashIterations(2);//1024次循环加密
        setCredentialsMatcher(credentialsMatcher);
    }
}
