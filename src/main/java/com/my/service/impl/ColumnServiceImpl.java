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
	public Page<ColumnInfo> getColumnPage(PageValues pageValues, QueryParams query) {
		return columnDao.getPageList(pageValues.getPageNum() == 0 ? 1 : pageValues.getPageNum(),
				pageValues.getNumPerPage()==0?10:pageValues.getNumPerPage(), query);
	}

	@Override
	public boolean save(ColumnInfo column) {
		boolean flag = false;
		try {
			columnDao.save(column);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ColumnInfo getColumnById(Integer columnId) {
		return columnDao.getByID(columnId);
	}

	@Override
	public void delColumnInfo(ColumnInfo column) {
		columnDao.delete(column);
	}

}
