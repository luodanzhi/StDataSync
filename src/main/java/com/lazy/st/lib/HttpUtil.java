package com.lazy.st.lib;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/** HttpClient 的封装 */
public class HttpUtil {

	private static Logger logger = Logger.getLogger("HttpUtil");

	/**
	 * 返回读取url的内容
	 * @param args
	 */
	public static String clientGet(String url) {
		
		String retContent = null;

        // 创建默认的客户端实例  
        HttpClient httpCLient = new DefaultHttpClient();  
          
        // 创建get请求实例  
        HttpGet httpget = new HttpGet(url);  
          
        logger.info("executing request "+httpget.getURI());  
          
        try  
        {  
            // 客户端执行get请求 返回响应实体  
            HttpResponse response = httpCLient.execute(httpget);  
              
            // 服务器响应状态行  
            logger.info(response.getStatusLine());  
              
            Header[] heads = response.getAllHeaders();  
            // 打印所有响应头  
            for(Header h:heads){  
            	logger.debug(h.getName()+":"+h.getValue());  
            }  
              
            // 获取响应消息实体  
            HttpEntity entity = response.getEntity();  
              
            logger.info("------------------------------------");  
              
              
              
            if(entity != null){  
            	retContent = EntityUtils.toString(entity);   
                //响应内容  
            	logger.info(retContent);  
                  
            	logger.info("----------------------------------------");  
                // 响应内容长度  
            	logger.info("响应内容长度："+entity.getContentLength());  
            }  
              
        } catch (ClientProtocolException e){  
            e.printStackTrace();  
        } catch (IOException e){  
            e.printStackTrace();  
        }finally{  
            httpCLient.getConnectionManager().shutdown();  
        } 

        return retContent;
	}
}
