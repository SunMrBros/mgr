package com.my.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.CustomException;
import com.my.po.AdminInfo;
import com.my.po.OpLogInfo;
import com.my.service.AdminService;
import com.my.service.LogService;
import com.my.util.StringUtil;
import com.my.util.VerifyCodeUtils;
import com.my.util.enums.AdminEnums;
import com.my.util.vo.AjaxResponse;
import com.my.util.vo.PageValues;
import com.my.util.vo.WebVo;

import my.utils.MD5Tools;
import my.utils.web.page.Direct;
import my.utils.web.page.FetchMode;
import my.utils.web.page.Page;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.ParamDirect;
import my.utils.web.page.ParamFetch;
import my.utils.web.page.QueryParams;

@Controller
public class MainController {
	private static Logger logger = Logger.getLogger(MainController.class.getName());

	@Resource
	private AdminService adminService;
	@Autowired
	private LogService log;

	/**
	 * 主页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {

		return "index";
	}

	@RequestMapping("/login")
	public String login(WebVo webValues, HttpSession session, HttpServletRequest request) {
		String loginname = webValues.getLoginname();
		String password = webValues.getPassword();
		String code = webValues.getCode();
		logger.info(loginname);
		logger.info(password);
		logger.info(code);
		String scode = (String) session.getAttribute("verCode");
		logger.info(scode);
		if (StringUtil.isBlank(code)) {
			request.setAttribute("msg", "填写验证码");
			return "login";
		}
		if (!code.equals(scode)) {
			request.setAttribute("msg", "验证码不正确");
			return "login";
		}
		if (StringUtil.isBlank(loginname) || StringUtil.isBlank(password)) {
			return "login";
		}
		AdminInfo admin = adminService.getAdmin(loginname, password);
		if (admin != null) {
			if(admin.getStatus()==AdminEnums.Normal.getStatus()){
				session.setAttribute("admin", admin);
				log.log(session,"子管理员","管理员登录成功:"+admin.getRealName());
				logger.info("登录成功:"+admin.getRealName());
				return "index";
			}else if(admin.getStatus()==AdminEnums.Disable.getStatus()){
				request.setAttribute("msg", "管理员已被"+AdminEnums.Disable.getName());
				return "login";
			}else if(admin.getStatus()==AdminEnums.Delete.getStatus()){
				request.setAttribute("msg", "管理员已被"+AdminEnums.Delete.getName());
				return "login";
			}else{
				request.setAttribute("msg", "管理员状态未知错误");
				return "login";
			}
			
		} else {
			// 账号不存在,跳转登录界面
			request.setAttribute("msg", "账号或密码错误!");
			return "login";
		}
	}

	@RequestMapping("loginout")
	public String loginout(HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping("/getCodeImg")
	public void getCodeImg(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		// 删除以前的
		session.removeAttribute("verCode");
		session.setAttribute("verCode", verifyCode.toLowerCase());
		// 生成图片
		int w = 75, h = 32;
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException("验证码生成出问题");
		}
	}

	/**
	 * 获取管理员列表
	 * @param webValues
	 * @param pageValues
	 * @return
	 */
	@RequestMapping("/getAdmins")
	public ModelAndView getAdmins(@ModelAttribute WebVo webValues, @ModelAttribute PageValues pageValues) {
		ModelAndView mv = new ModelAndView();
		String realName = webValues.getRealName();
		QueryParams queryParams = new QueryParams();
		pageValues.setPageSize(pageValues.getNumPerPage());
		if (!StringUtil.isBlank(realName)) {
			queryParams.getConditions().add(new ParamCondition("realName", realName));
		}
		Page<AdminInfo> adminPage = adminService.getAdminPage(pageValues, queryParams);
		mv.addObject("adminPage", adminPage);
		mv.setViewName("admin/adminList");
		return mv;
	}

	/**
	 * 跳转到修改管理员界面
	 * @param webValues
	 * @return
	 */
	@RequestMapping("/toEditAdmin")
	public ModelAndView toEditAdmin(WebVo webValues) {
		ModelAndView mv = new ModelAndView();
		String id = webValues.getId();
		if (StringUtil.isBlank(id)) {
			throw new CustomException("参数错误");
		} else {
			AdminInfo admin = adminService.getAdminById(Integer.valueOf(id));
			mv.addObject("admin", admin);
			mv.setViewName("admin/editAdmin");
		}
		return mv;
	}

	/**
	 * 更新管理员
	 * @param webValues
	 * @return
	 */
	@RequestMapping("/updateAdmin")
	public @ResponseBody AjaxResponse updateAdmin(WebVo webValues,HttpSession session) {
		
		AjaxResponse res=new AjaxResponse();
		boolean flag=false;
		String id = webValues.getId();
		String loginname=webValues.getLoginname();
		String realName=webValues.getRealName();
		String telephone=webValues.getTelephone();
		String status=webValues.getStatus();
		
		try {
			if (StringUtil.isBlank(id)) {
				throw new CustomException("参数错误");
			}
			AdminInfo admin = adminService.getAdminById(Integer.valueOf(id));
			log.log(session,"管理员模块","更新子管理员:adminId:"+id);
			if (!StringUtil.isBlank(loginname)) {
				admin.setLoginname(loginname);
			}
			if (!StringUtil.isBlank(realName)) {
				admin.setRealName(realName);
			}
			if (!StringUtil.isBlank(telephone)) {
				admin.setTelephone(telephone);
			}
			if (!StringUtil.isBlank(status)) {
				admin.setStatus(Integer.valueOf(status));
			}
			flag=adminService.updateAdmin(admin);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		if (flag) {
			res.setStatusCode("200");
			res.setMessage("修改成功!");
			res.setCallbackType("closeCurrent");
			res.setNavTabId("admin");
		} else {
			res.setStatusCode("300");
			res.setMessage("修改失败!");
		}

		return res;
	}
	@RequestMapping("/deleteAdmin")
	public  @ResponseBody AjaxResponse deleteAdmin(WebVo webValues,HttpSession session){
		AjaxResponse res=new AjaxResponse();
		boolean flag=false;
		if(!StringUtil.isBlank(webValues.getId())){
			flag=adminService.deleteAdmin(webValues.getId());
			log.log(session,"管理员模块","删除管理员Id"+webValues.getId());
		}
		
		if (flag) {
			res.setStatusCode("200");
			res.setMessage("删除成功!");
			res.setNavTabId("admin");
		} else {
			res.setStatusCode("300");
			res.setMessage("删除失败!");
		}
		return res;
	}
	/**
	 * 跳转添加管理员界面
	 * @return
	 */
	@RequestMapping("/toAddAdmin")
	public String toAddAdmin(){
		return "admin/addAdmin";
	}
	
	/**
	 * 跳转添加管理员界面
	 * @return
	 */
	@RequestMapping("/addAdmin")
	public @ResponseBody AjaxResponse addAdmin(AdminInfo admin,HttpSession session){
		AjaxResponse res=new AjaxResponse();
		boolean flag=false;
		flag=adminService.addAdmin(admin);
		log.log(session,"子管理员","添加子管理员:"+admin.getRealName());
		if (flag) {
			res.setStatusCode("200");
			res.setMessage("添加成功!");
			res.setNavTabId("admin");
			res.setCallbackType("closeCurrent");
		} else {
			res.setStatusCode("300");
			res.setMessage("添加失败!");
		}
		return res;
	}
	/**
	 * 判断新增管理员登录名是否存在
	 * @param webValuse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/isExist")
	public @ResponseBody boolean isExist(@ModelAttribute WebVo webValuse){
		boolean flag = false;
		if (!StringUtil.isBlank(webValuse.getLoginname())) {
			flag = adminService.isExist(webValuse.getLoginname());
		}
		return !flag;
	}
	
	/**
	 * 跳转修改密码界面
	 * @return
	 */
	@RequestMapping("/tochangepass")
	public String tochgpass(@ModelAttribute WebVo webValues){
		
		return "changepass";
	}
	
	/**
	 * 判断就密码是否正确
	 * @param webValuse id,oldpassword
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/isOldpasswordright")
	public @ResponseBody boolean isOldpasswordright(@ModelAttribute WebVo webValues) {
		boolean flag = false;
		if (!StringUtil.isBlank(webValues.getOldpassword())&&!StringUtil.isBlank(webValues.getId())) {
			String enpass=MD5Tools.getKeyedDigest(webValues.getOldpassword(), "");
			flag = adminService.isPassright(webValues.getId(), enpass);
			
		}
		return flag;

	}
	
	/**
	 * 跳转修改密码界面
	 * @return
	 */
	@RequestMapping("/changepass")
	public @ResponseBody AjaxResponse changepass(@ModelAttribute WebVo webValues,HttpSession session){
		logger.info("修改密码");
		AjaxResponse res=new AjaxResponse();
		boolean flag = false;
		if (!StringUtil.isBlank(webValues.getPassword())&&!StringUtil.isBlank(webValues.getId())) {
			//加密密码
			String enpassword=MD5Tools.getKeyedDigest(webValues.getPassword(), "");
			flag = adminService.changePass(webValues.getId(), enpassword);
			log.log(session,"","管理员Id:"+webValues.getId()+"修改密码");
		}
		if (flag) {
			res.setStatusCode("200");
			res.setMessage("修改密码成功!");
			res.setCallbackType("closeCurrent");
		} else {
			res.setStatusCode("300");
			res.setMessage("修改密码失败!");
		}
		return res;
	}
	/**
	 * 获取用户后台操作日志
	 * @param webVo
	 * @param pageVo
	 * @return
	 */
	@RequestMapping("/getLogPage")
	public ModelAndView getLogPage(@ModelAttribute WebVo webVo,@ModelAttribute PageValues pageVo){
		ModelAndView mv=new ModelAndView();
		String loginname=webVo.getLoginname();
		QueryParams query=new QueryParams();
		query.getDirects().add(new ParamDirect("opdate",Direct.DESC));
		if(!StringUtil.isBlank(loginname)){
			query.getFetchs().add(new ParamFetch("admin","admin",FetchMode.LEFT_JOIN));
			query.getConditions().add(new ParamCondition("admin.loginname", loginname));
		}
		
		pageVo.setPageSize(pageVo.getNumPerPage());
		Page<OpLogInfo> logPage=log.getLogPage(pageVo,query);
		mv.addObject("logPage",logPage);
		mv.setViewName("log/logList");
		return mv;
	}
}
