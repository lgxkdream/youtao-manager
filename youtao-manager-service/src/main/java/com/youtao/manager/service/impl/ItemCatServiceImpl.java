package com.youtao.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtao.manager.mapper.ItemCatMapper;
import com.youtao.manager.pojo.ItemCat;
import com.youtao.manager.service.ItemCatService;

/**
 * 
 * @title: ItemCatServiceImpl
 * @description: 商品类目Service实现
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月25日 下午12:31:14
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public List<ItemCat> queryItemCatListByParentId(Long parentId) {
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		return this.itemCatMapper.select(itemCat);
	}
	
}
