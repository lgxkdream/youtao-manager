package com.youtao.manager.service;

import com.github.abel533.mapper.Mapper;
import com.youtao.manager.mapper.ItemCatMapper;
import com.youtao.manager.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gang.li
 * @version 1.0.0
 * @title: ItemCatServiceImpl
 * @description: 商品类目Service实现
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @since 2018年2月25日 下午12:31:14
 */
@Service
public class ItemCatService extends BaseService<ItemCat> {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public Mapper<ItemCat> getMapper() {
        return this.itemCatMapper;
    }

}
