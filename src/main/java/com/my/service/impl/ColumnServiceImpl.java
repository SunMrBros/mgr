package com.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.dao.ColumnDao;
import com.my.po.ColumnInfo;
import com.my.service.ColumnService;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

@Service
@Transactional
public class ColumnServiceImpl implements ColumnService {

	@Autowired
	private ColumnDao columnDao;
	@Override
	public Page<ColumnInfo> getColumnPage(PageValues pageValues,QueryParams query) {
		return columnDao.getPageList(pageValues.getPageNum(), pageValues.getPageSize(), query);
	}

}
