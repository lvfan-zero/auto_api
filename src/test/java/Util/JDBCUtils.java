package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static final String JDBC_URL = "jdbc:mysql://test.lemonban.com:3306/future?useUnicode=true&characterEncoding=utf-8";
    public static final String JDBC_USER = "test";
    public static final String JDBC_PASSWORD = "test";

    public static Connection getConnection(){

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
