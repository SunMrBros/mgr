package com.my.service;

import java.util.List;

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

	/**
	 * 保存栏目
	 * @param column
	 * @return
	 */
	public boolean save(ColumnInfo column);

	/**
	 * columnId 获取栏目
	 * @param valueOf
	 * @return
	 */
	public ColumnInfo getColumnById(Integer columnId);

	/**
	 * 删除栏目信息
	 * @param columnId
	 */
	public void delColumnInfo(ColumnInfo column);

	/**
	 * 更新栏目信息
	 * @param column
	 */
	public void updateColumnInfo(ColumnInfo column);

	/**
	 * 获取所有column
	 * @return
	 */
	public List<ColumnInfo> getColumnList();

}
