package suanfa;

public class MergeSort {
	//a asc,b desc ,merge and sort a 、b
	public static int[] sort(int[] a,int[] b){
		int[] c = new  int[a.length+b.length];
		int currIndex = 0;
		int aIndex = 0;
		int bIndex = b.length-1;
		while(aIndex<a.length && bIndex>=0){
			if(a[aIndex]<b[bIndex] ){
				c[currIndex++]=a[aIndex++];
			}else{
				c[currIndex++]=b[bIndex--];
			}
		}
		while(aIndex < a.length){
			c[currIndex++]=a[aIndex++];
		}
		while(bIndex >=0){
			c[currIndex++]=b[bIndex--];
		}
		return c;
	}
	
	public static void main(String[] args) {
		int[] a = {1,3,5,6,11,23,45};
		int[] b = {46,45,42};
		int[] c = sort(a, b);
		for(int num : c){
			System.out.println(num);
		}
	}
}
