package com.youtao.manager.service;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.youtao.common.bean.EasyUIResult;
import com.youtao.manager.pojo.Content;

/**
 * @title: ContentService
 * @description: 内容Service
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月6日 下午3:15:40
 */
@Service
public class ContentService extends BaseService<Content> {
	
	/**
	 * 内容查询
	 * @param categoryId 类目id
	 * @param page 页数
	 * @param rows 页大小
	 * @return
	 */
	public EasyUIResult queryContentList(Long categoryId, Integer page, Integer rows) {
		PageInfo<Content> pageInfo = this.querySortPages(page, rows, "updated DESC", "categoryId", categoryId, Content.class);
		return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
	}

}
