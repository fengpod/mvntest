package serializable;

import java.io.Serializable;

public class A implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String a ;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
}
