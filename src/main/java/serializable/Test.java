package serializable;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		A a = new A();
		a.setA("a");
		B b = getB(a);
		System.out.println(JSONObject.toJSONString(b));
	}
	public static B getB(A a) throws IllegalAccessException, InvocationTargetException{
		B b = new B();
		BeanUtils.copyProperties(b, a);
		b.setB("b");
		b.setOUrl("123");
		return b;
	}
}
