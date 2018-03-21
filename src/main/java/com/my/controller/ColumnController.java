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
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("删除栏目异常");
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
		mv.setViewName("editColumn");
		mv.addObject("column", column);
		return mv;
		
	}
	/**
	 * 删除栏目
	 * @param column
	 * @return
	 */
	@RequestMapping("/editColumn")
	public @ResponseBody AjaxResponse editColumn(WebVo webVo,HttpServletRequest request,HttpSession session){
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
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("删除栏目异常");
			res.setStatusCode("300");
			return res;
		}
		res.setMessage("删除栏目完成");
		res.setStatusCode("200");
		return res;
		
	}
	
}
