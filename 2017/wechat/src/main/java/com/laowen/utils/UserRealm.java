package com.laowen.utils;

import com.laowen.bean.SysPermission;
import com.laowen.bean.SysRole;
import com.laowen.bean.SysUser;
import com.laowen.dao.SysPermissionMapper;
import com.laowen.dao.SysRoleMapper;
import com.laowen.dao.SysUserMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(sysUser.getUserId()), SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //赋予角色
        for (SysRole role : sysRoleMapper.findListByUserId(sysUser.getUserId())) {
            info.addRole(role.getRoleCode());
        }
        //赋予权限
        for (SysPermission perm : sysPermissionMapper.findListPermByUserId(sysUser.getUserId())) {
            if (StringUtils.isNotBlank(perm.getPermCode())) {
                info.addStringPermission(perm.getPermCode());
            }
        }

        return info;
    }

    /**
     * 登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser sysUser = sysUserMapper.findByUserAccount(token.getUsername());
        if (sysUser != null) {
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", sysUser);
            return new SimpleAuthenticationInfo(sysUser, sysUser.getUserPwd(), ByteSource.Util.bytes(sysUser.getUserPwd()), getName());
        }
        return null;
    }
}
