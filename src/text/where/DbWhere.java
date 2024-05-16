package text.where;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbWhere {

	public static void main(String[] args) {
		
        final String URL = "jdbc:mariadb://localhost:8889/java_db"; // MAMPの場合は"localhost:8889"としてください
        final String USER_NAME = "root";
        final String PASSWORD = "root";
        
        String sql = "SELECT * FROM users WHERE age >= ?;";
        
        int minAge = 25;
        
        try(Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        		PreparedStatement statement = con.prepareStatement(sql)) {
        	
        	statement.setInt(1, minAge);
        	
        	ResultSet result = statement.executeQuery();
        	
        	while(result.next()) {
        		int id = result.getInt("id");
        		String name = result.getString("name");
        		int age = result.getInt("age");
        		
        		System.out.println(result.getRow() + "件目：id＝" + id + "／name＝" + name + "／age＝" + age);
        	}
        } catch (SQLException e) {
        	System.out.println("データベース接続失敗" + e.getMessage());
        }
	}

}
