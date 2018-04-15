package com.my.service;

import javax.servlet.http.HttpSession;

import com.my.po.OpLogInfo;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

public interface LogService {

	public Page<OpLogInfo> getLogPage(PageValues pageVo, QueryParams query);

	/**
	 * 
	 * @param session
	 * @param model 操作模块
	 * @param string 操作内容
	 */
	public void log(HttpSession session,String model ,String string);

}
