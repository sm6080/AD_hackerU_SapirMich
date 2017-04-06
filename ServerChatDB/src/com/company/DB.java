package com.company;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

    /**
     * Created by Sapir on 4/5/2017.
     */
    public class DB {
        public static final String HOST = "localhost";
        private static boolean f = true;
        public static Connection getConnection() throws SQLException {
            if(f){
                try {
                    Class.forName ("oracle.jdbc.OracleDriver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                f = false;
            }
            Connection conn =  DriverManager.getConnection(
                    "jdbc:oracle:thin:@"+HOST+":1521/XE", "SYSTEM", "12345");
            //SqlConnection conn = new SqlConnection();
            //conn.setConnection(DriverManager.getConnection(
            //                "jdbc:oracle:thin:@"+HOST+":1521/XE", "SYSTEM", "12345"));
            return conn;
        }
}
