
package com.core;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.util.Properties;

public class ConnDB
{   
public Connection conn;
public Statement stmt;
public ResultSet rs;
private static String user = null;
private static String password = null;
private static String dbName = null;
private static Properties p = new Properties();


    public ConnDB()
    {
        conn = null;
        stmt = null;
        rs = null;
        
      
            user = "root".toString();
            password = "root".toString();
            dbName = "db_library".toString();
            
    
    }

    public Connection getConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection((new StringBuilder("jdbc:mysql://localhost/")).append(dbName).toString(), user, password);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        return conn;
    }

    public ResultSet executeQuery(String sql)
    {
        try
        {
            conn = getConnection();
            stmt = conn.createStatement(1004, 1007);
            rs = stmt.executeQuery(sql);
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    public int executeUpdate(String sql)
    {
        int result = 0;
        try
        {
            conn = getConnection();
            stmt = conn.createStatement(1004, 1007);
            result = stmt.executeUpdate(sql);
        }
        catch(SQLException ex)
        {
            result = 0;
        }
        return result;
    }

    public void close()
    {
        try
        {
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }


}