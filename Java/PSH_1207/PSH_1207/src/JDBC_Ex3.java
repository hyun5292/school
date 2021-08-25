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
			System.out.println("DB ���� �Ϸ�");
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into student (id, name, dept) values('14', '����', '��ǻ�� ����');");
			printTable(stmt);
			stmt.executeUpdate("update student set id = '20' where name = '�質��'");
			printTable(stmt);
		}
		catch(ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		}
		catch(SQLException e) {
			System.out.println("SQL ���� ����");
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