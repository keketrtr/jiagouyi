package com.sishuok.architecture1.ordermgr.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sishuok.architecture1.common.vo.WebModel;
import com.sishuok.architecture1.ordermgr.service.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.vo.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.vo.OrderDetailQueryModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/orderDetail")
public class OrderDetailController {
	@Resource
	private IOrderDetailService orderDetailService;
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "orderDetail/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") OrderDetailModel m){
		orderDetailService.create(m);
		return "orderDetail/success";
	}
	
	@RequestMapping(value="/toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		OrderDetailModel m = orderDetailService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "orderDetail/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("m") OrderDetailModel m){
		orderDetailService.update(m);
		return "orderDetail/success";
	}
	@RequestMapping(value="/toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		OrderDetailModel m = orderDetailService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "orderDetail/delete";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam("uuid") int uuid){
		orderDetailService.delete(uuid);
		return "orderDetail/success";
	}
	@RequestMapping(value="/toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm") WebModel wm, Model model){
		OrderDetailQueryModel orderDetailQueryModel = null;
		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
			orderDetailQueryModel = new OrderDetailQueryModel();
		}else {
			orderDetailQueryModel = JsonHelper.str2Object(wm.getQueryJsonStr(), OrderDetailQueryModel.class);
		}
		orderDetailQueryModel.getPage().setCurrentPage(wm.getCurrentPage());
		if(wm.getNumPerPage()>0){
			orderDetailQueryModel.getPage().setNumPerPage(wm.getNumPerPage());
		}
		Page<OrderDetailModel> dbPage = orderDetailService.getByConditionPage(orderDetailQueryModel);
		model.addAttribute("wm",wm);
		model.addAttribute("page",dbPage);
		
		return "orderDetail/list";
	}
	@RequestMapping(value="/toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "orderDetail/query";
	}
}
