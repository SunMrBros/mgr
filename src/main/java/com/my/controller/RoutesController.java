package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.po.RoutesInfo;
import com.my.service.RoutesService;
import com.my.util.StringUtil;
import com.my.util.vo.PageValues;
import com.my.util.vo.WebVo;

import my.utils.web.page.FetchMode;
import my.utils.web.page.Page;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.ParamFetch;
import my.utils.web.page.QueryParams;

/**
 * 线路控制器
 * @author KangZheng
 *
 */
@Controller
@RequestMapping("/route")
public class RoutesController {

	@Autowired
	private RoutesService routesService;
	
	@RequestMapping("/getRoutesPage")
	public ModelAndView getRoutesPage(@ModelAttribute WebVo webVo,PageValues pageVo){
		ModelAndView mv=new ModelAndView();
		String title=webVo.getTitle();
		
		pageVo.setPageSize(pageVo.getNumPerPage());//整合框架
		QueryParams query =new QueryParams();//准查询条件
		if(!StringUtil.isBlank(title)){
			query.getConditions().add(new ParamCondition("title",title));
		}
		query.getFetchs().add(new ParamFetch("column","column",FetchMode.LEFT_JOIN));//column懒加载,这里要fetch
		Page<RoutesInfo> routesPage=routesService.getRoutesPage(pageVo,query);
		mv.setViewName("route/routesList");
		mv.addObject("routesPage", routesPage);
		return mv;
	}
}
