package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Sapir on 4/5/2017.
 */
public class DB {
    public static final String HOST="10.0.11.49";
    private static boolean f=true;
    public static Connection getConnection() throws SQLException {
        if (f) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            f = false;
        }
        return DriverManager.getConnection("jdbc:oracle:thin:@" + HOST + ":1521/XE", "SYSTEM", "12345");  // Connectionמחזיר אוביקט של
    }
    //decorator - we would like to override close of getConnection


    /* אפשר לדרוס את ה close שלו בדרך הזו אך היא לא דרך טובה כי יש פ הלממש מלא פונקציות וזה ממשק ואם נממש רק את המתודה close אז נהיה חייבים לעשות אותה אבסטרקטית
  לא גמור !!

    public static abstract class SqlConnection implements Connection{

        private Connection conn;

        public void setConn(Connection conn) {
            this.conn = conn;
        }

        @Override
        public void close() throws SQLException {
            conn.close();
            System.out.println("");
        }
    }   */
}