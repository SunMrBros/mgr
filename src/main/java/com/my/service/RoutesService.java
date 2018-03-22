package com.my.service;

import com.my.po.RoutesInfo;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

public interface RoutesService {

	public Page<RoutesInfo> getRoutesPage(PageValues pageVo, QueryParams query);

}
