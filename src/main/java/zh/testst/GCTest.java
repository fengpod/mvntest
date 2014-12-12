package zh.testst;

import java.util.ArrayList;
import java.util.List;

public class GCTest {
	public static void main(String[] args) {
		int a =1;
		change(a);
		System.out.println(a);
	}
	public static void change(int a ){
		a = 0;
	}
}
