package test.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 用来获取Access_Token和JsAPI_Ticket
 * 
 * @author 郭青松
 */
public class TokenHttpUtils {

	/**
	 * 工具类私有构造
	 */
	private TokenHttpUtils () {

	}
	
	/**
	 * 获取微信的关键token和ticket
	 * 
	 * @param url 请求的微信接口
	 * @param type 请求方式
	 * @return
	 */
	public static JSONObject httpUrl(String url, String type) {

		StringBuffer token = null;

		if ("GET".equals(type)) {
			// 创建可关闭的http短连接
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
				
				// 创建httpget
				HttpGet httpget = new HttpGet(url);
				// 执行get请求.
				CloseableHttpResponse response = httpclient.execute(httpget);
				try {
					// 获取响应实体
					HttpEntity entity = response.getEntity();
					// 打印响应状态
					if (entity != null) {
						// 打印响应内容长度
						// 有内容在赋值
						if (entity.getContentLength() > 0) {
							token = new StringBuffer();
							// 获取实体内容
							InputStream content = entity.getContent();
							BufferedReader br = new BufferedReader(new InputStreamReader(content,"utf-8"));
				            String temp = "";
				            while ((temp = br.readLine()) != null) {
				            	token.append(temp+"\n");
				            }
				            // 关流
				            br.close();
						}
					}
				} finally {
					httpclient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭连接,释放资源
				try {
					httpclient.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// 转换成json
			JSONObject jsonObject = JSON.parseObject(token.toString());
			return jsonObject;

		} else if ("POST".equals(type)) {
			return null;
		} else {
			return null;
		}
		
	}
	
}
