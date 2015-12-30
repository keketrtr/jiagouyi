package com.sishuok.architecture1.goodsmgr.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sishuok.architecture1.goodsmgr.service.GoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.interactive.InteractiveBaseController;

@Controller
@RequestMapping(value="/goodsFI")
public class GoodsForInteractiveController extends InteractiveBaseController {
	@Resource
	private GoodsService goodsService;
	@Override
	protected Object doCall(String opeType, Map<String, Object> map) {
		//根据opeType来转调逻辑层的业务方法
		if("1".equals(opeType)){
			System.out.println("now处理opeType=="+opeType);
		}else if("2".equals(opeType)){
			System.out.println("now处理opeType2222222222=="+opeType);
		}
		//准备一个测试数据来返回
		GoodsModel gm = new GoodsModel();
		gm.setName("goods123");
		gm.setUuid(11);
		return gm;
	}
	
}
