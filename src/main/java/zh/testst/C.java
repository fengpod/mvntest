package zh.testst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class C implements IA, IB {

	public int a() {
		return 0;
	}

	public String b() {
		return null;
	}
	public void testE() {
		File file = new File("d:/2.swf");
			FileInputStream fis;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					try {
						throw e;
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					}
				}
				System.out.println("after e");
				
	}
	
}
