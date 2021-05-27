package com.fan.wuye.service.impl;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.dao.NewsInfoMapper;
import com.fan.wuye.pojo.NewsInfo;
import com.fan.wuye.pojo.NewsInfoExample;
import com.fan.wuye.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class NewsInfoServiceImpl implements NewsInfoService {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    NewsInfoMapper newsInfoMapper;

    /**
     * 新增
     */
    @Override
    public String add(NewsInfo model, LoginModel login) {
        model.setCreateTime(sdf1.format(new Date())); //当前时间格式
        newsInfoMapper.insertSelective(model); //插入数据

        return "";
    }

    /**
     * 修改
     */
    @Override
    public String update(NewsInfo model, LoginModel login) {
        NewsInfo preModel = newsInfoMapper.selectByPrimaryKey(model.getId());
        preModel.setTitle(model.getTitle());
        preModel.setContent(model.getContent());
        newsInfoMapper.updateByPrimaryKey(preModel); //更新数据

        return "";
    }

    /**
     * 根据参数查询新闻列表数据
     */
    @Override
    public Map<String, Object> getDataList(String createTimeOrder,
                                           NewsInfo queryModel, Integer page, Integer pageSize, LoginModel login) {
        NewsInfoExample se = new NewsInfoExample();
        NewsInfoExample.Criteria sc = se.createCriteria();
        se.setOrderByClause("id desc"); //默认按照id倒序排序

        if (queryModel.getId() != null) {
            sc.andIdEqualTo(queryModel.getId());
        }

        if ((queryModel.getTitle() != null) &&
                (queryModel.getTitle().equals("") == false)) {
            sc.andTitleLike("%" + queryModel.getTitle() + "%"); //模糊查询
        }

        if ((createTimeOrder != null) && (createTimeOrder.equals("") == false)) {
            se.setOrderByClause(createTimeOrder); //按照创建时间排序
        }

        int count = (int) newsInfoMapper.countByExample(se);
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

        List<NewsInfo> list = newsInfoMapper.selectByExample(se); //执行查询语句
        Map<String, Object> rs = new HashMap<String, Object>();
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

        for (NewsInfo model : list) {
            list2.add(getNewsInfoModel(model, login));
        }

        rs.put("list", list2); //数据列表
        rs.put("count", count); //数据总数
        rs.put("totalPage", totalPage); //总页数

        return rs;
    }

    /**
     * 封装新闻为前台展示的数据形式
     */
    @Override
    public Map<String, Object> getNewsInfoModel(NewsInfo model, LoginModel login) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("newsInfo", model);

        return map;
    }

    /**
     * 删除数据
     */
    @Override
    public void delete(Integer id) {
        newsInfoMapper.deleteByPrimaryKey(id);
    }
}

