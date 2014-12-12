package thread;


public class Test {
	public static void main(String[] args) throws InterruptedException {
			final T t = new T();
			new Thread(){
				@Override
				public void run() {
					t.out("zhangsan");				}
			}.start();
			new Thread(){
				@Override
				public void run() {
					t.out("lisi");				}
			}.start();
	}
	
	static class T{
		public synchronized void out(String name){
			for(int i= 0 ;i<name.length();i++){
				System.out.println(name.charAt(i));
			}
		}
	}
	
}