package com.my.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.po.ColumnInfo;
import com.my.po.RoutesInfo;
import com.my.service.ColumnService;
import com.my.service.RoutesService;
import com.my.util.StringUtil;
import com.my.util.vo.AjaxResponse;
import com.my.util.vo.PageValues;
import com.my.util.vo.WebVo;

import my.utils.web.page.Direct;
import my.utils.web.page.FetchMode;
import my.utils.web.page.Page;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.ParamDirect;
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

	private static Logger logger=Logger.getLogger(RoutesController.class.getName());
	@Autowired
	private RoutesService routesService;
	@Autowired
	private ColumnService columnService;
	
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
		query.getDirects().add(new ParamDirect("sortNum",Direct.DESC));
		Page<RoutesInfo> routesPage=routesService.getRoutesPage(pageVo,query);
		mv.setViewName("route/routesList");
		mv.addObject("routesPage", routesPage);
		return mv;
	}
	
	/**
	 * 跳转添加线路页面
	 */
	@RequestMapping("/toAddRoute")
	public ModelAndView toAddRoute(){
		ModelAndView mv=new ModelAndView();
		//初始化栏目数据
		List<ColumnInfo> columns=columnService.getColumnList();
		mv.addObject("columns", columns);
		mv.setViewName("route/addRoute");
		return mv;
		
	}
	/**
	 * 添加线路
	 */
	@RequestMapping("/addRoute")
	public @ResponseBody AjaxResponse addRoute(WebVo webVo,RoutesInfo route,HttpSession session,HttpServletRequest request){
		logger.info("addRoute");
		AjaxResponse res=new AjaxResponse();
		String columnId=webVo.getColumnId();
		
		MultipartFile[] files= webVo.getFiles();
		for(int i=0;i<files.length;i++){
			String filename=files[i].getOriginalFilename();
			if(StringUtil.isBlank(filename)){
				//文件没有时 取下一个文件
				continue;
			}
			String nfile=System.currentTimeMillis()+filename.substring(filename.indexOf("."));
			//保存Eclipse中路径
			String absPath=session.getServletContext().getRealPath("/")+"/route";
			//数据库中路径
			String path=request.getContextPath()+"/route/"+nfile;
			File file1=new File(absPath,nfile);
			if(!file1.exists()){
				file1.mkdirs();
			}
			try {
				files[i].transferTo(file1);
				if(StringUtil.isBlank(route.getBasePic1())){
					route.setBasePic1(path);
				}else{
					route.setBasePic2(path);
				}
				
				
			} catch (IOException e) {
				logger.error("保存线路图文件异常"+e.toString());
				e.printStackTrace();
				res.setStatusCode("300");
				res.setMessage("保存文件出错啦");
			}
		}
		ColumnInfo column=columnService.getColumnById(Integer.valueOf(columnId));
		route.setColumn(column);
		boolean flag=routesService.save(route);
		if(flag){
			res.setStatusCode("200");
			res.setMessage("操作成功");
			res.setCallbackType("closeCurrent");
			res.setNavTabId("routes");
		}else{
			res.setStatusCode("300");
			res.setMessage("操作失败");
		}
		return res;
		
	}
}
