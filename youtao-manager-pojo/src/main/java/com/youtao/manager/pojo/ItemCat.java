package com.youtao.manager.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @title: ItemCat
 * @description: 商品类目实体
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月25日 下午12:28:45
 */
@Table(name = "tb_item_cat")
public class ItemCat extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    /**
     * 支持EasyUI的tree结构
     *
     * @return
     */
    public String getText() {
        return name;
    }

    /**
     * 支持EasyUI的tree结构 closed:父节点 open:叶子节点
     *
     * @return
     */
    public String getState() {
        return this.getIsParent() ? "closed" : "open";
    }

}
