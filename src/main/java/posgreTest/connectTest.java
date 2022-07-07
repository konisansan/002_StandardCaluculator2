package posgreTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectTest {

	public static void main(String[] args) {
		// 接続文字列の設定
		String url = "jdbc:postgresql://localhost:5432/testdb";
		String user = "postgres";
		String password = "testpos33";

		// SELECT文の作成・実行
		String sql_1 = "SELECT * FROM public.seasoning ORDER BY id ASC ";

		// PostgreSQLに接続
		try (Connection con = DriverManager.getConnection(url, user, password);
				Statement stmt = con.createStatement();
				ResultSet result = stmt.executeQuery(sql_1);) {

			// 実行結果の取得
			while (result.next()) {
                String col1 = result.getString ( 1 ) ;
                String col2 = result.getString ( 2 ) ;
                String col3 = result.getString ( 3 ) ;
                System.out.println ( col1 + " " + col2 + " " + col3 ) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql_2 = "INSERT INTO public.seasoning VALUES(?,?,?)" ;
		
		try (Connection con = DriverManager.getConnection ( url, user, password ) ;
                PreparedStatement pstmt = con.prepareStatement(sql_2); ) {

            pstmt.setInt(1, 2);
            pstmt.setString(2, "味噌");
            pstmt.setDouble(3, 0.1);

            int count = pstmt.executeUpdate();
            System.out.println( count + "件 登録しました" );

        } catch ( SQLException e ) {
          e.printStackTrace() ;
      }
		
	}

}
