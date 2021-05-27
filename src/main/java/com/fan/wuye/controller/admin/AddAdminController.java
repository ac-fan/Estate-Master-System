package com.fan.wuye.controller.admin;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.dao.AdminInfoMapper;
import com.fan.wuye.dao.UserInfoMapper;
import com.fan.wuye.pojo.AdminInfo;
import com.fan.wuye.pojo.UserInfo;
import com.fan.wuye.service.AdminInfoService;
import com.fan.wuye.util.CommonVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/commonapi")
public class AddAdminController {
    @Autowired
    AdminInfoMapper adminInfoMapper;
    @Autowired
    AdminInfoService adminInfoService;

    /**
     * 进入注册页面
     */
    @RequestMapping("addAdmin")
    public String add(ModelMap modelMap,  AdminInfo model,
                      HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName); //从session中获取当前登录账号
        modelMap.addAttribute("data", model);

        return "admin/add_admin";
    }

    /**
     * 新增提交信息接口
     */
    @RequestMapping("add_submit")
    @ResponseBody
    public Object add_submit(AdminInfo model, ModelMap modelMap,
                             HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName);
        Map<String, Object> rs = new HashMap<String, Object>();
        String msg = adminInfoService.add(model, login); //执行添加操作
        modelMap.addAttribute("data", model);
        if (msg.equals("")) {
            rs.put("code", 1);
            rs.put("msg",
                    "注册成功");

            return rs;
        }

        rs.put("code", 0);
        rs.put("msg", msg);

        return rs;
    }

}

