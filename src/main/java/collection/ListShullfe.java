package collection;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

public class ListShullfe {
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("1","2","3","4","5","6","101");
		shuffle(list);
		shuffle(list);
		shuffle(list);
		for(String str : list){
			System.out.println(str);
		}
	}
	
	private static void shuffle(List<String> list){
		Random rnd = new Random();
		for(int i = 0;i<list.size();i++){
			swap(list,i,rnd.nextInt(i+1));
		}
	}

	private static void swap(List<String> list, int i, int nextInt) {
		list.set(i, list.set(nextInt, list.get(i)));
	}
}
