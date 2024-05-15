package text.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {

	public static void main(String[] args) {
		
		final String URL = "jdbc:mariadb://localhost:8889/java_db";
		final String USER_NAME = "root";
		final String PASSWORD = "root";
		
		String sql = """
				CREATE TABLE users (
					id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
					name VARCHAR(60) NOT NULL,
					age INT(11)
				);
				""";
		
		// データベースに接続（try-with-resources文を使用）
		try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			Statement statement = con.createStatement()) {
			
			int rowCnt = statement.executeUpdate(sql);
			System.out.println("テーブルを作成：rowCnt=" + rowCnt);
			
		} catch (SQLException e) {
			System.out.println("データベース接続失敗" + e.getMessage());
		}
	}
}