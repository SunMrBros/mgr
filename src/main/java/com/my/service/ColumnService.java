package com.my.service;

import com.my.po.ColumnInfo;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

public interface ColumnService {

	/**
	 * 条件查询栏目分页信息
	 * @param query
	 * @return
	 */
	public Page<ColumnInfo> getColumnPage(PageValues pageValues ,QueryParams query);

}
