package com.laowen.controller;


import com.laowen.bean.SysUser;
import com.laowen.dao.SysUserMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @RequestMapping(value = "/login")
    public String login() {
        // 获取当前的Subject
        Subject subject = SecurityUtils.getSubject();
        // 验证是否登录成功
        if (subject.isAuthenticated()) {
            return "redirect:/index";
        }
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(String userAccount, String userPwd, HttpServletRequest request) throws IOException {

        request.setAttribute("userAccount", userAccount);
        request.setAttribute("userPwd", userPwd);

        List<String> errList = new ArrayList<String>();
        if (StringUtils.isBlank(userAccount)) {
            errList.add("【账号】为空...");
        }
        if (StringUtils.isBlank(userPwd)) {
            errList.add("【密码】为空...");
        }
        if (0 != errList.size()) {
            request.setAttribute("error", errList);
            return "/login";
        }

        SysUser user = sysUserMapper.findByUserAccount(userAccount);
        if (null == user) {
            errList.add("账号【" + userAccount + "】不存在...");
            request.setAttribute("error", errList);
            return "/login";
        }
        if (!userPwd.equalsIgnoreCase(user.getUserPwd())) {
            errList.add("密码不正确...");
            request.setAttribute("error", errList);
            return "/login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userAccount, userPwd);
        //token.setRememberMe(true);
        // 获取当前的Subject
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        //InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";
        //user = (SysUser) currentUser.getPrincipals().getPrimaryPrincipal();
        // 验证是否登录成功
        if (subject.isAuthenticated()) {
            return "redirect:/index";
        }
        return "/login";
    }

    @RequestMapping("/index")
    public String adminIndex() {
        return "/index";
    }

    /**
     * 用户登出
     */
    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "/login";
    }
}
