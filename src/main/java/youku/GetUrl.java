package youku;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

public class GetUrl {
	public static void main(String[] args) throws Exception {
		String fileType = "flv";
		String url = "http://v.youku.com/v_show/id_XNzQ4MjUwOTgw.html";
		String key = getKey(url);
		url = getMetaUrl(key);
		String resp = httpClientGet(url);
//		JSONArray infos = parseMetaJson(resp,fileType);
		String ip = getIpFromJson(resp);
		String ep = getEpFromJson(resp);
		String[] data = getData(ep);
		String sid = getSid(data);
		String token = getToken(data);
		ep = getEp(key, sid, token);
		System.out.println(ep);
		String m3u8Url = getM3u8Url(ep,ip,sid,token,fileType,key);
		resp = httpClientGet(m3u8Url);
		String[] contents = resp.split("\r\n");
		Set<String> urlSet = Sets.newHashSet();
		for(String content : contents){
			if(content.startsWith("http://")){
				content = content.substring(0, content.indexOf("?"));
//				System.out.println(content);
//				if(!urlSet.contains(content)){
					urlSet.add(content);
//				}
			}
		}
		for(String subUrl : urlSet){
			System.out.println(subUrl);
		}
	}
	private static String getM3u8Url(String ep, String ip, String sid,
			String token, String type, String vid) {
		return "http://pl.youku.com/playlist/m3u8?ctype=12&ep="+ep+"&ev=1&keyframe=1&oip="+ip+"&sid="+sid+"&token="+token+"&type="+type+"&vid="+vid;
	}
	private static String getEpFromJson(String resp) {
		JSONObject obj =getDataFromJson(resp);
		return obj.getString("ep");
	}
	private static String getKey(String url){
	    return url.substring(url.indexOf("id_")+3,url.indexOf(".html"));
	}
	private static String getMetaUrl(String key){
		return "http://v.youku.com/player/getPlayList/VideoIDS/"+key+"/Pf/4/ctype/12/ev/1";
	}
	
	private static String httpClientGet(String url) throws Exception{
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// 目标地址
		HttpGet httpget = new HttpGet(url);
		// 执行
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		String resultStr = EntityUtils.toString(entity,"utf-8");
		if (entity != null) {
			EntityUtils.consume(entity);
		}
		return resultStr;
	}
	
	private static JSONArray parseMetaJson(String json,String fileType){
		JSONObject obj = getDataFromJson(json);
		obj = obj.getJSONObject("segs");
		System.out.println(obj);
		JSONArray array = obj.getJSONArray(fileType);
		return array;
	}
	
	private static JSONObject getDataFromJson(String json) {
		JSONObject obj = JSONObject.parseObject(json);
		JSONArray array = obj.getJSONArray("data");
		obj = array.getJSONObject(0);
		return obj;
	}
	
	private static String getIpFromJson(String json){
		JSONObject obj =getDataFromJson(json);
		return obj.getString("ip");
	}
	
	private static String myEncoder(String a, byte[] c, boolean isToBase64) throws UnsupportedEncodingException
    {
        String result = "";
        List<Byte> bytesR = new ArrayList<Byte>();
        int f = 0, h = 0, q = 0;
        int[] b = new int[256];
        for (int i = 0; i < 256; i++)
                b[i] = i;
        while (h < 256)
        {
            f = (f + b[h] + a.charAt(h % a.length())) % 256;
            int temp = b[h];
            b[h] = b[f];
            b[f] = temp;
            h++;
        }
        f = 0; h = 0; q = 0;
        while (q < c.length)
        {
            h = (h + 1) % 256;
            f = (f + b[h]) % 256;
            int temp = b[h];
            b[h] = b[f];
            b[f] = temp;
            byte[] bytes = new byte[] { (byte)(c[q] ^ b[(b[h] + b[f]) % 256]) };
            bytesR.add(bytes[0]);
            result += new String(bytes);
            q++;
        }
        if (isToBase64)
        {
        	byte[] bs = new byte[bytesR.size()];
        	int i = 0;
        	for(byte by : bytesR){
        		bs[i++]=by;
        	}
            result = new BASE64Encoder().encode(bs);
        }
        return result;
    }
    public static String getEp(String vid, String sid,String token) throws Base64DecodingException, IOException
    {
    	String template = "bf7e5f01";
        String whole = sid+"_"+vid+"_"+token;
        byte[] newbytes = whole.getBytes();
        return myEncoder(template, newbytes, true);
    }
    private static String getSid(String[] data){
    	return data[0];
    }
    private static String getToken(String[] data){
    	return data[1];
    }
    private static String[] getData(String ep){
    	String template1 = "becaf9be";
		try {
			byte[] bytes = new BASE64Decoder().decodeBuffer(ep);
			String temp = myEncoder(template1, bytes, false);
	        return temp.split("_");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
