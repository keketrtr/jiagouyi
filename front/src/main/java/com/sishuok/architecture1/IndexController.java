package com.sishuok.architecture1;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sishuok.architecture1.goodsmgr.service.IGoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;

@Controller
@RequestMapping("/")
public class IndexController {
	@Resource
	private IGoodsService goodsService;
	
	@RequestMapping(value="/toIndex", method=RequestMethod.GET)
	public String toIndex(Model model){
		GoodsQueryModel qm = new GoodsQueryModel();
		qm.getPage().setNumPerPage(100);
		Page<GoodsModel> page = goodsService.getByConditionPage(qm);
		model.addAttribute("page", page);
		return "index";
	}
	
}
