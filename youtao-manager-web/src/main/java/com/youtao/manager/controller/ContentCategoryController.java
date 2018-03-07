package com.youtao.manager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.youtao.manager.pojo.ContentCategory;
import com.youtao.manager.service.ContentCategoryService;

/**
 * @title: ContentCategoryController
 * @description: 
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月6日 下午3:23:15
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentCategoryController.class);
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 根据父节点id内容类目列表
	 * @param parentId 父节点id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ContentCategory>> queryContentCategoryListByParentId(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		try {
			ContentCategory contentCategory = new ContentCategory();
			contentCategory.setParentId(parentId);
			List<ContentCategory> list = this.contentCategoryService.queryList(contentCategory);
			if (null == list || list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			LOGGER.error("根据父节点id内容类目列表报错，parentId = " + parentId, e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 内容类目新增
	 * @param contentCategory 内容类目
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ContentCategory> saveContentCategory(ContentCategory contentCategory) {
		try {
			this.contentCategoryService.saveContentCategory(contentCategory);
			return ResponseEntity.status(HttpStatus.CREATED).body(contentCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 内容类目重命名
	 * @param id 类目id
	 * @param name 名称
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> renameContentCategory(Long id, String name) {
		try {
			ContentCategory record = new ContentCategory();
			record.setId(id);
			record.setName(name);
			this.contentCategoryService.updateSelective(record);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	/**
	 * 内容类目删除
	 * @param id 类目id
	 * @param parentId 父类目id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContentCategory(Long id, Long parentId) {
		try {
			this.contentCategoryService.deleteContentCategory(id, parentId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
