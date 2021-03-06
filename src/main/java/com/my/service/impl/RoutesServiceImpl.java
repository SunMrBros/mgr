package com.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.dao.RoutesDao;
import com.my.po.RoutesInfo;
import com.my.service.RoutesService;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;
@Service
@Transactional
public class RoutesServiceImpl implements RoutesService {
	@Autowired
	private RoutesDao routeDao;

	@Override
	public Page<RoutesInfo> getRoutesPage(PageValues pageVo, QueryParams query) {
		return routeDao.getPageList(pageVo.getPageNum()==0?1:pageVo.getPageNum(), pageVo.getPageSize(), query);
	}

	@Override
	public boolean save(RoutesInfo route) {
		boolean flag=false;
		try {
			routeDao.save(route);
			flag= true;
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

	@Override
	public RoutesInfo getRouteById(Integer routeId) {
		return routeDao.getByID(routeId);
	}

	@Override
	public void updateRoute(RoutesInfo route) {
		routeDao.update(route);
		
	}
	
}
