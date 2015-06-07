package com.lazy.st.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JdbcBaseDAOImpl implements JdbcBaseDAO {
	
	
	public JdbcBaseDAOImpl() {
		
	}

	public int[] batchInsert(String sql, Object[][] obj) {
		
		if(sql == null || obj == null){
			return null;
		}
		int[] result = null;
		Connection conn=ConnectionManager.getInstance().getConnection();
		//SessionFactory factory = getSessionFactory();
		// Session session= super.getSession();
		// SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
		// ConnectionProvider provider = sessionFactoryImplementation.getConnectionProvider();
		//ConnectionProvider prider = C3P0PooledConnection
		//Connection conn = null;
		PreparedStatement pstmt=null;
		try {
	        conn.setAutoCommit(false);
			//conn = provider.getConnection();
			pstmt=conn.prepareStatement(sql);  

			for(int i=0;i<obj.length;i++){
				Object[] o = obj[i];
				for(int m= 0;m<o.length;m++){
					pstmt.setObject(m+1, o[m]);
				}
				pstmt.addBatch();
			}
			result = pstmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return result;
	}

	public int insertOrUpdateOrDelete(String sql, Object[] param) {
		int result = -1;
		if(sql == null){
			return result;
		}
		
		Connection conn=ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);  
			if(param !=null){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = -1;
			}
		}
		return result;
	}

	public List<? extends Object> query(String sql, Object[] param,
			ObjectMapper mppper) {
		if(sql == null){
			return null;
		}
		List list = new ArrayList();
		Connection conn=ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql); 
			if(param!=null){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
			   Object obj = mppper.mapping(rs);
			   list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
 
	public static void main(String[] args) {
		Connection conn=ConnectionManager.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			Statement pstmt=null;
			pstmt=conn.createStatement();
			pstmt.addBatch("UPDATE st_his SET  adj='8'");
			pstmt.addBatch("UPDATE st_his SET  adj='16'");
			
			pstmt.executeBatch();
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("OK");
	}
	
}
