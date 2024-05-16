package text.orderby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DbOrderBy {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        final String URL = "jdbc:mariadb://localhost:8889/java_db"; // MAMPの場合は"localhost:8889"としてください
        final String USER_NAME = "root";
        final String PASSWORD = "root"; // MAMPの場合は"root"を代入してください
        
        String order;
        System.out.println("0(昇順)か1（降順）を入力してください：");
        try(Scanner scanner = new Scanner(System.in)) {
        	order = scanner.nextInt() == 1 ? "DESC;" : "ASC;";
        } catch(InputMismatchException e) {
        	System.out.println("入力が正しくありません");
        	return;
        }
        
        String sql = "SELECT * FROM users ORDER BY age " + order;
        System.out.println("レコード取得：" + sql);
        
        try(Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        		Statement statement = con.createStatement()) {
        	ResultSet result = statement.executeQuery(sql);
        	
        	while(result.next()) {
        		int id = result.getInt("id");
        		String name = result.getString("name");
        		int age = result.getInt("age");
        		
        		System.out.println(result.getRow() + "件目：id=" + id
        				+ "／name=" + name + "／age=" + age);
        	}
        } catch(SQLException e) {
        	System.out.println("データベース接続失敗" + e.getMessage());
        }
	}

}
