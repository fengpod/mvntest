package zh.testst.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.http.client.ClientProtocolException;

public class PushToMblog {
	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException {
		Connection conn = getConn();
		int maxId = getMax();
		PreparedStatement st = conn.prepareStatement("select itemid from push_to_mblog where  id is null order by itemid",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery();
		if(rs.absolute(1)){
			do{
				maxId++;
				String itemId = rs.getString(1);
				update(conn, st, itemId, maxId);
			}while(rs.next());
		}
	}
	public static Connection getConn() throws SQLException{
		String url = "jdbc:mysql://192.168.82.2:3306/xh_photo";
		String username = "xh_blog";
		String passwd="xinhua";
		Connection conn = DriverManager.getConnection(url, username, passwd);
		return conn;
	}
	public static int getMax() throws SQLException{
		Connection conn = getConn();
		String sql = "select max(id) from push_to_mblog";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	public static void update(Connection conn,PreparedStatement ps,String itemId,int id) throws SQLException{
		String sql = "update push_to_mblog set id = ? where itemid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, itemId);
		ps.executeUpdate();
	}
}
