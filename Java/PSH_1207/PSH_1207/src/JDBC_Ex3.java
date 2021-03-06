import java.io.*;
import java.sql.*;

public class JDBC_Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/sampledb", "root", "1234");
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into student (id, name, dept) values('14', '누구', '컴퓨터 공학');");
			printTable(stmt);
			stmt.executeUpdate("update student set id = '20' where name = '김나연'");
			printTable(stmt);
		}
		catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		}
		catch(SQLException e) {
			System.out.println("SQL 실행 에러");
		}
	}
	private static void printTable(Statement stmt) throws SQLException
	{
		ResultSet srs = stmt.executeQuery("select * from student");
		while(srs.next()) {
			System.out.print(srs.getString("name"));
			System.out.print("\t|\t" + srs.getString("id"));
			System.out.println("\t|\t" + srs.getString("dept"));
			System.out.println("=================================");
		}
	}
}
