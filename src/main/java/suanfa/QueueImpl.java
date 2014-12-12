package suanfa;

import java.util.Stack;

public class QueueImpl {
	private Stack<Integer> s1 = new Stack<Integer>();
	private Stack<Integer> s2 = new Stack<Integer>();//tmp
	
	public void push(int a){
		while(s2.size()>0){
			s1.push(s2.pop());
		}
		s1.push(a);
	}
	public int pop(){
		while(s1.size()>0){
			s2.push(s1.pop());
		}
		return s2.pop();
	}
	public static void main(String[] args) {
		QueueImpl queue = new QueueImpl();
		queue.push(1);
		queue.push(2);
		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}
}
