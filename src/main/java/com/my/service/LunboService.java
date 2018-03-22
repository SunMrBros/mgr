package com.my.service;

import com.my.po.LunboInfo;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

public interface LunboService {

	/**
	 * 条件查询栏目分页信息
	 * @param query
	 * @return
	 */
	public Page<LunboInfo> getLunboPage(PageValues pageValues ,QueryParams query);

	/**
	 * 保存栏目
	 * @param Lunbo
	 * @return
	 */
	public boolean save(LunboInfo Lunbo);

	/**
	 * LunboId 获取栏目
	 * @param valueOf
	 * @return
	 */
	public LunboInfo getLunboById(Integer LunboId);

	/**
	 * 删除栏目信息
	 * @param LunboId
	 */
	public void delLunboInfo(LunboInfo Lunbo);

	/**
	 * 更新栏目信息
	 * @param Lunbo
	 */
	public void updateLunboInfo(LunboInfo Lunbo);

}
