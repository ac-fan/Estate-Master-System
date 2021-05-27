package com.fan.wuye.controller.admin;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.dao.AdminInfoMapper;
import com.fan.wuye.dao.ComplainInfoMapper;
import com.fan.wuye.pojo.AdminInfo;
import com.fan.wuye.pojo.ComplainInfo;
import com.fan.wuye.service.ComplainInfoService;
import com.fan.wuye.util.CommonVal;
import com.fan.wuye.util.DataListUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/admin/complain_info")
public class AComplainInfoController {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    ComplainInfoService complainInfoService;
    @Autowired
    AdminInfoMapper adminInfoMapper;
    @Autowired
    ComplainInfoMapper complainInfoMapper;

    public void getList(ModelMap modelMap, LoginModel login) { //获取数据列表并返回给前台
        modelMap.addAttribute("isDealList", DataListUtils.getIsDealList()); //返回is_deal数据列表
    }

    /**
     * 返回投诉列表jsp页面
     */
    @RequestMapping(value = "")
    public String index(ModelMap modelMap, HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName); //获取当前登录账号信息
        AdminInfo adminInfo = adminInfoMapper.selectByPrimaryKey(login.getId());
        modelMap.addAttribute("user", adminInfo);
        getList(modelMap, login); //获取数据列表并返回给前台

        return "admin/complain_info/list";
    }

    /**
     * 根据查询条件分页查询投诉数据,然后返回给前台渲染
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Object toList(ComplainInfo model, Integer page,
                         HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName);

        return complainInfoService.getDataList(model, page, CommonVal.pageSize,
                login); //分页查询数据
    }

    /**
     * 进入处理页面
     */
    @RequestMapping("update3")
    public String update3(ModelMap modelMap, ComplainInfo model,
                          HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName); //从session中获取当前登录账号
        getList(modelMap, login); //获取前台需要用到的数据列表并返回给前台

        ComplainInfo data = complainInfoMapper.selectByPrimaryKey(model.getId());
        modelMap.addAttribute("data", data);

        return "admin/complain_info/update3_page";
    }

    /**
     * 处理提交信息接口
     */
    @RequestMapping("update3_submit")
    @ResponseBody
    public Object update3_submit(ComplainInfo model, ModelMap modelMap,
                                 HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName);
        Map<String, Object> rs = new HashMap<String, Object>();
        String msg = complainInfoService.update3(model, login); //执行修改操作

        if (msg.equals("")) {
            rs.put("code", 1);
            rs.put("msg",
                    "修改成功");

            return rs;
        }

        rs.put("code", 0);
        rs.put("msg", msg);

        return rs;
    }
}

