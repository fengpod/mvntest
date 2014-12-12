package thread;

public class WaitTest {
	public static Integer status = 1;
	
	public static void main(String[] args) {
		T1 t1 = new T1();
		T2 t2 = new T2();
		T3 t3 = new T3();
		t1.start();
		t2.start();
		t3.start();
	}
	static class T1 extends Thread{
		public void run(){
			System.out.println(1);
			synchronized (WaitTest.status) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				WaitTest.status = 2;
//				WaitTest.status.notifyAll();
			}
		}
	}
static class T2 extends Thread{
	public void run(){
		System.out.println(2);
		synchronized (WaitTest.status) {
			while(WaitTest.status !=2){
				try {
					WaitTest.status.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WaitTest.status = 2;
			WaitTest.status.notifyAll();
		}
	}
	}
static class T3 extends Thread{
	public void run(){
		System.out.println(3);
		synchronized (WaitTest.status) {
			while(WaitTest.status !=3){
				try {
					WaitTest.status.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WaitTest.status = 3;
		}
	}
}
}
