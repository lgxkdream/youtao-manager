package com.youtao.manager.service;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youtao.manager.pojo.BasePojo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title: BaseService
 * @description: 基础Service
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月26日 上午12:08:39
 */
public abstract class BaseService<T extends BasePojo> {

    @Autowired
    private Mapper<T> mapper;

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return
     */
    public T queryById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<T> queryAll() {
        return this.mapper.select(null);
    }

    /**
     * 根据条件查询一条
     *
     * @param record 条件
     * @return
     */
    public T queryOne(T record) {
        return this.mapper.selectOne(record);
    }

    /**
     * 根据条件查询多条
     *
     * @param record 条件
     * @return
     */
    public List<T> queryList(T record) {
        return this.mapper.select(record);
    }

    /**
     * 根据条件分页查询多条
     *
     * @param record 条件
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return
     */
    public PageInfo<T> queryPages(T record, int pageNum, int pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = this.queryList(record);
        return new PageInfo<T>(list);
    }

    /**
     * 保存
     *
     * @param record 记录
     * @return
     */
    public int save(T record) {
        Date date = new Date();
        record.setCreated(date);
        record.setUpdated(date);
        return this.mapper.insert(record);
    }

    /**
     * 字段选择性(不为null)保存
     *
     * @param record 记录
     * @return
     */
    public int saveSelective(T record) {
        Date date = new Date();
        record.setCreated(date);
        record.setUpdated(date);
        return this.mapper.insertSelective(record);
    }

    /**
     * 更新
     *
     * @param record 记录
     * @return
     */
    public int update(T record) {
        record.setUpdated(new Date());
        return this.mapper.updateByPrimaryKey(record);
    }

    /**
     * 字段选择性(不为null)更新
     *
     * @param record 记录
     * @return
     */
    public int updateSelective(T record) {
        record.setCreated(null);
        record.setUpdated(new Date());
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return
     */
    public int deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    /**
     * 批零删除
     *
     * @param clazz 实体Class对象
     * @param property 字段名
     * @param ids 字段值列表
     * @return
     */
    public int deleteByIds(Class<T> clazz, String property, List<Object> ids) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, ids);
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据条件删除
     *
     * @param record 条件
     * @return
     */
    public int delete(T record) {
        return this.mapper.delete(record);
    }

}
