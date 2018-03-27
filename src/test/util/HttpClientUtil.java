package test.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * HttpClient工具类
 * 
 * @return
 * @author lbb
 * @create 
 */
public class HttpClientUtil {

    private final static  CloseableHttpClient httpClient = createHttpClient(PropertiesUtil.getInt("http.maxTotal"), PropertiesUtil.getInt("http.defaultMaxPerRoute"));
	private static final String DEFAULT_CHARSET = "UTF-8";
    
    private static void config(HttpRequestBase httpRequestBase) {

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(PropertiesUtil.getInt("http.connectionRequestTimeout"))
                .setConnectTimeout(PropertiesUtil.getInt("http.connectTimeout")).setSocketTimeout(PropertiesUtil.getInt("http.socketTimeout")).build();
        httpRequestBase.setConfig(requestConfig);
    }


    /**
     * 创建HttpClient对象
     * 
     * @return
     * @author
     * @create 
     */
    public static CloseableHttpClient createHttpClient(int maxTotal,
            int maxPerRoute) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory
                .getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory
                .getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory> create().register("http", plainsf)
                .register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
                registry);
        // 将最大连接数增加
        cm.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(maxPerRoute);
        // 请求重试处理
        DefaultHttpRequestRetryHandler httpRequestRetryHandler = new  DefaultHttpRequestRetryHandler(0,false);
        //定时清理连接池中的无效连接
        IdleConnectionEvictor connEvictor = new IdleConnectionEvictor(cm,1,TimeUnit.MINUTES);
        connEvictor.start();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler).build();
        return httpClient;
    }

    private static void setPostFormParams(HttpPost httpost, Object obj){
    	JSONObject params = (JSONObject)JSON.toJSON(obj);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @param httpost
     * @param params
     */
    private static void setPostParams(HttpPost httpost, String param) {
        HttpEntity entity = new StringEntity(param, "UTF-8");
        try {
        	httpost.setEntity(entity);
        	// 销毁  
			EntityUtils.consume(entity);
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
   
    private static void setPostParams(HttpPost httpost, Object obj) {
    	JSONObject json = (JSONObject)JSON.toJSON(obj);
        HttpEntity entity = new StringEntity(json.toJSONString(), "UTF-8");
        try {
        	httpost.setEntity(entity);
        	// 销毁  
			EntityUtils.consume(entity);
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
    /**
     * POST请求URL获取内容
     * 
     * @param url
     * @return
     * @author 
     * @create 
     */
	public static String post(String url, Map<String, Object> params){
		String result = null;
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setPostParams(httppost, params);
		result = postBase(httppost);
		return result;
	}
	public static String post(String url, String params){
		String result = null;
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setPostParams(httppost, params);
		result = postBase(httppost);
		return result;
	}
	
	public static String postForm(String url, Object obj){
		String result = null;
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setPostFormParams(httppost, obj);
		result = postBase(httppost);
		return result;
	}
	public static String postForm(String url, Object obj,Map<String, String> headers){
		String result = null;
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setHeader(httppost,headers);
		setPostFormParams(httppost, obj);
		result = postBase(httppost);
		return result;
	}
	
	/**
     * 添加header 调用 httpost.setHeader("Referer", header);
     * @param httpost
     * @param params
     * @param header
     */
    public static String post(String url , Map<String, Object> params,Map<String, String> headers) {
    	String result = null;
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setHeader(httppost,headers);
		setPostParams(httppost, params);
		result = postBase(httppost);
		return result;
    }
	
    public static String post(String url ,Object obj,Map<String, String> headers) {
    	String result = null;
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setPostParams(httppost, obj);
		setHeader(httppost,headers);
		result = postBase(httppost);
		return result;
    }
    private static void setHeader(HttpRequest httpRequest,Map<String, String> headers) {
    	for(String key:headers.keySet()) {
    		httpRequest.setHeader(key, headers.get(key));
    	}
	}


	/**
     * 
     * @param httpost
     * @param params
     * @param header
     */
    private static String postBase(HttpPost httppost) {
    	String result = null;
		HttpEntity entity = null;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httppost, HttpClientContext.create());
			entity = response.getEntity();
			result = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null){
					response.close();
				}
				if(entity != null){
					EntityUtils.consume(entity);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			httppost.releaseConnection();
		}
		return result;
    }
    /**
     * GET请求URL获取内容
     * 
     * @param url
     * @return
     * @author 
     * @create 
     */
    public static String get(String url) {
    	String result=null;
    	HttpEntity entity = null;
        HttpGet httpget = new HttpGet(url);
        config(httpget);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget, HttpClientContext.create());
            entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null){
                	response.close();
                }
                if(entity != null){
					EntityUtils.consume(entity);
				}
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpget.releaseConnection();
        }
        return result;
    }
    public static String get(String url,Map<String,String> headers) {
    	String result=null;
    	HttpEntity entity = null;
        HttpGet httpget = new HttpGet(url);
        config(httpget);
        setHeader(httpget,headers);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget, HttpClientContext.create());
            entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null){
                	response.close();
                }
                if(entity != null){
					EntityUtils.consume(entity);
				}
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpget.releaseConnection();
        }
        return result;
    }
    /**
	 * 上传媒体文件
	 * 
	 * @param url
	 * @param params
	 * @param file
	 * @return
     * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String url, File file) {
		
		String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; // 定义数据分隔线
		
		HttpURLConnection conn = null;
		InputStream in = null;
		StringBuffer bufferRes = null;
		try {
			conn = getConnection(url, file, BOUNDARY);
			getOutPutStream(conn,file, BOUNDARY);
			
			// 定义BufferedReader输入流来读取URL的响应
			in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
			String valueString = null;
			bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (conn != null) {
				// 关闭连接
				conn.disconnect();
			}
		}
		return bufferRes.toString();
	}
    
	/**
	 * 
	 * @param url
	 * @param file
	 * @param BOUNDARY 
	 * @return
	 * @throws IOException 
	 */
	private static HttpURLConnection getConnection(String url, 
			File file, String BOUNDARY) throws IOException {
    	
    	URL urlGet = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urlGet.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
		conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		conn.setConnectTimeout(3000);
		conn.setReadTimeout(3000);
		return conn;
	}
	
	/**
	 * 
	 * @param conn
	 * @param file
	 * @param BOUNDARY 
	 * @return
	 * @throws IOException 
	 */
	private static void getOutPutStream(HttpURLConnection conn, 
			File file, String BOUNDARY) throws IOException {
    	OutputStream out = new DataOutputStream(conn.getOutputStream());
		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] data = sb.toString().getBytes();
		out.write(data);
		
		DataInputStream fs = new DataInputStream(new FileInputStream(file));
		byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = fs.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
		fs.close();
		out.write(end_data);
		out.flush();
		out.close();
	}

	/**
	 * https 域名校验
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;// 直接返回true
		}
	}
}
