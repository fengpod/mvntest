package collection;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

public class SetTest {
	public static void main(String[] args) {
		Set<String> set = Sets.newHashSet();
		set.add("1");
		set.add("2");
		set.add("3");
		System.out.println(set.size());
		Iterator it = set.iterator();
		set.remove("1");
		for(;it.hasNext();){
			it.next();
		}
	}
}
