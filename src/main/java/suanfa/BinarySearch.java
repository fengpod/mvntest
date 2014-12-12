package suanfa;

public class BinarySearch {
	public static int search(int[] a,int val){
		int start = 0;
		int end = a.length-1;
		int index = 0;
		while(start <= end){
			index = start + (end - start)/2;
			if(val == a[index]){
				return index;
			}
			if(val < a[index]){
				end = index-1;
			}else{
				start = index+1;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] a = {1,3,12,36,47,68,71,73,77,88,99};
		System.out.println(search(a, 99));
	}
}
