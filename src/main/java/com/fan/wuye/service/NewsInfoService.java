package com.fan.wuye.service;

import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.pojo.NewsInfo;

import java.util.Map;


public interface NewsInfoService {
    /**
     * 分页查询新闻数据列表
     */
    public Map<String, Object> getDataList(String createTimeOrder,
                                           NewsInfo queryModel, Integer page, Integer pageSize, LoginModel login);

    /**
     * 封装新闻为前台展示的数据形式
     */
    public Map<String, Object> getNewsInfoModel(NewsInfo model, LoginModel login);

    /**
     * 删除数据
     */
    public void delete(Integer id);

    /**
     * 新增
     */
    public String add(NewsInfo model, LoginModel login);

    /**
     * 修改
     */
    public String update(NewsInfo model, LoginModel login);
}

