package com.youtao.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.youtao.common.bean.EasyUIResult;
import com.youtao.manager.pojo.Item;
import com.youtao.manager.pojo.ItemDesc;
import com.youtao.manager.pojo.ItemParamValue;

/**
 * @title: ItemService
 * @description: 商品信息Service
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月27日 下午3:04:44
 */
@Service
public class ItemService extends BaseService<Item> {
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemParamValueService itemParamValueService;

	/**
	 * 保存商品信息(包括描述)
	 * @param item 商品信息
	 * @param desc 商品描述
	 * @param itemParams 商品规格参数
	 */
	public void saveItemAndDesc(Item item, String desc, String itemParams) {
		item.setId(null); // 出于安全考虑，强制设置为null
		item.setStatus(1); // 默认状态为正常
		this.save(item);
		// 保存商品描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescService.save(itemDesc);
		// 保存商品规格参数
		ItemParamValue itemParamValue = new ItemParamValue();
		itemParamValue.setItemId(item.getId());
		itemParamValue.setParamData(itemParams);
		itemParamValueService.save(itemParamValue);
	}
	
	/**
	 * 更新商品信息(包括描述)
	 * @param item 商品信息
	 * @param desc 商品描述
	 */
	public void updateItemAndDesc(Item item, String desc, String itemParams) {
		// 强制设置不能更新的字段为null
		item.setStatus(null);
		this.updateSelective(item);
		// 更新商品描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescService.updateSelective(itemDesc);
		// 更新 商品规格参数
		ItemParamValue itemParamValue = new ItemParamValue();
		itemParamValue.setItemId(item.getId());
		itemParamValue.setParamData(itemParams);
		itemParamValueService.updateByItemIdSelective(itemParamValue);
	}

	/**
	 * 分页查询商品列表
	 * @param page 页数
	 * @param rows 页大小
	 * @return
	 */
	public EasyUIResult queryItemList(Integer page, Integer rows) {
		PageInfo<Item> pageInfo = this.querySortPages(page, rows, "updated DESC", Item.class);
		return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
	}


}
