package com.youtao.manager.service;

import java.util.List;

import com.youtao.manager.pojo.ItemCat;

/**
 * 
 * @title: ItemCatService
 * @description: 商品类目Service
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月25日 下午12:30:41
 */
public interface ItemCatService {

	List<ItemCat> queryItemCatListByParentId(Long parentId);

}
