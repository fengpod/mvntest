package test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class Test{
	final static String str = null;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException {
//		List<ClassLoadTest> list = new ArrayList<ClassLoadTest>();
//		list.add(new ClassLoadTest());
//		System.out.println(list.size());
//		ClassLoadTest clt = new ClassLoadSubSub();
//		System.out.println(clt.getClass().getSimpleName());
//		System.out.println(clt.getClass().getSuperclass());
//		Class subclasss = ClasLoadSub.class;
//		System.out.println(subclasss.getClassLoader());
//		JSONObject json = new JSONObject();
//		System.out.println(json.getClass().getClassLoader());
//		System.out.println(String.class.getResource("String.class"));
//		Object obj = subclasss.newInstance();
//		str = "123";
//		main:s2();
//		String s = new String("文鑫");
//		s = new String(s.getBytes(),"utf8");
//		System.out.println(s.length());
//		for(Byte b : s.getBytes()){
//			System.out.println(b.intValue());
//		}
//		System.out.println(Bytes.bytesToVint(s.getBytes()));
//		StringBuffer buffer = new StringBuffer(16);
//		System.out.println(buffer.length());
		Comparator<String> com = new Comparator<String>() {

			public int compare(String o1, String o2) {
				
				return Integer.valueOf(o2).compareTo(Integer.valueOf(o1));
			}
		};
		final Map<String,String> map  = new TreeMap<String,String>(com);
		map.put("1", "2");
		map.put("5", "2");
		map.put("2", "2");
		map.put("3", "2");
		map.put("4", "2");
		for(String key : map.keySet()){
			System.out.println(key+":"+map.get(key));
		}
		ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<String, String>();
		for(int i = 0;i<16;i++){
			chm.put(i+"", i+"");
		}
		LinkedHashMap<String,String> linkedMap = new LinkedHashMap<String, String>(16,0.75f,true);
		linkedMap.put("1", "1");
		linkedMap.put("4", "4");
		linkedMap.put("2", "2");
		linkedMap.get("4");
		Set<Entry<String,String>> entry = linkedMap.entrySet();
		for(Entry e : entry){
			System.out.println(e.getKey());
		}
		for(String key : linkedMap.keySet()){
			System.out.println(key);
		}
//		byte[] bys = "其".getBytes();
//		for(byte b : bys){
//			System.out.println(b);
//		}
		String str = "\"\\u"+"5176\"";
		System.out.println(str);
		ArrayList<String> list = new ArrayList<String>();
		list.add(null);
		System.out.println(list.get(0));
		LinkedList<String> llist = new LinkedList<String>();
		llist.add(null);
		System.out.println(llist.get(0));
		String[] arrs = {"1","2","3"};
		List<String> l1 = new ArrayList<String>(Arrays.asList(arrs));
		System.out.println(l1.size());
		List<String> l2 = new ArrayList<String>();
		l2.add("4");
		l2.add("5");
		l2.add("6");
		System.out.println(l1.retainAll(l2));
		
		Calendar cal = Calendar.getInstance();
		String date = "2014103009";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		getFileByHour(cal);
	}
	
	public static List<File> getFileByHour(Calendar last){
		List<File> hours = createHours(last);
//		if(last.get(Calendar.HOUR_OF_DAY) != 23){
//			last.add(Calendar.HOUR_OF_DAY, 1);
//		}
		List<File> files = new ArrayList<File>();
		String lastHourStr = null;
		for(int i = 0;i<hours.size();i++){
			File file = hours.get(i);
			System.out.println(file.getName());
			if(file.exists()){
				files.add(file);
				lastHourStr = file.getName().substring(0,10);
			}
		}
		if(lastHourStr != null){
			System.out.println(lastHourStr);
		}
		return files;
	}
	private static List<File> createHours(Calendar cal) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
		Date date = new Date(System.currentTimeMillis());
		String dateStr2 = format.format(date);
		Calendar cal2 = Calendar.getInstance();
		try {
			cal2.setTime(format.parse(dateStr2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<File> list = new ArrayList<File>(24);
		while(cal.compareTo(cal2) == -1){
			cal.add(Calendar.HOUR_OF_DAY, 1);
			Date date2 = cal.getTime();
			String dateStr = format.format(date2);
			StringBuilder builder = new StringBuilder();
			builder.append("d:/huanbaofile/").append(dateStr);
			builder.append(".xls");
			File file = new File(builder.toString());
			list.add(file);
		}
		return list;
	}
	static void s(){
		System.out.println(1);
	}
	static void s2(){
		System.out.println(2);
	}
	
}
class ClasLoadSub extends ClassLoadTest{
	
}
class ClassLoadSubSub extends ClasLoadSub{
	
}