package zh.testst.register;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Register {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpHost hostProxy = new HttpHost("202.84.17.41", 8080);
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,hostProxy);
		HttpPost httppost = null;
		httppost = new HttpPost("http://login.home.news.cn/profile/xuanid.do?&o=768836da43c649f0b6954ca79ab23f2b");
		HttpResponse httpResponse = httpclient.execute(httppost);
		HttpEntity entity = httpResponse.getEntity();
		String resultStr = EntityUtils.toString(entity,"UTF-8");
		if (entity != null) {
			EntityUtils.consume(entity);
		}
		System.out.println(resultStr);
	}
}
