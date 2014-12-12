package extend;

public class B extends A {
	public void b(){
		System.out.println("B.b");
	}
	public static void main(String[] args) {
//		A a = new A().new C();
		A b = new B();
		System.out.println(b.getClass().getSuperclass());
	}
}
