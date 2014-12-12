package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler{
	
	private Object target;
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println(1);
		Object obj = method.invoke(target, args);
		System.out.println(2);
		return obj;
	}
	
	public Object getProxy(Object target){
		this.target = target;
		Class classes = target.getClass();
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),classes.getInterfaces() , this);
	}

}
