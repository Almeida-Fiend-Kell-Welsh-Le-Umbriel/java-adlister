import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {

    static String url = "jdbc:mysql://localhost:3306/codeup_test_db";
    static String username = "root";
    static String password = "codeup";
    static Connection con;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            makeCon();
    }

    public static Connection makeCon() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,username,password);
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO albums (artist, album_name, release_date, genre, sales) VALUES('2Aceqw3 of Base', '2Pyqwra23mid', 2001, '2P23op', 14.5)");
        return con;
    }

}
