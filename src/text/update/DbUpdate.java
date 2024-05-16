package text.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUpdate {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        final String URL = "jdbc:mariadb://localhost:8889/java_db"; // MAMPの場合は"localhost:8889"としてください
        final String USER_NAME = "root";
        final String PASSWORD = "root"; // MAMPの場合は"root"を代入してください
        
        String sql = "UPDATE users SET name = ? WHERE id = ?;";
        
        String newName = "武士山花子";
        int targetId = 2;
        
        try(Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        		PreparedStatement statement = con.prepareStatement(sql)) {
        	
        	statement.setString(1, newName);
        	statement.setInt(2, targetId);
        	
        	System.out.println("レコード更新：" + statement.toString());
        	int rowCnt = statement.executeUpdate();
        	System.out.println(rowCnt + "件のレコードが更新されました");
        } catch(SQLException e) {
        	System.out.println("データベース接続失敗" + e.getMessage());
        }
	}

}
