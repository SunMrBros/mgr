package com.my.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.dao.BaseDao;
import com.my.dao.CityDao;
import com.my.dao.TownDao;
import com.my.po.BaseInfo;
import com.my.po.CityInfo;
import com.my.po.TownInfo;
import com.my.service.BasementService;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.QueryParams;

@Service
@Transactional
public class BasementServiceImpl implements BasementService {

	@Autowired
	private BaseDao baseDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private TownDao townDao;
	@Override
	public Page<BaseInfo> getBasePage(PageValues pageValues, QueryParams query) {
		return baseDao.getPageList(pageValues.getPageNum(), pageValues.getPageSize(), query);
	}
	@Override
	public void save(BaseInfo base) {
		baseDao.save(base);
		
	}

	@Override
	public List<CityInfo> getCitys() {
		return cityDao.getListAll();
	}
	@Override
	public List<TownInfo> getTownByCityId(String cityId) {
		QueryParams q=new QueryParams();
		q.getConditions().add(new ParamCondition("city.id",Integer.valueOf(cityId)));
		
		return townDao.getListByQueryParams(q);
	}
	@Override
	public CityInfo getCityById(String cityId) {
		return cityDao.getByID(Integer.valueOf(cityId));
	}
	@Override
	public BaseInfo getBasementById(String basementId) {
		return baseDao.getByID(Integer.valueOf(basementId));
	}
	@Override
	public void updateBase(BaseInfo base) {
		baseDao.update(base);
		
	}
	@Override
	public boolean delBaseInfo(BaseInfo base) {
		boolean flag=false;
		try {
			baseDao.delete(base);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	@Override
	public List<BaseInfo> getBasesByQuery(QueryParams query) {
		return baseDao.getListByQueryParams(query);
	}

}
