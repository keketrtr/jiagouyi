package com.sishuok.consistance;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.interactive.InteractiveBaseController;
import com.sishuok.interactive.InteractiveCallHelper;

@Controller
@RequestMapping(value="/storeDispatchFI")
public class StoreDispatchForInteractiveController extends InteractiveBaseController {
	@Resource
	private InteractiveCallHelper callHelper;
	@Override
	protected Object doCall(String opeType, Map<String, Object> map) {
		//1：分别调用北京和上海的库存管理模块，去获得当前他们各自A商品的数据
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("goodsUuid", "1");
		
		mapParam.put("ip", "localhost");
		mapParam.put("port", "9080");
		mapParam.put("url", "/storeFI/call");
		
		StoreModel bjsm = callHelper.call("StoreModule", "1", mapParam, StoreModel.class);
		
		mapParam.put("ip", "localhost");
		mapParam.put("port", "8080");
		mapParam.put("url", "/front/storeFI/call");
		
		StoreModel shsm = callHelper.call("StoreModule", "1", mapParam, StoreModel.class);
		
		//2：进行逻辑判断，看看是否需要调整他们的库存
		
		//3：如果需要调整，按照一定的业务算法来计算新的数据
		int avg = (bjsm.getStoreNum()+shsm.getStoreNum())/2;
		
		//4：把这些数据分别的调用回去
		mapParam.put("ip", "localhost");
		mapParam.put("port", "9080");
		mapParam.put("url", "/storeFI/call");
		
		mapParam.put("adjustNum", bjsm.getStoreNum()-avg);
		callHelper.call("storeModule", "2", mapParam);
		
		mapParam.put("ip", "localhost");
		mapParam.put("port", "8080");
		mapParam.put("url", "/front/storeFI/call");
		
		mapParam.put("adjustNum", shsm.getStoreNum()-avg);
		callHelper.call("storeModule", "2", mapParam);
		
		return "{}";
	}
	
}
