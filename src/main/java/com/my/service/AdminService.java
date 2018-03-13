package com.my.service;

import com.my.po.AdminInfo;
import com.my.util.vo.PageValues;

import my.utils.web.page.Page;
import my.utils.web.page.QueryParams;

/**
 * 管理员service
 * @author KangZheng
 *
 */
public interface AdminService {

	public AdminInfo getAdmin(String loginname, String password);

	public Page<AdminInfo> getAdminPage(PageValues pageValues,QueryParams queryParams);

	public AdminInfo getAdminById(Integer id);

	/**
	 * 更新管理员,成功返回true,失败返回false
	 * @param admin
	 * @return
	 */
	public boolean updateAdmin(AdminInfo admin);

	/**
	 * 存在返回true
	 * @param loginname
	 * @return
	 */
	public boolean isExist(String loginname);

	/**
	 * 判断管理员旧密码是否正确,正确返回true,错误返回false
	 * @param id
	 * @param loginname
	 * @return 
	 */
	public boolean isPassright(String id, String loginname);

	/**
	 * 添加管理员
	 * @param admin
	 * @return
	 */
	public boolean addAdmin(AdminInfo admin);

	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean changePass(String id, String password);

	/**
	 * 删除该管理员
	 * @param id
	 * @return
	 */
	public boolean deleteAdmin(String id);

}
