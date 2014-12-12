package changlianginwhere;

import java.util.concurrent.TimeUnit;

public class Test {
	public final static A a = new A(1000*1000*20);
	public static void main(String[] args) throws InterruptedException {
		TimeUnit.SECONDS.sleep(60);
	}
}
class A{
	private int size;
	private int[] intArray;
	public A(int size){
		intArray = new int[size];
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int[] getIntArray() {
		return intArray;
	}
	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}
	
}