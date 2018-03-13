package com.my.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.po.ColumnInfo;
import com.my.service.ColumnService;
import com.my.util.StringUtil;
import com.my.util.vo.AjaxResponse;
import com.my.util.vo.PageValues;
import com.my.util.vo.WebVo;

import my.utils.web.page.Page;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.QueryParams;

/**
 * 栏目管理控制层
 * @author KangZheng
 *
 */
@Controller
@RequestMapping("/column")
public class ColumnController {

	@Autowired
	private ColumnService columnService;
	
	@RequestMapping("/getColumnPage")
	public ModelAndView getColumnPage(PageValues pageValues,WebVo webValues){
		ModelAndView mv=new ModelAndView();
		QueryParams query=new QueryParams();
		String title =webValues.getTitle();
		if(!StringUtil.isBlank(title)){
			query.getConditions().add(new ParamCondition("title",title));
		}
		Page<ColumnInfo> columnPage=columnService.getColumnPage(pageValues,query);
		mv.addObject("columnPage", columnPage);
		mv.setViewName("columnList");
		return mv;
	}
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping("/toAddColumn")
	public String toAddColumn(){
		
		return "addColumn";
	}
	/**
	 * 添加栏目
	 * @param column
	 * @return
	 */
	@RequestMapping("/addColumn")
	public AjaxResponse addColumn(ColumnInfo column, MultipartFile file1,HttpServletRequest request){
		AjaxResponse res=new AjaxResponse();
		System.out.println(request.getContextPath());
		res.setStatusCode("200");
		res.setMessage("操作成功");
		return res;
		
	}
	@RequestMapping("/upload")
	public AjaxResponse upload( MultipartFile file){
		AjaxResponse ajaxR=new AjaxResponse();
		
		return ajaxR;
		
	}
}
