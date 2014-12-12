package serializable;

import java.io.Serializable;

public class B extends A implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String b;
	private String oUrl;
	

	public String getOUrl() {
		return oUrl;
	}

	public void setOUrl(String oUrl) {
		this.oUrl = oUrl;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
	
}
