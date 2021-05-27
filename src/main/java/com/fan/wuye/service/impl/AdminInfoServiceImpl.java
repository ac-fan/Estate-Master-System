package com.fan.wuye.service.impl;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.dao.AdminInfoMapper;
import com.fan.wuye.pojo.AdminInfo;
import com.fan.wuye.pojo.AdminInfoExample;
import com.fan.wuye.pojo.UserInfo;
import com.fan.wuye.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    AdminInfoMapper adminInfoMapper;

    /**
     * 根据参数查询管理员列表数据
     */
    @Override
    public Map<String, Object> getDataList(AdminInfo queryModel, Integer page,
                                           Integer pageSize, LoginModel login) {
        AdminInfoExample se = new AdminInfoExample();
        AdminInfoExample.Criteria sc = se.createCriteria();
        se.setOrderByClause("id desc"); //默认按照id倒序排序

        if (queryModel.getId() != null) {
            sc.andIdEqualTo(queryModel.getId());
        }

        int count = (int) adminInfoMapper.countByExample(se);
        int totalPage = 0;

        if ((page != null) && (pageSize != null)) { //分页

            if ((count > 0) && ((count % pageSize) == 0)) {
                totalPage = count / pageSize;
            } else {
                totalPage = (count / pageSize) + 1;
            }

            se.setPageRows(pageSize);
            se.setStartRow((page - 1) * pageSize);
        }

        List<AdminInfo> list = adminInfoMapper.selectByExample(se); //执行查询语句
        Map<String, Object> rs = new HashMap<String, Object>();
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

        for (AdminInfo model : list) {
            list2.add(getAdminInfoModel(model, login));
        }

        rs.put("list", list2); //数据列表
        rs.put("count", count); //数据总数
        rs.put("totalPage", totalPage); //总页数

        return rs;
    }

    /**
     * 封装管理员为前台展示的数据形式
     */
    @Override
    public Map<String, Object> getAdminInfoModel(AdminInfo model,
                                                 LoginModel login) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("adminInfo", model);

        return map;
    }

    /**
     * 新增
     */
    @Override
    public String add(AdminInfo model, LoginModel login) {
        model.setCreateTime(sdf1.format(new Date())); //当前时间格式
        adminInfoMapper.insertSelective(model); //插入数据
        return "";
    }


}

