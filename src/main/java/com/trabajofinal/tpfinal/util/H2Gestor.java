package com.trabajofinal.tpfinal.util;
import org.apache.log4j.Logger;

import java.sql.*;


public class H2Gestor {

    public static final String H2_URL = "jdbc:h2:~/test";


    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String USERNAME="sa";
    private final static String PASSWORD="";

    final static Logger log = Logger.getLogger(H2Gestor.class);


    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection(H2_URL,USERNAME,PASSWORD);
    }


    public  void executeSQL(Connection conn, String sql){
        try {
            Statement statement= conn.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  ResultSet executeQuery(Connection conn, String sql){
        try {
            Statement statement= conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void executeUpdate(PreparedStatement ps){
        try {
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }


}

