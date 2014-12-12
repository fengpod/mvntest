package collection;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DescSort {
	public static void main(String[] args) {
		File dict = new File("d:/huanbaofile/");
		File[] files = dict.listFiles();
		List<File> list = new ArrayList<File>(Arrays.asList(files));
		Collections.sort(list);
		for(int i = list.size()-25;i<list.size();i++){
			System.out.println(list.get(i).getName());
		}
	}
}
