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
import com.sishuok.architecture1.ordermgr.service.IOrderService;
import com.sishuok.architecture1.ordermgr.vo.OrderModel;
import com.sishuok.architecture1.ordermgr.vo.OrderQueryModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.format.DateFormatHelper;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	@Resource
	private IOrderService orderService;
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "order/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") OrderModel m){
		m.setOrderTime(DateFormatHelper.date2str(System.currentTimeMillis()));
		orderService.create(m);
		return "order/success";
	}
	
	@RequestMapping(value="/toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		OrderModel m = orderService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "order/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("m") OrderModel m){
		orderService.update(m);
		return "order/success";
	}
	@RequestMapping(value="/toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		OrderModel m = orderService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "order/delete";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam("uuid") int uuid){
		orderService.delete(uuid);
		return "order/success";
	}
	@RequestMapping(value="/toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm") WebModel wm, Model model){
		OrderQueryModel orderQueryModel = null;
		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
			orderQueryModel = new OrderQueryModel();
		}else {
			orderQueryModel = JsonHelper.str2Object(wm.getQueryJsonStr(), OrderQueryModel.class);
		}
		orderQueryModel.getPage().setCurrentPage(wm.getCurrentPage());
		if(wm.getNumPerPage()>0){
			orderQueryModel.getPage().setNumPerPage(wm.getNumPerPage());
		}
		Page<OrderModel> dbPage = orderService.getByConditionPage(orderQueryModel);
		model.addAttribute("wm",wm);
		model.addAttribute("page",dbPage);
		
		return "order/list";
	}
	@RequestMapping(value="/toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "order/query";
	}
}
