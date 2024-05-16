package text.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbDelete {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        final String URL = "jdbc:mariadb://localhost:8889/java_db"; // MAMPの場合は"localhost:8889"としてください
        final String USER_NAME = "root";
        final String PASSWORD = "root"; // MAMPの場合は"root"を代入してください
        
        String sql = "DELETE FROM users WHERE id = ?;";
        
        int targetId = 6;
        
        try(Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        		PreparedStatement statement = con.prepareStatement(sql)) {
        	
        	statement.setInt(1, targetId);
        	
        	System.out.println("レコード対象：" + statement.toString());
        	int rowCnt = statement.executeUpdate();
        	System.out.println(rowCnt + "件のレコードが削除されました");
        } catch(SQLException e) {
        	System.out.println("データベース接続失敗" + e.getMessage());
        }
	}

}
