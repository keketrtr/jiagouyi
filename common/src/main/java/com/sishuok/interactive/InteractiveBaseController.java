package com.sishuok.interactive;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 进行模块间交互调用的基础控制器
 * @author sunniwell
 *
 */
public abstract class InteractiveBaseController {
	@RequestMapping("/call")
	@ResponseBody
	public Object call(@RequestParam("jsonParam") String jsonParam){
		if(StringUtils.isNotBlank(jsonParam) && jsonParam.contains("*")){
			jsonParam = jsonParam.replace("*", "#");
		}
		InteractiveModel im = InteractiveUtil.paramJson2Model(jsonParam);
		System.out.println("=================================");
		//去做真正的业务
		Object ret = doCall(im.getOpeType(), im.getMap());
		return ret;
	}
	protected abstract Object doCall(String opeType,Map<String, Object> map);
}
