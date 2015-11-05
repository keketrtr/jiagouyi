package com.sishuok.util;
/**
 * 获取类的相关信息
 * @author zhaoyujie
 *
 */
public class ClassInfoUtil {
	
	public static String getCurrentMethodName() {
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}
	
}
