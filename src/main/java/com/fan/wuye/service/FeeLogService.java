package com.fan.wuye.service;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.pojo.FeeLog;

import java.util.Map;


public interface FeeLogService {
    /**
     * 分页查询每月费用数据列表
     */
    public Map<String, Object> getDataList(FeeLog queryModel, Integer page,
                                           Integer pageSize, LoginModel login);

    /**
     * 封装每月费用为前台展示的数据形式
     */
    public Map<String, Object> getFeeLogModel(FeeLog model, LoginModel login);

    /**
     * 删除数据
     */
    public void delete(Integer id);

    /**
     * 新增费用单
     */
    public String add(FeeLog model, LoginModel login);
}

