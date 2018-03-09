package com.youtao.manager.controller.api;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youtao.manager.pojo.Item;
import com.youtao.manager.service.ItemService;

/**
 * @title: ApiItemController
 * @description: 商品对外接口
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月9日 下午4:26:42
 */
@Controller
@RequestMapping("/api/item")
public class ApiItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 商品详情
	 * @param ItemId 商品id
	 * @return
	 */
	@RequestMapping(value = "{itemId}", method =RequestMethod.GET)
	public ResponseEntity<Item> queryItemById(@PathVariable("itemId") Long itemId) {
		try {
			Item item = this.itemService.queryById(itemId);
			if (Objects.isNull(item)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
