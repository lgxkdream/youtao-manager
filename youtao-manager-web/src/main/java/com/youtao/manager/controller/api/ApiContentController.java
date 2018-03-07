package com.youtao.manager.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.youtao.common.bean.EasyUIResult;
import com.youtao.manager.service.ContentService;

/**
 * @title: ApiContentController
 * @description: 内容对外接口
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月7日 上午11:43:05
 */
@Controller
@RequestMapping("/api/content")
public class ApiContentController {
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 内容查询
	 * @param categoryId 类目id
	 * @param page 页数
	 * @param rows 页大小
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EasyUIResult> queryContentList(Long categoryId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer rows) {
		try {
			EasyUIResult easyUIResult = this.contentService.queryContentList(categoryId, page, rows);
			return ResponseEntity.ok(easyUIResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
