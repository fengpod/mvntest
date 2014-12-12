package classloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.digester.ObjectCreationFactory;
import org.jruby.compiler.ir.instructions.GetClassVariableInstr;

public class ClassLoderTest {
	private static Example obj = BeanFactory.getBean();
	private static Example obj2 = null;
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("java.class.path"));
		
		System.out.println("-------------");
		System.out.println(Thread.currentThread().getContextClassLoader());
		System.out.println(ClassLoderTest.class.getClassLoader());
		InputStream is = ClassLoderTest.class.getResourceAsStream("a.properties");
		InputStream is2 = ClassLoderTest.class.getClassLoader().getResourceAsStream("/a.properties");
		InputStream is3 = Thread.currentThread().getContextClassLoader().getResourceAsStream("a.properties");
		System.out.println("is"+is.available());
		System.out.println(is2.available());
		System.out.println(is3.available());
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(Example.class.getClassLoader());
		while(true){
			System.out.println(obj.getClass().getClassLoader());
			obj.print();
			obj2 = BeanFactory.getBean();
			obj2.print();
			System.out.println(obj2.getClass().getClassLoader());
			try {
				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
		 
}
