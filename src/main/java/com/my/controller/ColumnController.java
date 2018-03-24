package com.my.controller;

import java.io.File;
import java.io.IOException;

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
import com.my.service.ColumnService;
import com.my.service.LogService;
import com.my.util.StringUtil;
import com.my.util.vo.AjaxResponse;
import com.my.util.vo.PageValues;
import com.my.util.vo.WebVo;

import my.utils.web.page.Direct;
import my.utils.web.page.Page;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.ParamDirect;
import my.utils.web.page.QueryParams;

/**
 * 栏目管理控制层
 * @author KangZheng
 *
 */
@Controller
@RequestMapping("/column")
public class ColumnController {
	private static Logger logger=Logger.getLogger(ColumnController.class.getName());
	@Autowired
	private ColumnService columnService;
	@Autowired
	private LogService log;
	
	@RequestMapping("/getColumnPage")
	public ModelAndView getColumnPage(@ModelAttribute PageValues pageValues,@ModelAttribute WebVo webValues){
		ModelAndView mv=new ModelAndView();
		QueryParams query=new QueryParams();
		String columnName =webValues.getColumnName();
		if(!StringUtil.isBlank(columnName)){
			query.getConditions().add(new ParamCondition("columnName",columnName));
		}
		query.getDirects().add(new ParamDirect("sortNum",Direct.ASC));
		Page<ColumnInfo> columnPage=columnService.getColumnPage(pageValues,query);
		mv.addObject("columnPage", columnPage);
		mv.setViewName("column/columnList");
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
	public @ResponseBody AjaxResponse addColumn(ColumnInfo column, MultipartFile file1,HttpServletRequest request,HttpSession session){
		AjaxResponse res=new AjaxResponse();
		boolean flag=false;
		String filename=file1.getOriginalFilename();
		String nfile=System.currentTimeMillis()+filename.substring(filename.indexOf("."));
		//保存Eclipse中路径
		String absPath=session.getServletContext().getRealPath("/")+"/column";
		//数据库中路径
		String path=request.getContextPath()+"/column/"+nfile;
		File file=new File(absPath,nfile);
		if(!file.exists()){
			file.mkdirs();
		}
		try {
			file1.transferTo(file);
			
			column.setColumnPicUrl(path);
			flag=columnService.save(column);
			log.log(session,"旅游管理","添加了栏目:"+column.getColumnName());
		} catch (IOException e) {
			logger.error("保存栏目文件异常"+e.toString());
			e.printStackTrace();
			res.setStatusCode("300");
			res.setMessage("保存文件出错啦");
		}
		if(flag){
			res.setStatusCode("200");
			res.setMessage("操作成功");
			res.setCallbackType("closeCurrent");
			res.setNavTabId("column");
		}else{
			res.setStatusCode("300");
			res.setMessage("操作失败");
		}
		
		return res;
		
	}
	/**
	 * 删除栏目
	 * @param column
	 * @return
	 */
	@RequestMapping("/delColumn")
	public @ResponseBody AjaxResponse delColumn(WebVo webVo,HttpServletRequest request,HttpSession session){
		AjaxResponse res=new AjaxResponse();
		
		try {
			String columnId=webVo.getColumnId();
			ColumnInfo column=columnService.getColumnById(Integer.valueOf(columnId));
			String picurl=column.getColumnPicUrl();
			String name=picurl.substring(picurl.lastIndexOf("/"));
			if(!StringUtil.isBlank(picurl)){
				String local=session.getServletContext().getRealPath("/column");
				//删除栏目图片信息
				File f=new File(local,name);
				if(f.exists()){
					f.delete();
				}
			}
			
			//数据库删除数据
			columnService.delColumnInfo(column);
			log.log(session,"旅游管理","删除栏目:"+column.getColumnName());
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("删除栏目异常,确认没有线路使用该栏目?");
			res.setStatusCode("300");
			return res;
		}
		res.setMessage("删除栏目完成");
		res.setStatusCode("200");
		return res;
		
	}
	
	@RequestMapping("/toEditColumn")
	public ModelAndView toEditColumn(WebVo webVo){
		ModelAndView mv=new ModelAndView();
		String columnId=webVo.getColumnId();
		if(StringUtil.isBlank(columnId)){
			throw new CustomException("columnId is needed");
		}
		ColumnInfo column=columnService.getColumnById(Integer.valueOf(columnId));
		mv.setViewName("column/editColumn");
		mv.addObject("column", column);
		return mv;
		
	}
	/**
	 * 更新栏目
	 * @param column
	 * @return
	 */
	@RequestMapping("/updateColumn")
	public @ResponseBody AjaxResponse updateColumn(WebVo webVo,ColumnInfo co,HttpServletRequest request,HttpSession session){
		AjaxResponse res=new AjaxResponse();
		
		try {
			String columnId=webVo.getColumnId();
			if(StringUtil.isBlank(columnId)){
				res.setMessage("columnId is needed");
				res.setStatusCode("300");
				return res;
			}
			ColumnInfo column=columnService.getColumnById(Integer.valueOf(columnId));
			String picurl=column.getColumnPicUrl();
			String name=picurl.substring(picurl.lastIndexOf("/"));
			if(!StringUtil.isBlank(picurl)){
				String local=session.getServletContext().getRealPath("/column");
				//删除栏目图片信息
				File f=new File(local,name);
				if(f.exists()){
					f.delete();
				}
			}
			//保存新图片
			MultipartFile file=webVo.getFile();
			String filename=file.getOriginalFilename();
			if(!StringUtil.isBlank(filename)){
				String nfile=System.currentTimeMillis()+filename.substring(filename.indexOf("."));
				//保存Eclipse中路径
				String absPath=session.getServletContext().getRealPath("/")+"/column";
				//数据库中路径
				String path=request.getContextPath()+"/column/"+nfile;
				File local=new File(absPath,nfile);
				if(!local.exists()){
					local.mkdirs();
				}
				try {
					file.transferTo(local);
					column.setColumnPicUrl(path);
				} catch (IOException e) {
					logger.error("保存栏目文件异常"+e.toString());
					e.printStackTrace();
					res.setStatusCode("300");
					res.setMessage("保存文件出错啦");
				}
			}
			
			//更新数据
			column.setColumnName(co.getColumnName());
			column.setSortNum(co.getSortNum());
			column.setStatus(co.getStatus());
			
			columnService.updateColumnInfo(column);
			log.log(session,"旅游管理","更新栏目信息:"+column.getColumnName());
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("更新栏目异常");
			res.setStatusCode("300");
			return res;
		}
		res.setMessage("更新栏目完成");
		res.setStatusCode("200");
		res.setCallbackType("closeCurrent");
		res.setNavTabId("column");
		return res;
		
	}
	
}
