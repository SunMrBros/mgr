package com.my.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.my.po.AdminInfo;

/**
 * 后台管理员 登录拦截
 * 
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	private static Logger logger = Logger.getLogger(LoginInterceptor.class.getName());

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)

			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		String url = request.getRequestURI();
		// 判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
		if (url.indexOf("login.action") > 0 || url.indexOf("getCodeImg.action") > 0) {
			return true;
		}

		HttpSession session = request.getSession();
		AdminInfo admin = (AdminInfo) session.getAttribute("admin");
		if (admin == null) {

			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			logger.info("url访问被拦截:" + url);
			// return false表示拦截，不向下执行
			// return true表示放行
			return false;
		} else {
			return true;
		}
	}

}
