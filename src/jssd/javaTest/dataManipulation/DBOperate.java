package jssd.javaTest.dataManipulation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOperate {
	
	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DBURL = 
			"jdbc:mysql://www.lovelybaobao.cn:3306/pets_store?useSSL=false&encoding=utf8&serverTimezone=UTC";
	public static final String DBUSER = "jssd";
	public static final String DBPASS = "142536";
	
	public static Connection conn = null; 		//数据库连接
	
	public DBOperate() {
		connectDB();
	}
	
//	public static Connection getInstance() {
//		if(conn == null) {
//			connectDB();
//		}
//		return conn;
//	}
	
	//连接数据库
	private static void connectDB() {
		try {
			Class.forName(DBDRIVER);
			System.out.println("驱动连接成功");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("驱动加载失败");
		}
		
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败!");
			e.printStackTrace();
		} 
	}

	//终止数据库连接
	public static void disConnectDB() {
		try {
			conn.close();
			System.out.println("数据库关闭");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接关闭失败");
		}
	}
}

