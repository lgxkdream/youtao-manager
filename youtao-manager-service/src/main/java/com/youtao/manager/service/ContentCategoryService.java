package com.youtao.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.youtao.manager.pojo.ContentCategory;

/**
 * @title: ContentCategoryService
 * @description: 内容分类Service
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月6日 下午3:16:33
 */
@Service
public class ContentCategoryService extends BaseService<ContentCategory> {

	/**
	 * 内容类目新增
     * @param contentCategory 内容类目
	 */
	public void saveContentCategory(ContentCategory contentCategory) {
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		this.save(contentCategory);
		// 判断该节点的父节点的isParent是否为true，如果为false，修改为true
		ContentCategory parent = this.queryById(contentCategory.getParentId());
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			this.updateSelective(parent);
		}
	}

	/**
	 * 内容类目删除
	 * @param id 类目id
	 * @param parentId 父类目id
	 */
	public void deleteContentCategory(Long id, Long parentId) {
		List<Object> ids = new ArrayList<Object>();
		// 防止删除根目录
		if (0 != parentId) {
			ids.add(id);
		}
		findAllSubContentCategory(ids, id);
		this.deleteByIds(ContentCategory.class, "id", ids);
		// 判断该节点是否还存在其他兄弟节点，如果不存在，修改父节点的idParent为false
		ContentCategory record = new ContentCategory();
		record.setParentId(parentId);
		List<ContentCategory> list = this.queryList(record);
		if (CollectionUtils.isEmpty(list)) {
			ContentCategory parent = new ContentCategory();
			parent.setId(parentId);
			parent.setIsParent(false);
			this.updateSelective(parent);
		}
	}
	
	/**
	 * 递归获取子类目id
	 * @param ids 类目id集合
	 * @param pid 父类目id
	 */
	private void findAllSubContentCategory(List<Object> ids, Long pid) {
		ContentCategory record = new ContentCategory();
		record.setParentId(pid);
		List<ContentCategory> list = this.queryList(record);
		if (CollectionUtils.isNotEmpty(list)) {
			for (ContentCategory contentCategory : list) {
				ids.add(contentCategory.getId());
				if (contentCategory.getIsParent()) {
					findAllSubContentCategory(ids, contentCategory.getId());
				}
			}
		}
	}
	
}
