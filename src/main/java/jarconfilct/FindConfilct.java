package jarconfilct;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FindConfilct {
	private static Map<String, HashSet<String>> getClassMap(String dir)
			throws IOException {
		File file = new File(dir);
		Map<String, HashSet<String>> jarMap = new HashMap<String, HashSet<String>>();
		if (null != file && file.exists() && file.isDirectory()) {
			File[] jarFile = file.listFiles();
			for (File f : jarFile) {
				if (f.isFile() && f.getName().endsWith(".jar")) {
					try {
						JarFile jar = new JarFile(f);
						Enumeration<JarEntry> enumJar = jar.entries();
						while (enumJar.hasMoreElements()) {
							JarEntry je = enumJar.nextElement();
							if (je.getName().endsWith(".class")) {
								if (jarMap.containsKey(je.getName())) {
									jarMap.get(je.getName()).add(f.getName());
								} else {
									HashSet<String> set = new HashSet<String>();
									set.add(f.getName());
									jarMap.put(je.getName(), set);
								}
							}
						}
					} finally {

					}
				}
			}
		}
		return jarMap;
	}

	private static void showConflict(String dic) throws IOException {
		Map<String, HashSet<String>> jarMap = getClassMap(dic);
		Set<String> keys = jarMap.keySet();
		for (String s : keys) {
			if (jarMap.get(s).size() > 1) {
				// 存在重复
				HashSet<String> hsfile = jarMap.get(s);
				String sfiles = "";
				for (String sf : hsfile) {
					sfiles = sfiles + sf + ",";
				}
				if (sfiles.length() > 1) {
					sfiles = sfiles.substring(0, sfiles.length() - 1);
				}
				System.out.println(sfiles + "有重复类：" + s);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		showConflict("D:\\workplace3\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\newXHPortal\\WEB-INF\\lib");
	}
}
