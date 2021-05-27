package com.fan.wuye.service.impl;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.dao.FeeLogMapper;
import com.fan.wuye.dao.UserInfoMapper;
import com.fan.wuye.pojo.FeeLog;
import com.fan.wuye.pojo.FeeLogExample;
import com.fan.wuye.pojo.UserInfo;
import com.fan.wuye.service.FeeLogService;
import com.fan.wuye.util.DataListUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class FeeLogServiceImpl implements FeeLogService {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    FeeLogMapper feeLogMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 新增费用单
     */
    @Override
    public String add(FeeLog model, LoginModel login) {
        model.setIsJf(1); //默认未缴费,
        model.setLogDate(sdf2.format(new Date())); //当前日期格式
        model.setCreateTime(sdf1.format(new Date())); //当前时间格式

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(model.getUserId()); //查询用户供以下填入其他字段

        if (userInfo != null) {
            model.setWyFee(userInfo.getWyFee());
        }

        if (userInfo != null) {
            model.setCarFee(userInfo.getCarFee());
        }

        feeLogMapper.insertSelective(model); //插入数据

        return "";
    }

    /**
     * 根据参数查询每月费用列表数据
     */
    @Override
    public Map<String, Object> getDataList(FeeLog queryModel, Integer page,
                                           Integer pageSize, LoginModel login) {
        FeeLogExample se = new FeeLogExample();
        FeeLogExample.Criteria sc = se.createCriteria();
        se.setOrderByClause("id desc"); //默认按照id倒序排序

        if (queryModel.getId() != null) {
            sc.andIdEqualTo(queryModel.getId());
        }

        if (queryModel.getIsJf() != null) {
            sc.andIsJfEqualTo(queryModel.getIsJf()); //查询是否已缴费等于指定值
        }

        if (queryModel.getUserId() != null) {
            sc.andUserIdEqualTo(queryModel.getUserId());
        }

        int count = (int) feeLogMapper.countByExample(se);
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

        List<FeeLog> list = feeLogMapper.selectByExample(se); //执行查询语句
        Map<String, Object> rs = new HashMap<String, Object>();
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

        for (FeeLog model : list) {
            list2.add(getFeeLogModel(model, login));
        }

        rs.put("list", list2); //数据列表
        rs.put("count", count); //数据总数
        rs.put("totalPage", totalPage); //总页数

        return rs;
    }

    /**
     * 封装每月费用为前台展示的数据形式
     */
    @Override
    public Map<String, Object> getFeeLogModel(FeeLog model, LoginModel login) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("feeLog", model);

        if (model.getUserId() != null) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(model.getUserId()); //用户id为外接字段,需要关联用户来解释该字段

            if (userInfo != null) {
                map.put("userIdStr", userInfo.getName());
            }
        }

        map.put("isJfStr", DataListUtils.getIsJfNameById(model.getIsJf() + "")); //解释是否已缴费字段

        return map;
    }

    /**
     * 删除数据
     */
    @Override
    public void delete(Integer id) {
        feeLogMapper.deleteByPrimaryKey(id);
    }
}

