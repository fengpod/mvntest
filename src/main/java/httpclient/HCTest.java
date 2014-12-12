package httpclient;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HCTest {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		get();
	}
	public static void get() throws ClientProtocolException, IOException{
		HttpClient hc = new DefaultHttpClient();
		HttpGet get =  new HttpGet("http://my.xuan.news.cn/album/list.do");
		String cookieStr = "wdcid=37bc6854e31aa4d1; XHBLOG_XSID=5d93120e98e0441f90d4cff6f5ccc006; cookieId=e24972ea6ffa46f890e4adef376c14cc; XHCLOUDREAD_XSID=c814ac1a0c0541bd991d6d4eb17fb672; XHFORUM_XSID=1fd09224ade24a88959a66fc1c33edb8; XHLUCKY_XSID=f03c64e6828f4e66ab26d14d42e618de; REGISTERANDLOGIN_XSID=eb80e53ea9ae4103946eb12ea18e06ab; XHPORTAL_XSID=1b303ef86a8c44009fb0862fce5a6d3b; XHNEWSPACE_XSID=af997bb6073f4b6b99a9b8a55b62e4c1; XHCHAT_XSID=cca46e351a3a40ebb3694d171851d704; XHWEIBO_XSID=a02f2bdcf03c409f824242cbe2be80ae; GSID=670384218df8455fa8c5465cd58a3a65; sessionId=81b76e58c08d4d2a9a2ab232e77cd60b; userName=\"emhyczExMA==\"; bmlja05hbWU==\"t+/Gwg==\"; XHNEWSPACE=1yQMJHvGzs2RtkQBdJ0kvr8HTLBPsJyLmQLLnnmL27Wmvt4hnhCB!-973989572; JSESSIONID=9j31JHvJvz1nT5BNGYDzZvSwWx2mN9gcH3WmmZMmpyV4c1tRNFLc!-2112153379; clientlanguage=zh_CN";
		Header cookie = new BasicHeader("Cookie", cookieStr);
		get.addHeader(cookie);
		HttpResponse response = hc.execute(get);
		HttpEntity entity = response.getEntity();
		Header[] headers = response.getAllHeaders();
		for(Header header : headers){
			System.out.println(header.getName()+":"+header.getValue());
		}
		System.out.println(EntityUtils.toString(entity));
	}
}
