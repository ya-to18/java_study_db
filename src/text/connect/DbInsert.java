package text.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbInsert {

	public static void main(String[] args) {
		// データベース接続情報
		final String URL = "jdbc:mariadb://localhost:8889/java_db";
		final String USER_NAME = "root";
		final String PASSWORD = "root";
		
		// INSERT文のフォーマット
		String sql = "INSERT INTO users (name, age) VALUES (?, ?)";
		
		// ユーザーの氏名リスト
		String[] names = { "侍太郎", "侍花子", "侍二郎", "侍寺子", "侍三郎" };
		// ユーザーの年齢リスト
		int[] ages = { 28, 24, 26, 37, 21 };
		
		// データベース接続　＆　SQL文の送信準備
		try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				PreparedStatement statement = con.prepareStatement(sql)) {
			
			// リストの1行目から順番に読み込む
			int rowCnt = 0;
			for(int i = 0; i < names.length; i++) {
				// SQL文の「？」部分をリストのデータに置き換え
				statement.setString(1, names[i]);
				statement.setInt(2, ages[i]);
				
				// SQL文を実行（DBMSに送信）
				System.out.println("レコード追加" + statement.toString());
				rowCnt += statement.executeUpdate();
			}
			
			System.out.println(rowCnt + "件のレコードが追加されました");
		} catch (SQLException e) {
			System.out.println("データベース接続失敗" + e.getMessage());
		}
	}
}