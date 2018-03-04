package com.youtao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youtao.manager.pojo.ItemParamValue;
import com.youtao.manager.service.ItemParamValueService;

/**
 * @title: ItemParamValueController
 * @description: 商品规格参数Controller
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月4日 上午10:09:46
 */
@Controller
@RequestMapping("/item/param/value")
public class ItemParamValueController {
	
	@Autowired
	private ItemParamValueService itemParamValueService;
	
	/**
	 * 根据商品id查询规格参数
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping(value = "{itemId}", method = RequestMethod.GET)
	public ResponseEntity<ItemParamValue> queryItemParamList(@PathVariable("itemId") Long itemId) {
		try {
			ItemParamValue record = new ItemParamValue();
			record.setItemId(itemId);
			ItemParamValue itemParamValue = itemParamValueService.queryOne(record);
			if (null == itemParamValue) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemParamValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
