package com.sishuok.architecture1;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sishuok.architecture1.customermgr.service.ICustomerService;
import com.sishuok.architecture1.customermgr.vo.CustomerModel;

@Controller
@RequestMapping("/")
public class LoginController {
	@Resource
	private ICustomerService customerService;
	
	@RequestMapping(value="/toLogin", method=RequestMethod.GET)
	public String toLogin(){
		return "login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("customerId") String customerId, @RequestParam("pwd") String pwd,
			HttpServletResponse response){
		if(StringUtils.isEmpty(customerId)){
			return "login";
		}
		CustomerModel customerModel = customerService.getByCustomerId(customerId);
		if(customerModel == null){
			return "login";
		}
		
		Cookie cookie = new Cookie("MyLogin", customerModel.getUuid()+","+System.currentTimeMillis());
		response.addCookie(cookie);
		
		return "redirect:/toIndex";
	}
}
