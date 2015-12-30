package com.sishuok.consistance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sishuok.architecture1.storemgr.service.StoreService;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.interactive.InteractiveBaseController;

@Controller
@RequestMapping(value="/storeFI")
public class StoreForInteractiveController extends InteractiveBaseController {
	@Resource
	private StoreService storeService;
	@Override
	protected Object doCall(String opeType, Map<String, Object> map) {
		if("1".equals(opeType)){
			//获取A商品的库存数据
			int goodsUuid = Integer.parseInt(map.get("goodsUuid").toString());
			StoreModel storeModel = storeService.getByGoodsUuid(goodsUuid);
			System.out.println("storeModel==="+storeModel);
			return storeModel;
		}else if("2".equals(opeType)){
			//把新的库存数据进行调整，并更新到数据库
			int goodsUuid = Integer.parseInt(map.get("goodsUuid").toString());
			int adjustNum = Integer.parseInt(map.get("adjustNum").toString());
			StoreModel storeModel = storeService.getByGoodsUuid(goodsUuid);
			storeModel.setStoreNum(storeModel.getStoreNum()-adjustNum);
			storeService.update(storeModel);
		}
		return "{}";
	}
	
}
