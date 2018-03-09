package com.youtao.manager.controller.api;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youtao.manager.pojo.ItemDesc;
import com.youtao.manager.service.ItemDescService;

/**
 * @title: ApiItemDescController
 * @description: 商品描述对外接口
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月9日 下午4:56:54
 */
@Controller
@RequestMapping("/api/item/desc")
public class ApiItemDescController {
	
	@Autowired
	private ItemDescService itemDescService;
	
	/**
	 * 商品详情
	 * @param ItemId 商品id
	 * @return
	 */
	@RequestMapping(value = "{itemId}", method =RequestMethod.GET)
	public ResponseEntity<ItemDesc> queryItemDescById(@PathVariable("itemId") Long itemId) {
		try {
			ItemDesc record = new ItemDesc();
			record.setItemId(itemId);
			ItemDesc itemDesc = this.itemDescService.queryOne(record);
			if (Objects.isNull(itemDesc)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
