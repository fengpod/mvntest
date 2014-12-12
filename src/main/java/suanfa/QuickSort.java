package suanfa;

public class QuickSort {
	public static void sort(int[] a,int s,int e){
		int start = s;
		int end = e;
		if(start < end){
			int key = a[start];
			while(start < end){
				while(start < end && a[end]>=key){
					end--;
				}
				a[start]=a[end];
				while(start <end && a[start]<=key){
					start++;
				}
				a[end]=a[start];
			}
			a[start]=key;
			sort(a,s,start-1);
			sort(a,start+1,e);
		}
	}
	public static void main(String[] args) {
		int[] a = {72,14,35,76,24};
		sort(a,0,a.length-1);
		for(int i : a){
			System.out.println(i);
		}
	}
}
