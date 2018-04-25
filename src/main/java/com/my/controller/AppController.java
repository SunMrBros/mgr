package com.my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.exception.CustomException;
import com.my.po.BaseInfo;
import com.my.po.LunboInfo;
import com.my.service.BasementService;
import com.my.service.LunboService;
import com.my.util.StringUtil;
import com.my.util.vo.BasementVo;

import my.utils.web.page.Direct;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.ParamDirect;
import my.utils.web.page.QueryParams;
/**
 * 主app控制器
 * @author KangZheng
 *
 */
@Controller
@RequestMapping("/app")
public class AppController {

	@Autowired
	private LunboService lunboService;
	@Autowired
	private BasementService baseService;
	
	@RequestMapping("/index")
	public String toIndex(Model model){
		//轮播图
		List<LunboInfo> lunbos=lunboService.getLunboList();
		model.addAttribute("lunbos", lunbos);
		//初始化推荐基地
		QueryParams query=new QueryParams();
		query.getConditions().add(new ParamCondition("recommend",1));//推荐
		query.getConditions().add(new ParamCondition("status",1));//有效
		query.getDirects().add(new ParamDirect("sortNum",Direct.ASC));//升序
		List<BaseInfo> recBases=baseService.getBasesByQuery(query);
		model.addAttribute("recBases", recBases);
		
		return "/front/index";
	}
	@RequestMapping("toBaseDetail")
	public String toBaseDetail(BasementVo baseVo,Model model){
		String baseId=baseVo.getBaseId();
		if(StringUtil.isBlank(baseId)){
			throw new CustomException("参数错误,没有基地id");
		}
		BaseInfo base=baseService.getBasementById(baseId);
		model.addAttribute("base", base);
		return "/front/baseDetail";
	}
}
