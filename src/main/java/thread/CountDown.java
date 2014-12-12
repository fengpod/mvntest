package thread;

import java.util.concurrent.CountDownLatch;

public class CountDown {
	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(10);
		for(int i = 0;i<10;i++){
			CDTest cdt = new CDTest(cdl);
			cdt.start();
		}
		try {
			cdl.await();
			System.out.println(123);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class CDTest extends Thread {
	
	private CountDownLatch cdl;
	
	public CDTest(CountDownLatch cdl){
		this.cdl = cdl;
	}
	@Override
	public void run() {
		cdl.countDown();
		System.out.println(cdl.getCount());
	}
}
