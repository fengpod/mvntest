package historyvisitdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.xh.statistical.util.TimeConvertUtils;

public class ExportHomeData {
 	static JedisPool hdcPool 	= null;
	static int score = TimeConvertUtils.convertTimeInt("2014-08-31");
	static String blogUrl = "jdbc:mysql://192.168.87.52:3306/xh_blog";
	static String username = "xh_blog";
	static String password = "xinhua";
	static int pageSize = 10000;
	static String homeSql = " SELECT u.id, username,visitedcount as viewcount FROM bloguser AS u LEFT JOIN blogblogcounter AS c ON c.id = blogid ORDER BY u.id limit ?,?";
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(5000);
		config.setMaxIdle(1000);
		config.setMaxWait(10000);
		hdcPool = new JedisPool(config, "192.168.86.218", 22122, 10000);
	}
	public static void main(String[] args) throws Exception {
		exportHome();
//		Jedis jedis = hdcPool.getResource();
//		String pvkey = "pv:portal:zhrs110:ptl_index";
//		export(pvkey, 10000, jedis);
	}
	
	
	static void exportHome() throws SQLException{
//		int count = 5887045;
//		
//		int pages = (count+pageSize -1)/pageSize;
//		int taskNum = 50;
//		int offset = pages/taskNum;
//		for(int i = 1;i<=taskNum;i++){
//			int beginPage = (i-1)*offset+1;
//			int endPage = i*offset;
//			HomeTask task = new HomeTask(beginPage, endPage);
//			task.start();
//		}
		HomeTask task = new HomeTask(83, 88);
		task.start();
		HomeTask task2 = new HomeTask(290, 297);
		task2.start();
		HomeTask task3 = new HomeTask(301, 308);
		task3.start();
		HomeTask task4 = new HomeTask(156, 165);
		task4.start();
		HomeTask task5 = new HomeTask(356, 363);
		task5.start();
		HomeTask task6 = new HomeTask(258, 264);
		task6.start();
		HomeTask task7 = new HomeTask(204, 209);
		task7.start();
		HomeTask task8 = new HomeTask(367, 374);
		task8.start();
	}
	private static void export(String pv, Connection conn,
			int beginPage,int endPage,String sql,boolean isIndex) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int offset = 0;
		for(int pageNo=beginPage;pageNo<=endPage;pageNo++){
			System.out.println("pv : "+pv+",beginPage:"+beginPage+",endPage:"+endPage+",pageNo:"+pageNo);
			ps = conn.prepareStatement(sql);
			offset = (pageNo-1)*pageSize;
			ps.setInt(1, offset);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			List<UserData> datas = new ArrayList<UserData>(pageSize);
			while(rs.next()){
				UserData data = new UserData();
				data.setId(rs.getInt(1));
				data.setUserName(rs.getString(2));
				data.setViewCount(rs.getInt(3));
				datas.add(data);
			}
			export(datas, pv,isIndex);
			datas = null;
		}
	}
	static void export(List<UserData> datas ,String pv,boolean isIndex){
		Jedis jedis = hdcPool.getResource();
		for(UserData data : datas){
			String pvkey = "pv:portal:"+data.getUserName()+":"+pv;
			export(pvkey, data.getViewCount(),jedis);
		}
		hdcPool.returnResource(jedis);
	}
	static void export(String pvkey,int value,Jedis jedis){
		String valStr = value+"_"+score;
		jedis.zadd(pvkey, score,valStr);
	}
	
	private static Connection getConnection(String mysqlStr, String mysqlUser, String mysqlPass){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(mysqlStr,mysqlUser,mysqlPass);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	static class HomeTask extends Thread{
		int begin = 0;
		int end = 0;
		public HomeTask(int beginPage,int endPage){
			begin = beginPage;
			end = endPage;
		}
		@Override
		public void run() {
			Connection conn = getConnection(blogUrl, username, password);
			try {
				export("ptl_index", conn,begin,end, homeSql,true);
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" end");
		}
	}
	static class UserData{
		
		private int id;
		private String userName;
		private int viewCount;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public int getViewCount() {
			return viewCount;
		}
		public void setViewCount(int viewCount) {
			this.viewCount = viewCount;
		}
	}
}
