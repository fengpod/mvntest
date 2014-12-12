package suanfa;

public class ReverseSingleChain {
	static class SingleChain{
		int value;
		SingleChain next;
		public SingleChain(int value,SingleChain next){
			this.next = next;
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		SingleChain chain = new SingleChain(1, null);
		SingleChain chain2 = new SingleChain(3, null);
		SingleChain chain3 = new SingleChain(4, null);
		SingleChain chain4 = new SingleChain(2, null);
		SingleChain chain5 = new SingleChain(5, null);
		chain.next = chain2;
		chain2.next = chain3;
		chain3.next = chain4;
		chain4.next = chain5;
		SingleChain sc = new SingleChain(0,null);
		sc = reverse(chain);
		reversePrint(sc);
	}
	
	/**
	 * 反转单链表
	 * @param chain
	 * @param a
	 * @return
	 */
	public static SingleChain reversePrint(SingleChain chain,int a){
		if(chain == null || chain.next == null){
			return chain;
		}
		SingleChain schain = reversePrint(chain.next,a);
		chain.next.next = chain;
		chain.next = null;
		return schain;
	}
	
	public static SingleChain reverse(SingleChain head){
		if(head == null || head.next == null){
			return head;
		}
		SingleChain reverseHead = null;
		SingleChain curr = head;
		SingleChain pro = null;
		while(curr !=null){
			SingleChain next = curr.next;
			if(next == null){
				reverseHead = curr;
			}
			curr.next = pro;
			pro = curr;
			curr = next;
		}
		return reverseHead;
	}
	
	/**
	 * 倒序打印单链表
	 * @param chain
	 */
	public static void reversePrint(SingleChain chain){
		if(chain!= null){
			System.out.println(chain.value);// asc
			reversePrint(chain.next);
		}
	}
}
