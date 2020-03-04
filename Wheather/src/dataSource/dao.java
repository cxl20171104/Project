package dataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dao {
	
	private transient Connection conn;

	public Connection openConn(){
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dao.properties"); 
			//在src下寻找配置文件
			  System.out.println(inputStream);
			  Properties p = new Properties();    
			  try {    
			   p.load(inputStream);    
			  } catch (IOException e1) {    
			   e1.printStackTrace();    
			  }    
			  String driverName = p.getProperty("jdbc.driverClassName");
			  System.out.println(driverName);
			  String url = p.getProperty("jdbc.url");
			  System.out.println(url);
			  String user = p.getProperty("jdbc.username");
			  System.out.println(user);
			  String pwd = p.getProperty("jdbc.password");
			  System.out.println(pwd);
			  Class.forName(driverName); 
			  conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(Connection conn) {
		try {
			if (conn.isClosed()) {
			} else {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		dao d=new dao();
		d.openConn();
	}
}
