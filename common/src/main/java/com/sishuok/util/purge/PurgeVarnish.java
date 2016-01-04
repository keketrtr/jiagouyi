package com.sishuok.util.purge;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;

public class PurgeVarnish {
	public void purge(String url){
		HttpClient client = new HttpClient();
		HttpMethod method = new PurgeMethod(url);
		//添加头信息告诉服务端可以对Response进行GZip压缩。
		//我在varnish配置文件的vcl_hash函数中对Accept-Encoding进行了hash，
		//如果请求里面不带Accept-Encoding信息头的话，就找不到varnish中的缓存，导致无法清除缓存。
		//vcl_hash函数中对Accept-Encoding进行了hash是因为有些浏览器可能不支持压缩，
		//如果他们找到的缓存是被压缩过的，就会导致浏览器无法正确解析返回内容。
		method.setRequestHeader("Accept-Encoding", "gzip, deflate");
		try {
			int status = client.executeMethod(method);
			System.out.println("status==="+status);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			method.releaseConnection();
		}
	}
	
	public static void main(String[] args) {
		PurgeVarnish purgeVarnish = new PurgeVarnish();
		purgeVarnish.purge("http://172.16.15.111:1111/a.jsp");
	}
}

class PurgeMethod extends HttpMethodBase {
	public PurgeMethod() {
		super();
		setFollowRedirects(true);
	}
	public PurgeMethod(String url) {
		super(url);
		setFollowRedirects(true);
	}

	@Override
	public String getName() {
		return "PURGE";
	}
	
}
