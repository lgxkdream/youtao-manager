package com.youtao.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.youtao.common.bean.EasyUIResult;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
	
	private static final String SUCCESS_MESSAGE = "操作成功";
	private static final String FAIL_MESSAGE = "操作失败";

	@Autowired
	private ItemService itemService;

	/**
	 * 商品添加
	 * @param item 商品信息
	 * @param desc 商品描述
	 * @param itemParams 商品规格参数
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> saveItem(@Validated Item item, BindingResult bindingResult, String desc, String itemParams) {
		LOGGER.debug("商品添加，item = {}，desc = {}", item, desc);
		try {
			// 参数校验
			if (bindingResult.hasErrors()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage());
			}
			this.itemService.saveItemAndDesc(item, desc, itemParams);
			LOGGER.info("商品添加成功，itemId = {}", item.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESS_MESSAGE);
		} catch (Exception e) {
			LOGGER.error("商品添加报错，item = " + item + "，desc = " + desc, e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FAIL_MESSAGE);
	}
	
	/**
	 * 商品更新
	 * @param item 商品信息
	 * @param bindingResult 参数校验结果(不必传)
	 * @param desc 商品描述
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateItem(@Validated Item item, BindingResult bindingResult, String desc, String itemParams) {
		LOGGER.debug("商品修改，item = {}，desc = {}", item, desc);
		try {
			// 参数校验
			if (bindingResult.hasErrors()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage());
			}
			this.itemService.updateItemAndDesc(item, desc, itemParams);
			LOGGER.info("商品修改成功，itemId = {}", item.getId());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(SUCCESS_MESSAGE);
		} catch (Exception e) {
			LOGGER.error("商品修改报错，item = " + item + "，desc = " + desc, e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FAIL_MESSAGE);
	}
	
	/**
	 * 分页查询商品列表
	 * @param page 页数
	 * @param rows 页大小
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EasyUIResult> queryItemList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
		try {
			EasyUIResult easyUIResult = this.itemService.queryItemList(page, rows);
			return ResponseEntity.ok(easyUIResult);
		} catch (Exception e) {
			LOGGER.error("分页查询商品列表失败", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
