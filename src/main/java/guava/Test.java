package guava;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;

public class Test {
	public static void main(String[] args) {
		String str = null;
		try {
			if(checkNotNull(str).isEmpty()){
				System.out.println(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(Objects.equal(str, null)){
			System.out.println(2);
		}
		System.out.println(MoreObjects.toStringHelper(Image.class).add("id", 1).add("name","image"));
		List<String> list = Lists.newArrayListWithCapacity(10);
		System.out.println(list.isEmpty());
//		Map<String,String> map = Maps.newHashMap();
		str = " 123sd     f34 ";
		str = CharMatcher.WHITESPACE.trimAndCollapseFrom(str, ' ');
		System.out.println(str);
		str = CharMatcher.DIGIT.retainFrom(str);
		System.out.println(str);
		str = CharMatcher.DIGIT.replaceFrom(str, "*");
		System.out.println(str);
		str = "ABCSDFE_";
		str = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, str);
		System.out.println(str);
		str = "好的";
		byte[] bytes = str.getBytes(Charsets.UTF_8);
		System.out.println(new String(bytes,Charsets.UTF_8));
		LoadingCache<String, Image> images = CacheBuilder.newBuilder().maximumSize(5).expireAfterWrite(10, TimeUnit.SECONDS).build(new CacheLoader<String , Image>(){

			@Override
			public Image load(String key) throws Exception {
				System.out.println(key);
				return new Image(key);
			}
		});
		try {
			images.get("1");
			images.get("1");
			TimeUnit.SECONDS.sleep(11);
			images.get("1");
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class Image{
		public Image(String name){
			this.name = name;
		}
		private int id;
		private String name;
		private int width;
		private int height;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
	}
}
