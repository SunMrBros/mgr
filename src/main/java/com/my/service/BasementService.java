package com.my.service;

import java.util.List;

import com.my.po.BaseInfo;
import com.my.po.CityInfo;
import com.my.po.TownInfo;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

public interface BasementService {

	public Page<BaseInfo> getBasePage(PageValues pageValues, QueryParams query);

	/**
	 * 保存base信息
	 * @param base
	 */
	public void save(BaseInfo base);

	/**
	 * 获取城市信息
	 * @return
	 */
	public List<CityInfo> getCitys();

	/**
	 * 获取县级信息
	 * @param cityId
	 * @return
	 */
	public List<TownInfo> getTownByCityId(String cityId);

	/**
	 * 获取城市
	 * @param cityId
	 * @return
	 */
	public CityInfo getCityById(String cityId);

	/**
	 * 通过id获取basement基地信息
	 * @param basementId
	 * @return
	 */
	public BaseInfo getBasementById(String basementId);

	/**
	 * 更新基地信息
	 * @param base
	 */
	public void updateBase(BaseInfo base);

	/**
	 *删除基地信息
	 * @param base
	 * @return
	 */
	public boolean delBaseInfo(BaseInfo base);

	/**
	 * 条件查询基地信息
	 * @param query
	 * @return
	 */
	public List<BaseInfo> getBasesByQuery(QueryParams query);

}
