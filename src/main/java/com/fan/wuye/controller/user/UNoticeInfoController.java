package com.fan.wuye.controller.user;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.dao.NoticeInfoMapper;
import com.fan.wuye.dao.UserInfoMapper;
import com.fan.wuye.pojo.NoticeInfo;
import com.fan.wuye.pojo.UserInfo;
import com.fan.wuye.service.NoticeInfoService;
import com.fan.wuye.util.CommonVal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user/notice_info")
public class UNoticeInfoController {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    NoticeInfoService noticeInfoService;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    NoticeInfoMapper noticeInfoMapper;

    /**
     * 返回公告列表jsp页面
     */
    @RequestMapping(value = "")
    public String index(ModelMap modelMap, HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName); //获取当前登录账号信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(login.getId());
        modelMap.addAttribute("user", userInfo);

        return "user/notice_info/list";
    }

    /**
     * 根据查询条件分页查询公告数据,然后返回给前台渲染
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Object toList(NoticeInfo model, Integer page,
                         String createTimeOrder, HttpServletRequest request) {
        LoginModel login = (LoginModel) request.getSession()
                .getAttribute(CommonVal.sessionName);

        return noticeInfoService.getDataList(createTimeOrder, model, page,
                CommonVal.pageSize, login); //分页查询数据
    }
}

