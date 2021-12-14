
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDB {

//	public static void main(String[] args) {
//		Connection conn =ConnDB.getConnection();
//		System.out.println(conn);
//	}
//	
	public static Connection getConnection() {
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Library";//严格区分大小写
	    String username = "sa";
	    String password = "123456";
	    String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//严格区分大小写
	    
	    Connection conn = null;
	    
	  //1.加载驱动
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.获取数据库连接Connection
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeAll(Connection conn,Statement  stmt,ResultSet  rs ) {
		try {
			if(rs !=null) {
				rs.close();
			}
			if(stmt !=null) {
				stmt.close();
			}if(conn !=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
