package com.lazy.st.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库连接池管理连接  jdbc连接使用
 * @author grind
 *
 */
public class ConnectionManager {
	
    
	private static ConnectionManager manager;
	ComboPooledDataSource dataSource;
	
	//==============
	private static final boolean isPool = true;
    String url = Const.DB_URL;
    String driver = Const.DB_DRIVER_CLSNAME;
    String user = Const.DB_USER;
    String password = Const.DB_PASSWD;
	
	private ConnectionManager(){
	    if (isPool) {
	    	dataSource = new ComboPooledDataSource();
	    } else {
            try{
                Class.forName(driver);
            }catch(ClassNotFoundException e1){
                e1.printStackTrace();
            }

	    }
	}
	
	public static final ConnectionManager getInstance(){  
        if(manager == null){  
            try{  
            	manager = new ConnectionManager();  
            }catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return manager;  
    }  

    public synchronized final Connection getConnection() {    
        try {  
            if (isPool) {
            	return dataSource.getConnection();  
            } else {
                return DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {       
            e.printStackTrace();  
        }  
        return null;  
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Connection con = new ConnectionManager().getConnection();
        try{
            Statement pstmt = con.createStatement();
            String sql = "select * from st_his";
            pstmt.execute(sql);
            ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {
                
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            try{
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
	}

}
