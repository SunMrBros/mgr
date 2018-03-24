package com.my.service.impl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.dao.LogDao;
import com.my.po.AdminInfo;
import com.my.po.OpLogInfo;
import com.my.service.LogService;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;
@Service
@Transactional
public class LogServiceImpl implements LogService{

	@Autowired
	private LogDao logDao;
	@Override
	public Page<OpLogInfo> getLogPage(PageValues pageVo, QueryParams query) {
		return logDao.getPageList(pageVo.getPageNum(), pageVo.getPageSize(), query);
	}
	@Override
	public void log(HttpSession session,String model, String string) {
		OpLogInfo l=new OpLogInfo();
		AdminInfo admin=(AdminInfo)session.getAttribute("admin");
		l.setAdmin(admin);
		l.setOpdate(new Date());
		l.setOperator(string);
		l.setModel(model);
		logDao.save(l);
		
	}

}
