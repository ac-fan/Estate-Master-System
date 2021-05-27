package com.fan.wuye.service;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.pojo.CarPointInfo;

import java.util.Map;


public interface CarPointInfoService {
    /**
     * 分页查询车位数据列表
     */
    public Map<String, Object> getDataList(CarPointInfo queryModel,
                                           Integer page, Integer pageSize, LoginModel login);

    /**
     * 封装车位为前台展示的数据形式
     */
    public Map<String, Object> getCarPointInfoModel(CarPointInfo model,
                                                    LoginModel login);

    /**
     * 删除数据
     */
    public void delete(Integer id);

    /**
     * 新增
     */
    public String add(CarPointInfo model, LoginModel login);

    /**
     * 修改
     */
    public String update(CarPointInfo model, LoginModel login);
}

