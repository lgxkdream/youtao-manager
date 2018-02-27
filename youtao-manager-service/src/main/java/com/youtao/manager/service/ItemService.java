package com.youtao.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtao.manager.pojo.Item;
import com.youtao.manager.pojo.ItemDesc;

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

	/**
	 * 保存商品信息(包括描述)
	 * @param item 商品信息
	 * @param desc 商品描述
	 */
	public void saveItemAndDesc(Item item, String desc) {
		item.setId(null); // 出于安全考虑，强制设置为null
		item.setStatus(1); // 默认状态为正常
		this.save(item);
		// 保存商品描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescService.save(itemDesc);
	}

}
