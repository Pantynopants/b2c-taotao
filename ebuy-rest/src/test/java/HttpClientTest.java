import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @author shen
 * @desc httpclient请求参数
 * @date 2016年10月4日
 */
public class HttpClientTest {
	
	//@Test
	public void getTest() throws Exception, Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpclient对象
		
		//HttpGet getTset = new HttpGet("https://www.google.com.hk");
		HttpGet getTset = new HttpGet("https://www.sougou.com");//get请求
		CloseableHttpResponse response = httpClient.execute(getTset);//响应结果
		
		int code = response.getStatusLine().getStatusCode();//响应状态
		System.out.println(code);
		
		HttpEntity entity = response.getEntity();
		String data = EntityUtils.toString(entity, "utf-8");//设置编码
		System.out.println(data);
		
		response.close();
		httpClient.close();
	}
	
	
	/**
	 * @author shen
	 * @desc 带参
	 * @date 2016年10月4日
	 */
	//@Test
	public void getTestParam() throws Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		URIBuilder uriBuilder = new URIBuilder("http://baidu.com/s");
		uriBuilder.addParameter("wd", "jboss");
		HttpGet getTset = new HttpGet(uriBuilder.build());
		
		CloseableHttpResponse response = httpClient.execute(getTset);
		int code = response.getStatusLine().getStatusCode();//响应状态
		System.out.println(code);
		
		HttpEntity entity = response.getEntity();
		String data = EntityUtils.toString(entity, "utf-8");//设置编码
		System.out.println(data);
		
		response.close();
		httpClient.close();
	}
	
	
	/**
	 * @author shen
	 * @desc post请求
	 * @date 2016年10月4日
	 */
	@Test
	public void postTest() throws Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost postTest = new HttpPost("http://localhost:8082/httpclient/post.html");
		CloseableHttpResponse response = httpClient.execute(postTest);
		String data = EntityUtils.toString(response.getEntity());
		System.out.println(data);
		
		response.close();
		httpClient.close();
	}
	
	/**
	 * @author shen
	 * @desc post请求，带参数
	 * @date 2016年10月4日
	 */
	//@Test
	public void postTestParam() throws Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost postTest = new HttpPost("http://localhost:8082/httpclient/post.html");
		
		//模拟表单数据
		List<NameValuePair> listData = new ArrayList<>();
		listData.add(new BasicNameValuePair("username","English中文"));
		listData.add(new BasicNameValuePair("password","1243"));
		
		//创建Entity把数据放到post响应中
		StringEntity entity = new UrlEncodedFormEntity(listData);
		postTest.setEntity(entity);
		
		CloseableHttpResponse response = httpClient.execute(postTest);
		String data = EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println(data);
		
		response.close();
		httpClient.close();
	}
	
}
