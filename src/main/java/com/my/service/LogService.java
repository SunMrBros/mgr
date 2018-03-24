package com.my.service;

import javax.servlet.http.HttpSession;

import com.my.po.OpLogInfo;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

public interface LogService {

	public Page<OpLogInfo> getLogPage(PageValues pageVo, QueryParams query);

	public void log(HttpSession session,String model ,String string);

}
