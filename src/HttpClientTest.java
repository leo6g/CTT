import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;

import test.util.HttpClientUtil;

public class HttpClientTest {

	public static void main(String[] args) throws UnknownHostException, URISyntaxException {
//		InetAddress ip=InetAddress.getByName("http://mobile.12306.cn/timeconig/getTime");  
//		System.out.println(ip.toString());  
//		System.out.println("Address:"+ip.getHostAddress());  
//		System.out.println("Name"+ip.getHostName());  
		URI uri = new URI("https://kyfw.12306.cn/otn/leftTicket/queryO?leftTicketDTO.train_date=2018-03-22&leftTicketDTO.from_station=ZZF&leftTicketDTO.to_station=AYF&purpose_codes=ADULT");
		InetAddress ip = InetAddress.getByName(uri.getHost());
		String ipAdd = ip.getHostAddress();
		System.out.println(uri.getHost());
		System.out.println(ipAdd);
		URI rewrite = URIUtils.rewriteURI(uri, new HttpHost(ipAdd));
		Map<String,String> header = new HashMap<String,String>();
		header.put("Host", "kyfw.12306.cn");
		System.out.println(rewrite);
		String result = HttpClientUtil.get(rewrite.toString(),header);
		System.out.println(result);
		/*InetAddress[] ips=InetAddress.getAllByName("https://mobile.12306.cn/");  
		for(InetAddress ip: ips){  
			System.out.println(ip.toString());  
			System.out.println("Address:"+ip.getHostAddress());  
			System.out.println("Name"+ip.getHostName());  
		}*/
	}
}
