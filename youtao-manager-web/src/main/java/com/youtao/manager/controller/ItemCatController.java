package com.youtao.manager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.youtao.manager.pojo.ItemCat;
import com.youtao.manager.service.ItemCatService;

/**
 * @title: ItemCatController
 * @description: 商品类目Controller
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月25日 下午12:34:21
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemCatController.class);

	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 根据父节点id查询类目列表
	 * @param parentId 父节点id
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> queryItemCatListByParentId(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		try {
			ItemCat itemCat = new ItemCat();
			itemCat.setParentId(parentId);
			List<ItemCat> list = this.itemCatService.queryList(itemCat);
			if (null == list || list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			LOGGER.error("根据父节点id查询类目列表报错，parentId = " + parentId, e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
     * 根据id查询商品类目
     * 
     * @param id 类目id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ItemCat> queryById(@PathVariable("id") Long id) {
        try {
            ItemCat itemCat = this.itemCatService.queryById(id);
            if (null == itemCat) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemCat);
        } catch (Exception e) {
        	LOGGER.error("根据id查询商品类目报错，id = " + id, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
