package com.fan.wuye.service.impl;


import com.fan.wuye.controller.LoginModel;
import com.fan.wuye.dao.NoticeInfoMapper;
import com.fan.wuye.pojo.NoticeInfo;
import com.fan.wuye.pojo.NoticeInfoExample;
import com.fan.wuye.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class NoticeInfoServiceImpl implements NoticeInfoService {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    NoticeInfoMapper noticeInfoMapper;

    /**
     * 新增
     */
    @Override
    public String add(NoticeInfo model, LoginModel login) {
        model.setCreateTime(sdf1.format(new Date())); //当前时间格式
        noticeInfoMapper.insertSelective(model); //插入数据

        return "";
    }

    /**
     * 修改
     */
    @Override
    public String update(NoticeInfo model, LoginModel login) {
        NoticeInfo preModel = noticeInfoMapper.selectByPrimaryKey(model.getId());
        preModel.setTitle(model.getTitle());
        preModel.setContent(model.getContent());
        noticeInfoMapper.updateByPrimaryKey(preModel); //更新数据

        return "";
    }

    /**
     * 根据参数查询公告列表数据
     */
    @Override
    public Map<String, Object> getDataList(String createTimeOrder,
                                           NoticeInfo queryModel, Integer page, Integer pageSize, LoginModel login) {
        NoticeInfoExample se = new NoticeInfoExample();
        NoticeInfoExample.Criteria sc = se.createCriteria();
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

        int count = (int) noticeInfoMapper.countByExample(se);
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

        List<NoticeInfo> list = noticeInfoMapper.selectByExample(se); //执行查询语句
        Map<String, Object> rs = new HashMap<String, Object>();
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

        for (NoticeInfo model : list) {
            list2.add(getNoticeInfoModel(model, login));
        }

        rs.put("list", list2); //数据列表
        rs.put("count", count); //数据总数
        rs.put("totalPage", totalPage); //总页数

        return rs;
    }

    /**
     * 封装公告为前台展示的数据形式
     */
    @Override
    public Map<String, Object> getNoticeInfoModel(NoticeInfo model,
                                                  LoginModel login) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("noticeInfo", model);

        return map;
    }

    /**
     * 删除数据
     */
    @Override
    public void delete(Integer id) {
        noticeInfoMapper.deleteByPrimaryKey(id);
    }
}

