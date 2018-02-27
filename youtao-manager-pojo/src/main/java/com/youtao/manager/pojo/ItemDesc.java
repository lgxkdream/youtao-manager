package com.youtao.manager.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @title: ItemDesc
 * @description: 商品描述实体
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月27日 下午2:44:51
 */
@Table(name = "tb_item_desc")
public class ItemDesc extends BasePojo{
    
    @Id//对应tb_item中的id
    private Long itemId;
    
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
    
}
