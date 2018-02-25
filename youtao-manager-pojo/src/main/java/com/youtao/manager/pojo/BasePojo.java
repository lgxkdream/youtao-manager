package com.youtao.manager.pojo;

import java.util.Date;

/**
 * 
 * @title: BasePojo
 * @description: 基础实体
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年2月25日 下午12:28:26
 */
public abstract class BasePojo {

	private Date created;
	private Date updated;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
