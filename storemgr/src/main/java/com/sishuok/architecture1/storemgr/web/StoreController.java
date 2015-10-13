package com.sishuok.architecture1.storemgr.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sishuok.architecture1.common.vo.WebModel;
import com.sishuok.architecture1.storemgr.service.IStoreService;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.architecture1.storemgr.vo.StoreQueryModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/store")
public class StoreController {
	@Resource
	private IStoreService storeService;
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "store/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") StoreModel m){
		storeService.create(m);
		return "store/success";
	}
	
	@RequestMapping(value="/toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		StoreModel m = storeService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "store/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("m") StoreModel m){
		storeService.update(m);
		return "store/success";
	}
	@RequestMapping(value="/toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		StoreModel m = storeService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "store/delete";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam("uuid") int uuid){
		storeService.delete(uuid);
		return "store/success";
	}
	@RequestMapping(value="/toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm") WebModel wm, Model model){
		StoreQueryModel storeQueryModel = null;
		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
			storeQueryModel = new StoreQueryModel();
		}else {
			storeQueryModel = JsonHelper.str2Object(wm.getQueryJsonStr(), StoreQueryModel.class);
		}
		storeQueryModel.getPage().setCurrentPage(wm.getCurrentPage());
		if(wm.getNumPerPage()>0){
			storeQueryModel.getPage().setNumPerPage(wm.getNumPerPage());
		}
		Page<StoreModel> dbPage = storeService.getByConditionPage(storeQueryModel);
		model.addAttribute("wm",wm);
		model.addAttribute("page",dbPage);
		
		return "store/list";
	}
	@RequestMapping(value="/toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "store/query";
	}
}
