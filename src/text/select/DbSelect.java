package text.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSelect {

	public static void main(String[] args) {
		final String URL = "jdbc:mariadb://localhost:8889/java_db";
		final String USER_NAME = "root";
		final String PASSWORD = "root";
		
		String sql = "SELECT id, name FROM users";
		
		try(Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				Statement statement = con.createStatement()) {
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				
				System.out.println(result.getRow() + "件目：id=" + id + "／name=" + name);
			}
		} catch (SQLException e) {
			System.out.println("データベース接続失敗" + e.getMessage());
		}
	}

}
