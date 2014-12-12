package citycode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class TagBean {
	private String name;
	private Map<String, String> attrList = null;
	private ArrayList<TagBean> childList = new ArrayList<TagBean>();
	private boolean isLast =false;

	public void print(int count) {
		for (int i = 0; i < count; i++) {
			System.out.print(" ");
		}
		System.out.print(name + ":");
		if (attrList != null) {
			Iterator<String> keySetItr = attrList.keySet().iterator();
			while (keySetItr.hasNext()) {
				String key = keySetItr.next();
				System.out.print(key + "--" + attrList.get(key) + "\t");
			}
			System.out.println();
		} else {
			System.out.println();
		}
		if (childList.size() > 0) {
			int newCount = ++count;
			for (TagBean temp : childList) {
				temp.print(newCount);
			}
		} else {
			return;
		}
	}

	public void generateJSON(int nowLevel, int targetLevel) throws FileNotFoundException{
		if(nowLevel > targetLevel){
			System.out.println("输入有误");
			return;
		}
		if(nowLevel == targetLevel){
//			System.out.print(name + ":");
//			if (attrList != null) {
//				Iterator<String> keySetItr = attrList.keySet().iterator();
//				while (keySetItr.hasNext()) {
//					String key = keySetItr.next();
//					System.out.print(key + "--" + attrList.get(key) + "\t");
//				}
//				System.out.println();
//			} else {
//				System.out.println();
//			}
			return;
		}
		nowLevel++;
		if(nowLevel == targetLevel){
			String id = attrList.get("parentCode");
			File file = new File("D:\\temp\\areaData\\"+id+".html");
			if(!file.exists()){
				try {
					file.createNewFile();
					OutputStreamWriter temp = new OutputStreamWriter(new FileOutputStream(file, true));
					temp.write("[");
					temp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file, true));
			try {
				osw.write(JSONObject.toJSONString(attrList));
				if(this.isLast){
					osw.write("]");
				}else{
					osw.write(",");
				}
				osw.flush();
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(childList.size() > 0){
			for(int i =0;i< childList.size();i++){
				TagBean temp =  childList.get(i);
				if(i == childList.size() -1){
					temp.isLast = true;
				}
				temp.generateJSON(nowLevel, targetLevel);
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttrList() {
		if (attrList == null) {
			attrList = new HashMap<String, String>();
		}
		return attrList;
	}

	public void setAttrList(Map<String, String> attrList) {
		this.attrList = attrList;
	}

	public ArrayList<TagBean> getChildList() {
		return childList;
	}

	public void setChildList(TagBean childTag) {
		this.childList.add(childTag);
	}
}
