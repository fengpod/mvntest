package fastjson;

import java.util.StringTokenizer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		String str = "{\"id\":\"11\",\"value\":[{\"15\":[\"1\"]},{\"16\":[\"1\",\"2\"]},{\"14\":[\"啊啊啊\"]},{\"17\":[\"是是傻\"]}]}";
		JSONObject obj = JSON.parseObject(str);
		System.out.println(obj.get("id"));
		JSONArray vals = obj.getJSONArray("value");
		for(int i = 0;i<vals.size();i++){
			JSONObject val = vals.getJSONObject(i);
			for(String key : val.keySet()){
				System.out.println(key+":"+val.getString(key));
			}
		}
		String string = "hello world hello hadoop";
		StringTokenizer st = new StringTokenizer(string);
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
	}
}
