package com.lazy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientGet2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BufferedReader in = null; 
		
        // 创建默认的客户端实例  
        CloseableHttpClient httpclient = HttpClients.createDefault();
          
        // 创建get请求实例  
        HttpPost httppost = new HttpPost("http://table.finance.yahoo.com/table.csv?s=600000.ss&a=00&b=01&c=2015");  
        //HttpPost httppost = new HttpPost("http://download.finance.yahoo.com/d/quotes.csv?s=000625.sz&f=snohgl1v");  
          
        System.out.println("executing request "+httppost.getURI());  
          
        try
        {  
              
            // 客户端执行get请求 返回响应实体  
            HttpResponse response = httpclient.execute(httppost);  
              
            // 服务器响应状态行  
            System.out.println(response.getStatusLine());  
              
            Header[] heads = response.getAllHeaders();  
            // 打印所有响应头  
            for(Header h:heads){  
                System.out.println(h.getName()+":"+h.getValue());  
            }  
              
            // 获取响应消息实体  
            HttpEntity entity = response.getEntity();  
              
            System.out.println("------------------------------------");  
              
              
              
            if(entity != null){  
                in = new BufferedReader(new InputStreamReader(entity 
                        .getContent()));  
                StringBuffer sb = new StringBuffer("");  
                String line = "";  
                String NL = System.getProperty("line.separator");  
                while ((line = in.readLine()) != null) {  
                    sb.append(line + NL);  
                }  
                in.close();  
                String result = sb.toString();
                                  
                //响应内容  
                System.out.println(result);  
                  
                System.out.println("----------------------------------------");  
            }  
              
        } catch (ClientProtocolException e){  
            e.printStackTrace();  
        } catch (IOException e){  
            e.printStackTrace();  
        }finally{  
        	try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
        } 

	}

}
