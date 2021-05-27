package com.fan.wuye.service;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.pojo.ComplainInfo;

import java.util.Map;


public interface ComplainInfoService {
    /**
     * 分页查询投诉数据列表
     */
    public Map<String, Object> getDataList(ComplainInfo queryModel,
                                           Integer page, Integer pageSize, LoginModel login);

    /**
     * 封装投诉为前台展示的数据形式
     */
    public Map<String, Object> getComplainInfoModel(ComplainInfo model,
                                                    LoginModel login);

    /**
     * 删除数据
     */
    public void delete(Integer id);

    /**
     * 我要投诉
     */
    public String add(ComplainInfo model, LoginModel login);

    /**
     * 修改
     */
    public String update(ComplainInfo model, LoginModel login);

    /**
     * 处理
     */
    public String update3(ComplainInfo model, LoginModel login);
}

