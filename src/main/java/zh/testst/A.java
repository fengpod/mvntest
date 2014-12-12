package zh.testst;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

 class A{
	public static void main(String[] args) throws IOException, InterruptedException {
//		int ow = 1920;
//		int oh = 700;
//		int lw = 992;
//		int lh = 600;
//		int lw_t = ow*lh/oh;
//		String str = " convert -resize "+lw_t+"x600 d:/cate.jpg d:/12222.jpg";
//		str = "identify d:/1986.jpg";
//		System.out.println(str);
		
//		Process p = Runtime.getRuntime().exec(str);
//		p.waitFor();
//		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		StringBuffer  buffer = new  StringBuffer();
//		String msg = null;
//		while((msg = br.readLine())!= null){
//		   buffer.append(msg);
//		}
//		String[] aaa = buffer.toString().split(" ");
//		System.out.println(aaa[2]);
//		
//		System.out.println("sss".concat("00"));
		long l = System.currentTimeMillis();
		int[] array = new int[100000];
		for(int i = 0 ;i<99999;i++){
			array[i] = -1;
		}
		array[99999]=1;
		List<Integer> list = new ArrayList<Integer>();
		for(int i : array){
//			if(i == -1){
//				array = ArrayUtils.removeElement(array, -1);
//			}
			if(i != -1){
				list.add(i);
			}
		}
		array =list.toArray();
		for(int i : array){
			System.out.println(i);
		}
		long l2 = System.currentTimeMillis();
		System.out.println( l2- l);
		
	}
}
