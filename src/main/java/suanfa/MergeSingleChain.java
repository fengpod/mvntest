package suanfa;


public class MergeSingleChain {
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
		SingleChain chain2 = new SingleChain(2, null);
		SingleChain chain3 = new SingleChain(4, null);
		SingleChain chain4 = new SingleChain(9, null);
		SingleChain chain5 = new SingleChain(10, null);
		chain.next = chain2;
		chain2.next = chain3;
		chain3.next = chain4;
		chain4.next = chain5;
		
		SingleChain chain11 = new SingleChain(1, null);
		SingleChain chain12 = new SingleChain(2, null);
		SingleChain chain13 = new SingleChain(4, null);
		SingleChain chain14 = new SingleChain(6, null);
		SingleChain chain15 = new SingleChain(8, null);
		chain11.next = chain12;
		chain12.next = chain13;
		chain13.next = chain14;
		chain14.next = chain15;
		reversePrint(mergeAndSort(chain, chain11));
	}
	
	public static SingleChain mergeAndSort(SingleChain chain1,SingleChain chain2){
		SingleChain chain3 = new SingleChain(0,null);
		if(chain1.value < chain2.value){
			chain3 = new SingleChain(chain1.value,null);
			chain1 = chain1.next;
		}else{
			chain3 = new SingleChain(chain2.value,null);
			chain2 = chain2.next;
		}
		SingleChain head = chain3;
		while(chain1 != null && chain2!=null){
			if(chain1.value < chain2.value){
				chain3.next = chain1;
				chain1 = chain1.next;
			}else{
				chain3.next = chain2;
				chain2 = chain2.next;
			}
			chain3 = chain3.next;
		}
		if(chain1 != null){
			chain3.next = chain1;
		}
		if(chain2 != null){
			chain3.next = chain2;
		}
		return head;
	}
	public static void reversePrint(SingleChain chain){
		if(chain!= null){
			System.out.println(chain.value);// asc
			reversePrint(chain.next);
		}
	}
}
