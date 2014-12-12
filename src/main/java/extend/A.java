package extend;

public class A {
	private int a=1;
	public void a(){
		b();
	}
	public void b(){
		System.out.println("A.b");
	}
	
	static class C extends A{
		public void b(){
			System.out.println(super.a);
		}
	}
	public static void main(String[] args) {
		A c = new C();
		System.out.println(c.getClass());
	}
}
