package zh.testst;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class IpClient {
	static String rurl = "http://ip.taobao.com/service/getIpInfo.php?ip=";
//	static String rurl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException {
		Connection conn = getConn();
		List<String> list = getResult(conn);
		Map<String,Integer> map = new HashMap<String,Integer>();
		getArea(list,map);
		for(String country : map.keySet()){
			int count = map.get(country);
			System.out.println(map.size()+" "+country+"  "+count);
		}
//		System.out.println("\u7f8e\u56fd");
	}
	public static Connection getConn() throws SQLException{
		String url = "jdbc:mysql://192.168.69.8:3308/xh_space";
		String username = "xhspace";
		String passwd="xhspace";
		Connection conn = DriverManager.getConnection(url, username, passwd);
		return conn;
	}
	
	private static List<String> getResult(Connection conn) throws SQLException{
		String sql = "select loginip from login_info where logintime >'2014-01-10 00:00:00' ";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<String> list = new ArrayList<String>();
		while(rs.next()){
			String ip = rs.getString(1);
			list.add(ip);
		}
		conn.close();
		return list;
	}
	private static void getArea(List<String> list,Map<String,Integer> map) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpHost hostProxy = new HttpHost("202.84.17.41", 8080);
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,hostProxy);
		HttpPost httppost = null;
		int i =0;
		for(String ip : list){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			httppost = new HttpPost(rurl+ip);
//			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//	        HttpEntity entityForm = new UrlEncodedFormEntity(formParams, "UTF-8");
//			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
//			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
			HttpResponse httpResponse = httpclient.execute(httppost);
			HttpEntity entity = httpResponse.getEntity();
			String resultStr = EntityUtils.toString(entity,"UTF-8");
			if (entity != null) {
				EntityUtils.consume(entity);
			}
			JSONObject json = JSON.parseObject(resultStr);
			int code = json.getIntValue("code");
			if(code == 1){
				break;
			}
			json = (JSONObject) json.get("data");
			String country = (String) json.get("country");
//			String province = (String) json.get("province");
			if(map.get(country) == null){
				map.put(country, 1);
			}else{
				map.put(country, map.get(country)+1);
			}
			i++;
			System.out.println(list.size()+":  "+ i + "   "+country);
		}
	}
}
