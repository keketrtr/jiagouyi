package com.sishuok.architecture1.goodsmgr.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sishuok.architecture1.common.vo.WebModel;
import com.sishuok.architecture1.goodsmgr.service.IGoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {
	@Resource
	private IGoodsService goodsService;
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "goods/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") GoodsModel m){
		goodsService.create(m);
		return "goods/success";
	}
	
	@RequestMapping(value="/toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		GoodsModel m = goodsService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "goods/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("m") GoodsModel m){
		goodsService.update(m);
		return "goods/success";
	}
	@RequestMapping(value="/toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		GoodsModel m = goodsService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "goods/delete";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam("uuid") int uuid){
		goodsService.delete(uuid);
		return "goods/success";
	}
	@RequestMapping(value="/toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm") WebModel wm, Model model){
		GoodsQueryModel goodsQueryModel = null;
		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
			goodsQueryModel = new GoodsQueryModel();
		}else {
			goodsQueryModel = JsonHelper.str2Object(wm.getQueryJsonStr(), GoodsQueryModel.class);
		}
		goodsQueryModel.getPage().setCurrentPage(wm.getCurrentPage());
		if(wm.getNumPerPage()>0){
			goodsQueryModel.getPage().setNumPerPage(wm.getNumPerPage());
		}
		Page<GoodsModel> dbPage = goodsService.getByConditionPage(goodsQueryModel);
		model.addAttribute("wm",wm);
		model.addAttribute("page",dbPage);
		
		return "goods/list";
	}
	@RequestMapping(value="/toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "goods/query";
	}
}
