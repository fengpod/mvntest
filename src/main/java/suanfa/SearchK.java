package suanfa;


public class SearchK {
	static class SingleChain{
		int value;
		SingleChain next;
		public SingleChain(int value,SingleChain next){
			this.next = next;
			this.value = value;
		}
	}
	
	public static int getK(int k,SingleChain chain){
		int cursor = 0;
		SingleChain head = chain;
		while(cursor < k){
			chain = chain.next;
			cursor++;
		}
		while(chain !=null){
			head = head.next;
			chain = chain.next;
		}
		return head.value;
	}
	public static void main(String[] args) {
		SingleChain chain = new SingleChain(1,null);
		SingleChain chain2 = new SingleChain(2,chain);
		SingleChain chain3 = new SingleChain(3,chain2);
		SingleChain chain4 = new SingleChain(4,chain3);
		System.out.println(getK(1, chain4));
	}
}
