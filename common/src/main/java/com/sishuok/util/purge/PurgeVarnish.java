package com.sishuok.util.purge;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;

public class PurgeVarnish {
	public void purge(String url){
		HttpClient client = new HttpClient();
		HttpMethod method = new PurgeMethod(url);
		
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
