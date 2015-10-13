package com.sishuok.architecture1.customermgr.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sishuok.architecture1.common.vo.WebModel;
import com.sishuok.architecture1.customermgr.service.ICustomerService;
import com.sishuok.architecture1.customermgr.vo.CustomerModel;
import com.sishuok.architecture1.customermgr.vo.CustomerQueryModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.format.DateFormatHelper;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	@Resource
	private ICustomerService customerService;
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "customer/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") CustomerModel m){
		m.setRegisterTime(DateFormatHelper.date2str(System.currentTimeMillis()));
		customerService.create(m);
		return "customer/success";
	}
	
	@RequestMapping(value="/toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		CustomerModel m = customerService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "customer/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("m") CustomerModel m){
		customerService.update(m);
		return "customer/success";
	}
	@RequestMapping(value="/toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		CustomerModel m = customerService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "customer/delete";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam("uuid") int uuid){
		customerService.delete(uuid);
		return "customer/success";
	}
	@RequestMapping(value="/toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm") WebModel wm, Model model){
		CustomerQueryModel customerQueryModel = null;
		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
			customerQueryModel = new CustomerQueryModel();
		}else {
			customerQueryModel = JsonHelper.str2Object(wm.getQueryJsonStr(), CustomerQueryModel.class);
		}
		customerQueryModel.getPage().setCurrentPage(wm.getCurrentPage());
		if(wm.getNumPerPage()>0){
			customerQueryModel.getPage().setNumPerPage(wm.getNumPerPage());
		}
		Page<CustomerModel> dbPage = customerService.getByConditionPage(customerQueryModel);
		model.addAttribute("wm",wm);
		model.addAttribute("page",dbPage);
		
		return "customer/list";
	}
	@RequestMapping(value="/toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "customer/query";
	}
}
