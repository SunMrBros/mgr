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

import com.my.exception.CustomException;
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
	/**
	 * 跳转编辑线路页
	 * @return
	 */
	@RequestMapping("/toEditRoute")
	public ModelAndView toEditRoute(WebVo webVo){
		logger.info("编辑线路");
		ModelAndView mv=new ModelAndView();
		String routeId=webVo.getRouteId();
		if(StringUtil.isBlank(routeId)){
			throw new CustomException("routeId is needed");
		}
		RoutesInfo route=routesService.getRouteById(Integer.valueOf(routeId));
		List<ColumnInfo> columns=columnService.getColumnList();
		mv.setViewName("route/editRoute");
		mv.addObject("columns", columns);
		mv.addObject("route", route);
		return mv;
		
	}
	
	/**
	 * 更新线路图
	 * @param route
	 * @return
	 */
	@RequestMapping("/updateRoute")
	public @ResponseBody AjaxResponse updateRoute(WebVo webVo, RoutesInfo route, MultipartFile file1,
			MultipartFile file2, HttpServletRequest request, HttpSession session) {
		AjaxResponse res = new AjaxResponse();

		try {
			String routeId = webVo.getRouteId();
			String columnId=webVo.getColumnId();
			if (StringUtil.isBlank(routeId)||StringUtil.isBlank(columnId)) {
				res.setMessage("routeId or columnId is needed");
				res.setStatusCode("300");
				return res;
			}
			RoutesInfo oldR = routesService.getRouteById(Integer.valueOf(routeId));

			String pic1 = file1.getOriginalFilename();
			String pic2 = file2.getOriginalFilename();
			if (!StringUtil.isBlank(pic1)) {
				// basePic1图片更新,删除basePic1原有图片
				String basePic1 = oldR.getBasePic1();
				if(!StringUtil.isBlank(basePic1)){
					String name = basePic1.substring(basePic1.lastIndexOf("/"));
					
					String local = session.getServletContext().getRealPath("/route");
					// 删除轮播图图片信息
					File f = new File(local, name);
					if (f.exists()) {
						f.delete();
					}
				}
				
				// 保存新图片
				String filename = file1.getOriginalFilename();
				String nfile = System.currentTimeMillis() + filename.substring(filename.indexOf("."));
				// 保存Eclipse中路径
				String absPath = session.getServletContext().getRealPath("/") + "/route";
				// 数据库中路径
				String path = request.getContextPath() + "/route/" + nfile;
				File nlocal = new File(absPath, nfile);
				
				if (!nlocal.exists()) {
					nlocal.mkdirs();
					try {
						file1.transferTo(nlocal);
						route.setBasePic1(path);
					} catch (IOException e) {
						logger.error("保存轮播图文件异常" + e.toString());
						e.printStackTrace();
						res.setStatusCode("300");
						res.setMessage("保存文件出错啦");
					}
				}
			}
			if (!StringUtil.isBlank(pic2)) {
				// basePic1图片更新,删除basePic1原有图片
				String basePic2 = oldR.getBasePic2();
				if(!StringUtil.isBlank(basePic2)){
					String name = basePic2.substring(basePic2.lastIndexOf("/"));
					String local = session.getServletContext().getRealPath("/route");
					// 删除轮播图图片信息
					File f = new File(local, name);
					if (f.exists()) {
						f.delete();
					}
				}
				
				// 保存新图片
				String filename = file1.getOriginalFilename();
				String nfile = System.currentTimeMillis() + filename.substring(filename.indexOf("."));
				// 保存Eclipse中路径
				String absPath = session.getServletContext().getRealPath("/") + "/route";
				// 数据库中路径
				String path = request.getContextPath() + "/route/" + nfile;
				File nlocal = new File(absPath, nfile);
				if (!nlocal.exists()) {
					nlocal.mkdirs();
					try {
						file2.transferTo(nlocal);
						route.setBasePic2(path);
					} catch (IOException e) {
						logger.error("保存轮播图文件异常" + e.toString());
						e.printStackTrace();
						res.setStatusCode("300");
						res.setMessage("保存文件出错啦");
					}
				}
			}
			
			ColumnInfo column=columnService.getColumnById(Integer.valueOf(columnId));
			oldR.setColumn(column);
			oldR.setRouteDesc(route.getRouteDesc());
			oldR.setSortNum(route.getSortNum());
			oldR.setStatus(route.getStatus());
			routesService.updateRoute(oldR);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("更新线路异常");
			res.setStatusCode("300");
			return res;
		}
		res.setMessage("更新完成");
		res.setStatusCode("200");
		res.setCallbackType("closeCurrent");
		res.setNavTabId("routes");
		return res;

	}
}
