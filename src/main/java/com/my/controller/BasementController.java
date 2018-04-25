package com.my.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.po.AdminInfo;
import com.my.po.BaseInfo;
import com.my.po.CityInfo;
import com.my.po.TownInfo;
import com.my.service.BasementService;
import com.my.service.LogService;
import com.my.util.FileUtil;
import com.my.util.StringUtil;
import com.my.util.enums.ProvinceEnum;
import com.my.util.vo.AjaxResponse;
import com.my.util.vo.BasementVo;
import com.my.util.vo.CityVo;
import com.my.util.vo.PageValues;
import com.my.util.vo.TownVo;
import com.my.util.vo.WebVo;

import my.utils.web.page.Compare;
import my.utils.web.page.Page;
import my.utils.web.page.ParamCondition;
import my.utils.web.page.QueryParams;

/**
 * 基地信息控制器
 * 
 * @author KangZheng
 *
 */
@Controller
@RequestMapping("/basement")
public class BasementController {
	private Logger logger = Logger.getLogger(BasementController.class.getName());

	@Autowired
	private LogService log;
	@Autowired
	private BasementService baseService;

	/**
	 * 基地信息列表页面
	 * 
	 * @param pageValues
	 * @param webVo
	 * @return
	 */
	@RequestMapping("/getBasePage")
	public ModelAndView getBasePage(@ModelAttribute PageValues pageValues, @ModelAttribute WebVo webVo) {
		logger.info("");
		ModelAndView mv = new ModelAndView();
		pageValues.setPageNum(pageValues.getNumPerPage());
		QueryParams query = new QueryParams();
		String title = webVo.getTitle();
		String province = webVo.getProvince();
		if (!StringUtil.isBlank(title)) {
			query.getConditions().add(new ParamCondition("title",Compare.LIKE, title));
		}
		if (!StringUtil.isBlank(province)) {
			query.getConditions().add(new ParamCondition("province", province));
		}
		Page<BaseInfo> basePage = baseService.getBasePage(pageValues, query);
		mv.setViewName("basement/basementList");
		mv.addObject("basePage", basePage);
		return mv;
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param webVo
	 * @return
	 */
	@RequestMapping("/toAddBase")
	public ModelAndView toAddBase(@ModelAttribute WebVo webVo) {
		ModelAndView mv = new ModelAndView();
		List<CityVo> citys = new ArrayList<CityVo>();
		List<CityInfo> cityss = baseService.getCitys();
		for (CityInfo c : cityss) {
			CityVo cv = new CityVo();
			cv.setId(c.getId());
			cv.setCityName(c.getCityName());
			if (citys.contains(cv)) {
				continue;
			} else {
				citys.add(cv);
			}
		}

		mv.setViewName("basement/addBasement");
		mv.addObject("citys", citys);
		return mv;
	}

	/**
	 * 通过市级id获取城市信息
	 * 
	 * @param webVo
	 * @return
	 */
	@RequestMapping("/getTowns")
	public @ResponseBody List<TownVo> getTowns(WebVo webVo) {
		String cityId = webVo.getCityId();
		if (StringUtil.isBlank(cityId)) {
			return null;
		}
		List<TownInfo> towns = baseService.getTownByCityId(cityId);
		List<TownVo> ts = new ArrayList<TownVo>(towns.size());
		for (TownInfo t : towns) {
			TownVo tv = new TownVo();
			tv.setId(t.getId());
			tv.setTownName(t.getTownName());
			ts.add(tv);
		}
		return ts;
	}

	/**
	 * 添加基地信息
	 * 
	 * @param basement
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/addBasement")
	public @ResponseBody AjaxResponse addBasement(BasementVo basement, HttpServletRequest request,
			HttpSession session) {
		logger.info("add basementinfo");
		AjaxResponse res = new AjaxResponse();
		String province = basement.getProvince();
		if (StringUtil.isBlank(province)) {
			res.setMessage("province is needed");
			res.setStatusCode("300");
			return res;
		}
		try {
			AdminInfo admin = (AdminInfo) session.getAttribute("admin");

			BaseInfo base = new BaseInfo();
			base.setUpdateTime(new Date());
			base.setAddress(basement.getAddress());
			base.setAdmin(admin);
			String area = null;
			String city = null;
			if (!StringUtil.isBlank(basement.getCityId())) {
				CityInfo c = baseService.getCityById(basement.getCityId());
				city = c.getCityName();
				if (!StringUtil.isBlank(basement.getTownId())) {
					Set<TownInfo> ts = c.getTowns();
					for (TownInfo t : ts) {
						if (t.getId() == Integer.valueOf(basement.getTownId())) {
							area = t.getTownName();
						}
					}
				}

			}

			base.setProvince(province);
			base.setArea(area);
			base.setCity(city);
			base.setLocation(basement.getLocation());
			base.setCloseTime(basement.getCloseTime());
			base.setOpenTime(basement.getOpenTime());
			base.setContent(basement.getContent());

			MultipartFile pic = basement.getPic();
			String picUrl = FileUtil.saveFile(pic, "basementimg", request);
			base.setPicUrl(picUrl);
			MultipartFile video = basement.getVideo();
			String videoUrl = FileUtil.saveFile(video, "video", request);
			base.setVideoUrl(videoUrl);
			MultipartFile audio = basement.getAudio();
			String audioUrl = FileUtil.saveFile(audio, "audio", request);
			base.setAudioUrl(audioUrl);

			base.setRecommend(basement.getRecommend());
			base.setSortNum(basement.getSortNum());
			base.setStatus(basement.getStatus());
			base.setTelephone(basement.getTelephone());
			base.setTicketPrice(basement.getTicketPrice());
			base.setTitle(basement.getTitle());
			base.setUrl(basement.getUrl());
			baseService.save(base);
			log.log(session, "基地模块", "添加基地信息");
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
			res.setStatusCode("300");
			res.setMessage("exception");
			return res;
		}
		res.setStatusCode("200");
		res.setMessage("添加基地信息成功");
		res.setCallbackType("closeCurrent");
		if (Integer.valueOf(province) == ProvinceEnum.Hebei.getStatus()) {
			res.setNavTabId("hebei");
		} else if (Integer.valueOf(province) == ProvinceEnum.Beijing.getStatus()) {
			res.setNavTabId("beijing");
		} else if (Integer.valueOf(province) == ProvinceEnum.Tianjin.getStatus()) {
			res.setNavTabId("tianjin");
		}

		return res;
	}

	/**
	 * 跳转到编辑基地信息页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEditBasement")
	public String toEditBasement(Model model, @ModelAttribute WebVo webVo) {
		String basementId = webVo.getBasementId();
		if (StringUtil.isBlank(basementId)) {
			return "redirect:basement/getBasePage.action";
		}
		BaseInfo basement = baseService.getBasementById(basementId);
		List<CityVo> citys = new ArrayList<CityVo>();
		List<CityInfo> cityss = baseService.getCitys();
		for (CityInfo c : cityss) {
			CityVo cv = new CityVo();
			cv.setId(c.getId());
			cv.setCityName(c.getCityName());
			if (citys.contains(cv)) {
				continue;
			} else {
				citys.add(cv);
			}
		}
		model.addAttribute("citys", citys);
		model.addAttribute("base", basement);
		return "basement/editBasement";
	}

	/**
	 * 编辑基地信息
	 * 
	 * @param webVo
	 * @return
	 */
	@RequestMapping("/editBasement")
	public @ResponseBody AjaxResponse editBasement(WebVo webVo, BasementVo basement, HttpSession session,
			HttpServletRequest request) {
		logger.info("编辑基地信息");
		AjaxResponse res = new AjaxResponse();
		AdminInfo admin = (AdminInfo) session.getAttribute("admin");
		String basementId = webVo.getBasementId();
		if (StringUtil.isBlank(basementId)) {
			res.setMessage("参数异常basementId is needed");
			res.setStatusCode("300");
			return res;
		}
		try {
			BaseInfo base = baseService.getBasementById(basementId);

			base.setUpdateTime(new Date());
			base.setAddress(basement.getAddress());
			base.setAdmin(admin);
			String area = null;
			String city = null;
			if (!StringUtil.isBlank(basement.getCityId())) {
				CityInfo c = baseService.getCityById(basement.getCityId());
				city = c.getCityName();
				if (!StringUtil.isBlank(basement.getTownId())) {
					Set<TownInfo> ts = c.getTowns();
					for (TownInfo t : ts) {
						if (t.getId() == Integer.valueOf(basement.getTownId())) {
							area = t.getTownName();
						}
					}
				}

			}

			base.setProvince(basement.getProvince());
			base.setArea(area);
			base.setCity(city);
			base.setCloseTime(basement.getCloseTime());
			base.setOpenTime(basement.getOpenTime());
			base.setContent(basement.getContent());

			boolean delF = true;
			MultipartFile pic = basement.getPic();
			String picUrl = FileUtil.saveFile(pic, "basementimg", request);
			if (picUrl != null) {
				// TODO:删除旧文件
				delF = FileUtil.delFile(base.getPicUrl(), "basementimg", session);
				if (delF) {
					base.setPicUrl(picUrl);
				}

			}

			MultipartFile video = basement.getVideo();
			String videoUrl = FileUtil.saveFile(video, "video", request);
			if (videoUrl != null) {
				// TODO删除旧文件
				if (delF) {
					base.setVideoUrl(videoUrl);
					delF = FileUtil.delFile(base.getVideoUrl(), "video", session);
				}

			}

			MultipartFile audio = basement.getAudio();
			String audioUrl = FileUtil.saveFile(audio, "audio", request);
			if (audioUrl != null) {
				// TODO:删除旧文件
				if (delF) {
					delF = FileUtil.delFile(base.getAudioUrl(), "audio", session);
					base.setAudioUrl(audioUrl);
				}

			}

			if (!delF) {
				logger.error("delete file faile!");
				res.setMessage("更新基地信息,删除旧文件出错");
				res.setStatusCode("300");
				return res;

			}
			base.setRecommend(basement.getRecommend());
			base.setSortNum(basement.getSortNum());
			base.setStatus(basement.getStatus());
			base.setTelephone(basement.getTelephone());
			base.setTicketPrice(basement.getTicketPrice());
			base.setTitle(basement.getTitle());
			base.setUrl(basement.getUrl());

			baseService.updateBase(base);
			log.log(session, "基地模块", "更新基地信息");
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
			res.setMessage("更新基地信息异常");
			res.setStatusCode("300");
			return res;
		}
		res.setMessage("更新基地信息成功");
		res.setStatusCode("200");
		res.setCallbackType("closeCurrent");
		if (Integer.valueOf(basement.getProvince()) == ProvinceEnum.Hebei.getStatus()) {
			res.setNavTabId("hebei");
		} else if (Integer.valueOf(basement.getProvince()) == ProvinceEnum.Beijing.getStatus()) {
			res.setNavTabId("beijing");
		} else if (Integer.valueOf(basement.getProvince()) == ProvinceEnum.Tianjin.getStatus()) {
			res.setNavTabId("tianjin");
		}
		return res;
	}

	/**
	 * 删除基地信息
	 * 
	 * @param webVo
	 * @return
	 */
	@RequestMapping("/delBasement")
	public @ResponseBody AjaxResponse toEditBasement(WebVo webVo, HttpSession session) {
		AjaxResponse res = new AjaxResponse();
		String basementId = webVo.getBasementId();
		if (StringUtil.isBlank(basementId)) {
			res.setMessage("basementId is needed");
			res.setStatusCode("300");
			return res;
		}
		BaseInfo base = baseService.getBasementById(basementId);
		if (base == null) {
			res.setMessage("删除基地信息不存在");
			res.setStatusCode("300");
			return res;
		}
		boolean delF = true;
		if (base.getPicUrl() != null) {
			delF = FileUtil.delFile(base.getPicUrl(), "basementimg", session);
			if (!delF) {
				logger.error("delete pic file faile!");

			}
		}
		if (base.getVideoUrl() != null) {

			delF = FileUtil.delFile(base.getVideoUrl(), "video", session);
			if (!delF) {
				logger.error("delete video file faile!");

			}
		}
		if (base.getAudioUrl() != null) {

			delF = FileUtil.delFile(base.getAudioUrl(), "audio", session);
			if (!delF) {
				logger.error("delete audio file faile!");

			}

		}
		delF = baseService.delBaseInfo(base);
		if(delF){
			logger.info("delete baseInfo OK!");
			log.log(session, "基地模块", "删除基地信息");
			res.setMessage("删除基地信息完成");
			res.setStatusCode("200");
		}else{
			logger.info("delete baseInfo not OK!");
			res.setMessage("删除基地信息出问题了");
			res.setStatusCode("300");
		}
		

		return res;
	}
}
