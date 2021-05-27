package com.fan.wuye.service;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.pojo.CarUserInfo;

import java.util.Map;


public interface CarUserInfoService {
    /**
     * 分页查询车位分配数据列表
     */
    public Map<String, Object> getDataList(CarUserInfo queryModel,
                                           Integer page, Integer pageSize, LoginModel login);

    /**
     * 封装车位分配为前台展示的数据形式
     */
    public Map<String, Object> getCarUserInfoModel(CarUserInfo model,
                                                   LoginModel login);

    /**
     * 分配车位
     */
    public String add(CarUserInfo model, LoginModel login);
}

