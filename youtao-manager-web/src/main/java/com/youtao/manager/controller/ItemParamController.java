package com.youtao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youtao.manager.pojo.ItemParam;
import com.youtao.manager.service.ItemParamService;

/**
 * @title: ItemParamController
 * @description: 商品规格参数模板Controller
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月4日 上午10:08:29
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	/**
	 * 根据商品类目id查询参数模板
	 * @param itemCatId 商品类目id
	 * @return
	 */
	@RequestMapping(value = "{itemCatId}", method = RequestMethod.GET)
	public ResponseEntity<ItemParam> queryItemParamList(@PathVariable("itemCatId") Long itemCatId) {
		try {
			ItemParam record = new ItemParam();
			record.setItemCatId(itemCatId);
			ItemParam itemParam = this.itemParamService.queryOne(record);
			if (null == itemParam) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 商品规格参数模板添加
	 * @param itemCatId
	 * @param paramData
	 * @return
	 */
	@RequestMapping(value = "{itemCatId}", method = RequestMethod.POST)
	public ResponseEntity<Void> savaItemParam(@PathVariable("itemCatId") Long itemCatId, String paramData) {
		try {
			ItemParam record = new ItemParam();
			record.setItemCatId(itemCatId);
			record.setParamData(paramData);
			this.itemParamService.save(record);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
