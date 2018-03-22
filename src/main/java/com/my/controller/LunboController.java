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
import com.my.po.LunboInfo;
import com.my.service.LunboService;
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
 * 轮播图控制层
 * @author KangZheng
 *
 */
@Controller
@RequestMapping("/lunbo")
public class LunboController {
	private static Logger logger=Logger.getLogger(LunboController.class.getName());
	@Autowired
	private LunboService lunboService;
	
	@RequestMapping("/getLunboPage")
	public ModelAndView getLunboPage(@ModelAttribute PageValues pageValues,@ModelAttribute WebVo webValues){
		ModelAndView mv=new ModelAndView();
		QueryParams query=new QueryParams();
		String title =webValues.getTitle();
		if(!StringUtil.isBlank(title)){
			query.getConditions().add(new ParamCondition("title",title));
		}
		query.getDirects().add(new ParamDirect("sortNum",Direct.ASC));
		Page<LunboInfo> lunboPage=lunboService.getLunboPage(pageValues,query);
		mv.addObject("lunboPage", lunboPage);
		mv.setViewName("lunbo/lunboList");
		return mv;
	}
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping("/toAddLunbo")
	public String toAddLunbo(){
		
		return "lunbo/addLunbo";
	}
	/**
	 * 添加轮播图
	 * @param Lunbo
	 * @return
	 */
	@RequestMapping("/addLunbo")
	public @ResponseBody AjaxResponse addLunbo(LunboInfo lunbo, MultipartFile file,HttpServletRequest request,HttpSession session){
		AjaxResponse res=new AjaxResponse();
		boolean flag=false;
		String filename=file.getOriginalFilename();
		String nfile=System.currentTimeMillis()+filename.substring(filename.indexOf("."));
		//保存Eclipse中路径
		String absPath=session.getServletContext().getRealPath("/")+"/lunbo";
		//数据库中路径
		String path=request.getContextPath()+"/lunbo/"+nfile;
		File file1=new File(absPath,nfile);
		if(!file1.exists()){
			file1.mkdirs();
		}
		try {
			file.transferTo(file1);
			
			lunbo.setPicurl(path);
			flag=lunboService.save(lunbo);
		} catch (IOException e) {
			logger.error("保存轮播图文件异常"+e.toString());
			e.printStackTrace();
			res.setStatusCode("300");
			res.setMessage("保存文件出错啦");
		}
		if(flag){
			res.setStatusCode("200");
			res.setMessage("操作成功");
			res.setCallbackType("closeCurrent");
			res.setNavTabId("lunbo");
		}else{
			res.setStatusCode("300");
			res.setMessage("操作失败");
		}
		
		return res;
		
	}
	/**
	 * 删除轮播图
	 * @param column
	 * @return
	 */
	@RequestMapping("/delLunbo")
	public @ResponseBody AjaxResponse delLunbo(WebVo webVo,HttpServletRequest request,HttpSession session){
		AjaxResponse res=new AjaxResponse();
		
		try {
			String columnId=webVo.getLunboId();
			LunboInfo lunbo=lunboService.getLunboById(Integer.valueOf(columnId));
			String picurl=lunbo.getPicurl();
			String name=picurl.substring(picurl.lastIndexOf("/"));
			if(!StringUtil.isBlank(picurl)){
				String local=session.getServletContext().getRealPath("/lunbo");
				//删除轮播图图片信息
				File f=new File(local,name);
				if(f.exists()){
					f.delete();
				}
			}
			
			//数据库删除数据
			lunboService.delLunboInfo(lunbo);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("删除轮播图异常");
			res.setStatusCode("300");
			return res;
		}
		res.setMessage("删除轮播图完成");
		res.setStatusCode("200");
		return res;
		
	}
	
	@RequestMapping("/toEditLunbo")
	public ModelAndView toEditLunbo(WebVo webVo){
		ModelAndView mv=new ModelAndView();
		String lunboId=webVo.getLunboId();
		if(StringUtil.isBlank(lunboId)){
			throw new CustomException("lunboId is needed");
		}
		LunboInfo lunbo=lunboService.getLunboById(Integer.valueOf(lunboId));
		mv.setViewName("lunbo/editLunbo");
		mv.addObject("lunbo", lunbo);
		return mv;
		
	}
	/**
	 * 更新轮播图
	 * @param lunbo
	 * @return
	 */
	@RequestMapping("/updateLunbo")
	public @ResponseBody AjaxResponse updateLunbo(WebVo webVo,LunboInfo co,HttpServletRequest request,HttpSession session){
		AjaxResponse res=new AjaxResponse();
		
		try {
			String lunboId=webVo.getLunboId();
			if(StringUtil.isBlank(lunboId)){
				res.setMessage("lunboId is needed");
				res.setStatusCode("300");
				return res;
			}
			LunboInfo lunbo=lunboService.getLunboById(Integer.valueOf(lunboId));
			String picurl=lunbo.getPicurl();
			String name=picurl.substring(picurl.lastIndexOf("/"));
			if(!StringUtil.isBlank(picurl)){
				String local=session.getServletContext().getRealPath("/lunbo");
				//删除轮播图图片信息
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
				String absPath=session.getServletContext().getRealPath("/")+"/lunbo";
				//数据库中路径
				String path=request.getContextPath()+"/lunbo/"+nfile;
				File local=new File(absPath,nfile);
				if(!local.exists()){
					local.mkdirs();
				}
				try {
					file.transferTo(local);
					lunbo.setPicurl(path);
				} catch (IOException e) {
					logger.error("保存轮播图文件异常"+e.toString());
					e.printStackTrace();
					res.setStatusCode("300");
					res.setMessage("保存文件出错啦");
				}
			}
			
			//更新数据
			lunbo.setTitle(co.getTitle());
			lunbo.setSortNum(co.getSortNum());
			lunbo.setStatus(co.getStatus());
			
			lunboService.updateLunboInfo(lunbo);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("更新轮播图异常");
			res.setStatusCode("300");
			return res;
		}
		res.setMessage("更新轮播图完成");
		res.setStatusCode("200");
		res.setCallbackType("closeCurrent");
		res.setNavTabId("lunbo");
		return res;
		
	}
	
}
