package com.my.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.dao.AdminDao;
import com.my.po.AdminInfo;
import com.my.service.AdminService;
import com.my.util.enums.AdminEnums;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.QueryParams;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminDao adminDao;
	@Override
	public AdminInfo getAdmin(String loginname, String password) {
		QueryParams query=new QueryParams();
		query.getConditions().add(new ParamCondition("loginname", loginname));
		query.getConditions().add(new ParamCondition("password", password));
		return adminDao.getByQueryParams(query);
	}
	@Override
	public Page<AdminInfo> getAdminPage(PageValues pageValues,QueryParams queryParams) {
		return adminDao.getPageList(pageValues.getPageNum(), pageValues.getPageSize(), queryParams);
	}
	@Override
	public AdminInfo getAdminById(Integer id) {
		return adminDao.getByID(id);
	}
	@Override
	public boolean updateAdmin(AdminInfo admin) {
		boolean flag=false;
		try {
			adminDao.update(admin);
			flag= true;
		} catch (Exception e) {
			flag=false;
		}
		return flag;
		
	}
	@Override
	public boolean isExist(String loginname) {
		AdminInfo admin=adminDao.getByField("loginname", loginname);
		if(admin==null){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean isPassright(String id, String oldpassword) {
		AdminInfo admin = adminDao.getByID(Integer.valueOf(id));
		if (oldpassword.equals(admin.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean addAdmin(AdminInfo admin) {
		boolean flag=false;
		try {
			adminDao.save(admin);
			flag= true;
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
	@Override
	public boolean changePass(String id, String password) {
		boolean flag=false;
		try {
			AdminInfo admin=adminDao.getByID(Integer.valueOf(id));
			admin.setPassword(password);
			adminDao.update(admin);
			flag= true;
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
	@Override
	public boolean deleteAdmin(String id) {
		boolean flag=false;
		try {
			AdminInfo admin=adminDao.getByID(Integer.valueOf(id));
			admin.setStatus(AdminEnums.Delete.getStatus());
			adminDao.update(admin);
			flag= true;
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}

}
