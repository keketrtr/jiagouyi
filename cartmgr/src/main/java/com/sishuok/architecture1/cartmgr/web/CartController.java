package com.sishuok.architecture1.cartmgr.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sishuok.architecture1.cartmgr.service.ICartService;
import com.sishuok.architecture1.cartmgr.vo.CartModel;
import com.sishuok.architecture1.cartmgr.vo.CartQueryModel;
import com.sishuok.architecture1.common.vo.WebModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/cart")
public class CartController {
	@Resource
	private ICartService cartService;
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "cart/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") CartModel m){
		cartService.create(m);
		return "cart/success";
	}
	
	@RequestMapping(value="/toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		CartModel m = cartService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "cart/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("m") CartModel m){
		cartService.update(m);
		return "cart/success";
	}
	@RequestMapping(value="/toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		CartModel m = cartService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "cart/delete";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam("uuid") int uuid){
		cartService.delete(uuid);
		return "cart/success";
	}
	@RequestMapping(value="/toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm") WebModel wm, Model model){
		CartQueryModel cartQueryModel = null;
		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
			cartQueryModel = new CartQueryModel();
		}else {
			cartQueryModel = JsonHelper.str2Object(wm.getQueryJsonStr(), CartQueryModel.class);
		}
		cartQueryModel.getPage().setCurrentPage(wm.getCurrentPage());
		if(wm.getNumPerPage()>0){
			cartQueryModel.getPage().setNumPerPage(wm.getNumPerPage());
		}
		Page<CartModel> dbPage = cartService.getByConditionPage(cartQueryModel);
		model.addAttribute("wm",wm);
		model.addAttribute("page",dbPage);
		
		return "cart/list";
	}
	@RequestMapping(value="/toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "cart/query";
	}
}
