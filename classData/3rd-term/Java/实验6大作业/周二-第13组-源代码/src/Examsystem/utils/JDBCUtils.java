package Examsystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection connection;
    private static String url = "jdbc:mysql://47.94.45.181:3306/sansuiban?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "123456";
    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch ( ClassNotFoundException exception )
        {
            System.out.println( "ClassNotFoundException " + exception.getMessage( ) );
        }
    }
    public static void beginConnect() {
        try {
            connection = DriverManager.getConnection(url, username, password);//连接数据库
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
