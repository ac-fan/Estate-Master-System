package com.fan.wuye.controller.user;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.dao.UserInfoMapper;
import com.fan.wuye.pojo.UserInfo;
import com.fan.wuye.util.CommonVal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user/index")
public class USIndexController {
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 用户角色进入管理首页接口
     */
    @RequestMapping(value = "")
    public String index(ModelMap modelMap, HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName);
        UserInfo user = userInfoMapper.selectByPrimaryKey(login.getId());
        modelMap.addAttribute("user", user);

        modelMap.addAttribute("roleName", "用户");
        modelMap.addAttribute("login", login);

        return "user/index";
    }
}

