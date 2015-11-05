package com.sishuok.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
/**
 * 不集成spring，单独使用memcache的时候可以用该工具类
 * @author sunniwell
 *
 */
public class MemcachedUtil {
	private static MemCachedClient memCachedClient = new MemCachedClient();

	private MemcachedUtil() {
	}

	static {
		//服务器列表及其权重
		String[] servers = { "127.0.0.1:11211" };
		Integer[] weights = {1};
		
		SockIOPool pool = SockIOPool.getInstance();
		
		pool.setServers(servers);
		pool.setWeights(weights);
		//设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000*60*60*6);
		//设置连接池守护线程的睡眠时间
		pool.setMaintSleep(30);
		
		pool.setNagle(false);//禁用nagle算法
		pool.setSocketConnectTO(0);
		pool.setSocketTO(3000);//3秒超时
		pool.setHashingAlg(3);//设置为一致性hash算法
		//初始化并启动连接池
		pool.initialize();
	}
	
	public static MemCachedClient getMemCachedClient() {
		return memCachedClient;
	}

}
