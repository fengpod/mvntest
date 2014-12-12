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

public class ExportData {
//	static HistoryDatastoreClient hdc = null;
 	static JedisPool hdcPool 	= null;
//	static Jedis   jedis =	null;
	static int score = TimeConvertUtils.convertTimeInt("2014-08-31");
	static String photoUrl = "jdbc:mysql://192.168.82.7:3306/xh_photo";
	static String videoUrl = "jdbc:mysql://192.168.82.7:3306/xh_video";
	static String blogUrl = "jdbc:mysql://192.168.87.52:3306/xh_blog";
	static String username = "xh_blog";
	static String password = "xinhua";
	static int pageSize = 10000;
	static String blogSql = "SELECT a.id AS id,username ,c.readcount AS viewcount  FROM blogarticle AS a LEFT JOIN blogarticlecounter AS c ON c.id=a.id  ORDER BY a.id desc LIMIT ?,?";
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(5000);
		config.setMaxIdle(1000);
		config.setMaxWait(10000);
		hdcPool = new JedisPool(config, "192.168.86.218", 22122, 10000);
//		hdc = new HistoryDatastoreClient(HistoryClientConfig.getInstance());
	}
	public static void main(String[] args) throws Exception {
		//photo data
//		export(photoUrl,"photoimagecloud","ptl_photo_dt");
		//video data
//		export(videoUrl,"videoarticle","ptl_video_dt");
		//blog data
//		exportBlog();
//		exportHome();
//		Jedis jedis = hdcPool.getResource();
//		String key = "pv:portal:thewestlake:ptl_blog_dt:218345841";
//		export(key, 87, jedis);
		Connection conn = getConnection(blogUrl, username, password);
		export("ptl_blog_dt", conn, 1, 1, blogSql, false);
	}
	static void export(String connUrl,String tableName,String pv) throws SQLException{
		Connection conn = getConnection(connUrl, username, password);
		String sql = "select count(1) from "+tableName;
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		int beginPage = 1;
		int endPage = (count+pageSize-1)/pageSize;
		sql = " select id,username,viewcount from "+tableName+" order by id limit ?,?";
		export(pv, conn,beginPage,endPage,sql,false);
		conn.close();
	}
	static void exportBlog() throws SQLException{
		int count = 3416135;
		int pages = (count+pageSize -1)/pageSize;
		int taskNum = 20;
		int offset = pages/taskNum;
		for(int i = 1;i<=taskNum;i++){
			int beginPage = (i-1)*offset+1;
			int endPage = i*offset;
			BlogTask task = new BlogTask(beginPage, endPage);
			task.start();
		}
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
			String pvkey = null;
			if(isIndex){
				pvkey = "pv:portal:"+data.getUserName()+":"+pv;
			}else{
				pvkey = "pv:portal:"+data.getUserName()+":"+pv+":"+data.getId();
			}
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
	static class BlogTask extends Thread{
		int begin = 0;
		int end = 0;
		public BlogTask(int beginPage,int endPage){
			begin = beginPage;
			end = endPage;
		}
		@Override
		public void run() {
			Connection conn = getConnection(blogUrl, username, password);
			try {
				export("ptl_blog_dt", conn,begin,end, blogSql,false);
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
