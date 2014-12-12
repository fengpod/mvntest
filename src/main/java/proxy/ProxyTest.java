package proxy;

import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.ProxyGenerator;

public class ProxyTest {
	public static void main(String[] args) {
		TargetImpl target = new TargetImpl();
		MyInvocationHandler handler = new MyInvocationHandler();
		TargetInterface proxy = (TargetInterface) handler.getProxy(target);
//		System.out.println(proxy.getClass().getSuperclass());
		proxy.getRandomNum();
		proxy.getPrint();
		proxy.getPrintInt("str");
		writeProxyClassToHardDisk("d:/$Proxy11.class");
	}
	public static void writeProxyClassToHardDisk(String path) {  
        // 第一种方法，这种方式在刚才分析ProxyGenerator时已经知道了  
        // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);  
          
        // 第二种方法  
          
        // 获取代理类的字节码  
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy11", TargetImpl.class.getInterfaces());  
          
        FileOutputStream out = null;  
          
        try {  
            out = new FileOutputStream(path);  
            out.write(classFile);  
            out.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
