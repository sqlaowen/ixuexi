package com.x2016.util;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.poco.SysPermission;
import com.x2016.poco.SysRole;
import com.x2016.poco.SysUser;
import com.x2016.service.SysPermissionService;
import com.x2016.service.SysRoleService;
import com.x2016.service.SysUserService;


@Service
public class UserRealm extends AuthorizingRealm {
  @Autowired
  private SysUserService sysUserService;
  
  @Autowired
  private SysRoleService sysRoleService;
  
  @Autowired
  private SysPermissionService sysPermissionService;
  
  /**
   * 权限
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SysUser user = (SysUser) principals.getPrimaryPrincipal();
    SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getUserId()),SecurityUtils.getSubject().getPrincipals());
    
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    //赋予角色
    for(SysRole role:sysRoleService.findListByUserId(user.getUserId())){
      info.addRole(role.getRoleCode());
    }
    //赋予权限
    for(SysPermission perm:sysPermissionService.findListPermByUserId(user.getUserId())){
      if(StringUtils.isNotBlank(perm.getPermCode()))
        info.addStringPermission(perm.getPermCode());
    }
    
    return info;
  }
  
  /**
   * 登录
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; 
    SysUser user = sysUserService.findByUserAccount(token.getUsername());
    if (user != null) {
      Session session =SecurityUtils.getSubject().getSession();
      session.setAttribute("user", user);
      return new SimpleAuthenticationInfo(user,user.getUserPwd(),ByteSource.Util.bytes(user.getUserPwd()),getName());
    }
    return null;
  }
}
