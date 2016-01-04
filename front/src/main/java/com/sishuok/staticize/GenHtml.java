package com.sishuok.staticize;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GenHtml {
	public static final Map<String, byte[]> mapHtml = new HashMap<String, byte[]>();
	private void genStaticHtml(){
		try {
			//1：直接使用url去获取内容。为了提高效率，也可以用httpclient。
			URL url = new URL("http://localhost:9080/toIndex?gen=true");
			BufferedInputStream bin = new BufferedInputStream(url.openStream());
			byte[] bs = new byte[bin.available()];
			bin.read(bs);
			//2：接下来可以输出到文件，也可以放在内存中。简单起见，这里选择放在内容中。
			mapHtml.put("toIndex", bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void startGenStaticHtml(){
		Thread thread = new GenHtmlThread();
		thread.start();
	}
	private class GenHtmlThread extends Thread{
		@Override
		public void run() {
			while(true){
				genStaticHtml();
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
