package com.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.dao.LunboDao;
import com.my.po.LunboInfo;
import com.my.service.LunboService;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

@Service
@Transactional
public class LunboServiceImpl implements LunboService {

	@Autowired
	private LunboDao LunboDao;

	@Override
	public Page<LunboInfo> getLunboPage(PageValues pageValues, QueryParams query) {
		return LunboDao.getPageList(pageValues.getPageNum() == 0 ? 1 : pageValues.getPageNum(),
				pageValues.getNumPerPage()==0?10:pageValues.getNumPerPage(), query);
	}

	@Override
	public boolean save(LunboInfo Lunbo) {
		boolean flag = false;
		try {
			LunboDao.save(Lunbo);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public LunboInfo getLunboById(Integer LunboId) {
		return LunboDao.getByID(LunboId);
	}

	@Override
	public void delLunboInfo(LunboInfo Lunbo) {
		LunboDao.delete(Lunbo);
	}

	@Override
	public void updateLunboInfo(LunboInfo Lunbo) {
		LunboDao.update(Lunbo);
	}

}
