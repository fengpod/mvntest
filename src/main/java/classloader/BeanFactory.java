package classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class BeanFactory {
	public static Example getBean() {
		try {
			URLClassLoader ucl = new URLClassLoader(new URL[]{getClassPath()}){
				@Override
				public Class loadClass(String name)
						throws ClassNotFoundException {
					if("classloader.SimpleObj".equals(name)){
						return findClass(name);
					}
					return super.loadClass(name);
				}
			};
			System.out.println(Example.class.getClassLoader());
			return (Example) ucl.loadClass("classloader.SimpleObj").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static URL getClassPath() {
	    String resName = 
	      ClassLoderTest.class.getName().replace('.', '/') + ".class";
	    String loc = 
	    		ClassLoderTest.class.getClassLoader().getResource(resName)
	      .toExternalForm();    
	    URL cp;
	    try {
	      cp = new URL(loc.substring(0, loc.length() - resName.length()));
	    } catch (MalformedURLException e) {
	      throw new RuntimeException(e);
	    }
	    return cp;
	  }
}
