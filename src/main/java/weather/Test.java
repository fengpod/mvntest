package weather;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.client.utils.URLEncodedUtils;
import org.mortbay.util.UrlEncoded;

import com.sun.jersey.core.util.Base64;

public class Test {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalStateException {
		String url = "http://open.weather.com.cn/data/?areaid=101010100&type=forecast3d&date=201406170944&appid=e55487236fe465ac";
		String privateKey = "927b0d_SmartWeatherAPI_4976300";
		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec macKey = new SecretKeySpec(privateKey.getBytes(),"raw");
	    mac.init(macKey);
	    String s = URLEncoder.encode(new String(Base64.encode(mac.doFinal(url.getBytes()))),"UTF-8");
//	    String s = new String(Base64.encode(mac.doFinal(url.getBytes())));
	    System.out.println(s);
	    System.out.println(s.length());
	}
}
