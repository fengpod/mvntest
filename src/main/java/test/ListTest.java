package test;

import java.util.ArrayList;
import java.util.List;


public class ListTest {
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		System.out.println(list.size());
		list.clear();
		System.out.println(list.size());
	}
}
