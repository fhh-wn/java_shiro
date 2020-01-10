package com.woniuxy.realm;

import com.woniuxy.pojo.User;
import com.woniuxy.service.MenuSerice;
import com.woniuxy.service.UserService;
import com.woniuxy.utils.Contants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuSerice menuSerice;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===============授权调用============");
        Subject subject= SecurityUtils.getSubject();
        Session session=subject.getSession();
        User sessionUser=(User)session.getAttribute(Contants.SESSION_USER);
      //  System.out.println(sessionUser);
        Set<String> permssionList=menuSerice.getUrlByUserName(sessionUser.getUserName());
        Set<String> all=new HashSet<>();
        if(permssionList!=null&&!permssionList.isEmpty()){
            for(String url:permssionList){
                if(url.indexOf("/")!=-1){
                    url=url.substring(0,url.lastIndexOf("/"));
                    all.add(url+":*");
                }
            }
        }
        System.out.println("++++++++++++++++++++++++++++");
        for(String item:all){
            System.out.println(item);
        }
        System.out.println("++++++++++++++++++++++++++++++++");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(all);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("================认证调用============");
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.getUserByUserName(userName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,user.getPassword(),this.getName());

        return info;
    }
}
