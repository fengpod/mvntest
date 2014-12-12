package commonsio;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.LineIterator;

public class Utils {
	static final String txtFile = "d:/1.txt";
	static final String imgFile = "d:/1.jpg";
	public static void main(String[] args ) throws IOException{
		System.out.println(FilenameUtils.getFullPath(imgFile));
		System.out.println(FilenameUtils.getName(imgFile));
		System.out.println(FilenameUtils.getPrefix(imgFile));
		System.out.println(FilenameUtils.getBaseName(imgFile));
		System.out.println(FilenameUtils.getExtension(imgFile));
		
		File txtfile = FileUtils.getFile(txtFile);
		LineIterator iter = FileUtils.lineIterator(txtfile);
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		iter.close();
		String str = "SEDEFWEsddew";
		String str2 = "SEDEFWEsddeW";
		System.out.println(IOCase.SENSITIVE.checkEquals(str, str2));
	}
}
