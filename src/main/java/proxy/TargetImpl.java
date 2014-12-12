package proxy;

import java.util.Random;

public class TargetImpl implements TargetInterface{

	public void getRandomNum() {
		System.out.println( new Random().nextInt());
	}
	public void getPrint() {
		System.out.println(123);
	}
	public void getPrintInt(String s) {
		System.out.println(s);
	}

}
