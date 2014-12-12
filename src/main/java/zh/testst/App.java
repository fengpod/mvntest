package zh.testst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.alibaba.fastjson.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{	
	private static final String HISTORY_IMG_URL = "http://misc.home.news.cn/public/images/original";
	private final static char[] HEX_DIGITS =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    public static void main(String[] args) {
		String path = id2path(4466727);
		String file = id2file(4466727);
	}
    static String id2path(int fileId) {
		char[] buff = new char[13];

		buff[0] = '/';
		buff[1] = HEX_DIGITS[(fileId >>> 28) & 0xF];
		buff[2] = HEX_DIGITS[(fileId >>> 24) & 0xF];
		buff[3] = '/';
		buff[4] = HEX_DIGITS[(fileId >>> 20) & 0xF];
		buff[5] = HEX_DIGITS[(fileId >>> 16) & 0xF];
		buff[6] = '/';
		buff[7] = HEX_DIGITS[(fileId >>> 12) & 0xF];
		buff[8] = HEX_DIGITS[(fileId >>> 8) & 0xF];
		buff[9] = '/';
		buff[10] = HEX_DIGITS[(fileId >>> 4) & 0xF];
		buff[11] = HEX_DIGITS[fileId & 0xF];
		buff[12] = '/';
		return new String(buff, 0, 13);
	}
    static String id2file(int fileId) {
		char[] buff = new char[2];

		buff[0] = HEX_DIGITS[(fileId >>> 4) & 0xF];
		buff[1] = HEX_DIGITS[fileId & 0xF];

		return new String(buff, 0, 2);
	}
}
