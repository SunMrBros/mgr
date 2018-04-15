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

}
