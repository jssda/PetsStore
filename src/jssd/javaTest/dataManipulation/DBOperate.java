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
	
	public static Connection conn = null; 		//���ݿ�����
	
	public DBOperate() {
		connectDB();
	}
	
//	public static Connection getInstance() {
//		if(conn == null) {
//			connectDB();
//		}
//		return conn;
//	}
	
	//�������ݿ�
	private static void connectDB() {
		try {
			Class.forName(DBDRIVER);
			System.out.println("�������ӳɹ�");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("��������ʧ��");
		}
		
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ��!");
			e.printStackTrace();
		} 
	}

	//��ֹ���ݿ�����
	public static void disConnectDB() {
		try {
			conn.close();
			System.out.println("���ݿ�ر�");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ����ӹر�ʧ��");
		}
	}
}

