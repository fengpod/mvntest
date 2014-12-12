package serializable;

import java.io.Serializable;

public class Parent implements Serializable{
	private int a;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public Parent() {
	}
}
