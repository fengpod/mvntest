package pysort;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class GetPy {
	
	private static HanyuPinyinOutputFormat format;
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		format = getFormat();
		String str = "好";
		System.out.println(str.matches("[\\u4E00-\\u9FA5]+"));
		str = "1";
		System.out.println(str.matches("[a-z,A-Z]+"));
		String[] array = PinyinHelper.toHanyuPinyinStringArray(str.toCharArray()[0], format);
		out(array);
	}
	private static void out(String[] array) {
		for(String str : array){
			System.out.println(str);
		}
	}
	private static HanyuPinyinOutputFormat getFormat() {
		HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
		// 输出设置，大小写，音标方式等
		hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		return hanYuPinOutputFormat;
	}
}
