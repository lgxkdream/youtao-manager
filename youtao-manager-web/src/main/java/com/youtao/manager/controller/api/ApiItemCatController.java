package com.youtao.manager.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youtao.common.bean.ItemCatResult;
import com.youtao.manager.service.ItemCatService;

/**
 * @title: ApiItemCatController
 * @description: 商品类目对外接口
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月5日 上午11:00:08
 */
@Controller
@RequestMapping("/api/item/cat")
public class ApiItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 类目列表查询
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ItemCatResult> queryItemCatList(String callback) {
		try {
			ItemCatResult itemCatResult = this.itemCatService.queryItemCatList();
			if (null == itemCatResult) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemCatResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
