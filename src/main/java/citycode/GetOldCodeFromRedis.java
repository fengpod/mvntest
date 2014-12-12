package citycode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class GetOldCodeFromRedis {
	private static ShardedJedisPool pool = null;
	
	private static  void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(10);
		config.setMaxIdle(10);
		config.setMaxWait(1000);
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		JedisShardInfo info = new JedisShardInfo("localhost", 6387);
		info.setPassword("redistest");
		info.setTimeout(3000);
		list.add(info);
		pool = new ShardedJedisPool(config, list, 1);
	}
	public static void put(Map<String,String> map){
		initPool();
		ShardedJedis jedis = pool.getResource();
		for(String key : map.keySet()){
			jedis.hset("weatherCityCode", key, map.get(key));
		}
		System.out.println(jedis.hlen("weatherCityCode"));
	}
	public static void main(String[] args) throws IOException {
		initPool();
		ShardedJedis jedis = pool.getResource();
//		jedis.del("weatherCityCode");
		System.out.println(jedis.hlen("weatherCityCode"));
		Map<String,String> map = jedis.hgetAll("weatherCityCode");
		for(String key : map.keySet()){
			System.out.println(key+":"+map.get(key));
		}
//		writeFile(map);
		pool.returnResource(jedis);
	}
	private static void writeFile(Map<String,String> map) throws IOException{
		File file = new File("d:/temp/code.txt");
		FileWriter fw = new FileWriter(file);
		for(String key : map.keySet()){
			String code = map.get(key);
			System.out.println(key+":"+code);
			fw.write(key+":"+code+"\r\n");
		}
		fw.flush();
		fw.close();
	}
}
