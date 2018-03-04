package com.youtao.manager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.youtao.manager.mapper.ItemParamValueMapper;
import com.youtao.manager.pojo.ItemParamValue;

/**
 * @title: ItemParamValueService
 * @description: 商品规格参数Service
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月4日 上午10:06:33
 */
@Service
public class ItemParamValueService extends BaseService<ItemParamValue> {
	
	@Autowired
	private ItemParamValueMapper itemParamValueMapper;

	/**
	 * 根据商品id更新规格参数
	 * @param record 包括商品id
	 */
	public void updateByItemIdSelective(ItemParamValue record) {
		record.setUpdated(new Date());
		Example example = new Example(ItemParamValue.class);
		example.createCriteria().andEqualTo("itemId", record.getItemId());
		this.itemParamValueMapper.updateByExampleSelective(record, example);
	}

}
