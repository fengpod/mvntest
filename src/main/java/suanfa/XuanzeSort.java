package suanfa;

import java.util.Arrays;


public class XuanzeSort {
	public static void main(String[] args) {
		int[] array = {2,6,4,567,12,78,98,21,13,66,2};
//		xuanzeSort(array);
//		insertSort(array);
//		hebingSort(array);
		partition(array,0,array.length-1);
		printArray(array);
		
	}
	
	private static void partition(int[] array,int low,int height){
		if(height > low){
		int midIndex = (height-low)/2;
		int mid = array[midIndex];
		int l = low;
		int h = height;
		int m = midIndex;
		int lowIndex = -1;
		int hiIndex = -1;
		while(low<midIndex && height>midIndex){
			if(array[low]<mid){
				low++;
			}else{
				lowIndex = low;
			}
			if(array[height]>mid){
				height--;
			}else{
				hiIndex = height;
			}
			if(lowIndex != -1 && hiIndex != -1){
				array[lowIndex] = array[lowIndex]+array[hiIndex];
				array[hiIndex] = array[lowIndex] - array[hiIndex];
				array[lowIndex] = array[lowIndex] - array[hiIndex];
				lowIndex = -1;
				hiIndex = -1;
			}
		}
		if(low<midIndex){
			partition(array,low,midIndex);
		}
		if(height>midIndex){
			partition(array,midIndex,height);
		}
		partition(array,l,m);
		partition(array,m,h);
		}
	}
	
	private static void hebingSort(int[] array){
		int length = array.length;
		int middle = length/2;
		if(length > 1){
			int[] left = Arrays.copyOfRange(array, 0, middle);
			int[] right = Arrays.copyOfRange(array, middle, length);
			hebingSort(left);
			hebingSort(right);
			mergeSort(array,left,right);
		}
	}
	private static void mergeSort(int[] array,int[] left,int[] right){
		int i = 0,l = 0,r= 0;
		while(l<left.length && r < right.length){
			if(left[l] < right[r]){
				array[i++] = left[l++];
			}else{
				array[i++] = right[r++];
			}
		}
		while(r < right.length){
			array[i++] = right[r++];
		}
		while(l < left.length){
			array[i++] = left[l++];
		}
	}
	private static void insertSort(int[] array){
		for(int i = 1;i<array.length;i++){
			for(int j = i;j>0;j--){
				if(array[j]<array[j-1]){
					int tmp = array[j];
					array[j] = array[j-1];
					array[j-1] = tmp;
				}else{
					break;
				}
			}
		}
	}
	private static void xuanzeSort(int[] array) {
		for(int i = 0;i<array.length;i++){
			int current = array[i];
			int minIndex = i;
			for(int j = i+1;j<array.length;j++){
				if(array[minIndex] > array[j]){
					minIndex = j;
				}
			}
			if(current  > array[minIndex]){
				array[i] = array[minIndex];
				array[minIndex] = current;
			}
		}
	}
	private static void printArray(int[] a){
		for(int i : a){
			System.out.println(i);
		}
	}
}
