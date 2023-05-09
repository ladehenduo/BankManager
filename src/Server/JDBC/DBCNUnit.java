package Server.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCNUnit {
    public static String URL="jdbc:mysql://localhost:3306/bankmanager";
    public static String USER="root";
    public static String PASSWORD="1234";

    private static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 新的加载驱动
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
