package com.youtao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youtao.manager.pojo.Item;
import com.youtao.manager.service.ItemService;

/**
 * @title: ItemController
 * @description: 商品信息Controller
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月27日 下午3:08:21
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	
	private static final String SUCCESS_MESSAGE = "操作成功";
	private static final String FAIL_MESSAGE = "操作失败";

	@Autowired
	private ItemService itemService;

	/**
	 * 商品添加
	 * @param item 商品信息
	 * @param desc 商品描述
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> saveItem(@Validated Item item, BindingResult bindingResult, String desc) {
		try {
			// 参数校验
			if (bindingResult.hasErrors()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage());
			}
			this.itemService.saveItemAndDesc(item, desc);
			return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FAIL_MESSAGE);
	}

}
