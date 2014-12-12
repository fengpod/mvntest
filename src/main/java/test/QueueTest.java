package test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueTest {
	public static void main(String[] args) {
		Queue<String> que = new ConcurrentLinkedQueue<String>();
		que.add("1");
		que.add("2");
		for(String str : que){
			que.remove();
			System.out.println(str);
		}
		System.out.print(que.size());
	}
}
