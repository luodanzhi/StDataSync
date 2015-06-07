package com.lazy.st.db.jdbc;

import java.util.List;


public interface JdbcBaseDAO {
	/**
	 * 批量插入
	 * @param obj
	 */
	public abstract int[] batchInsert(String sql,Object[][] obj); 
	
	/**
	 * 单条插入，更新，删除
	 * @param sql
	 * @param param
	 */
	public abstract int insertOrUpdateOrDelete(String sql, Object[] param);
	
	/**
	 * 查询
	 * @param sql
	 * @param param
	 * @return
	 */
	public abstract List<? extends Object> query(String sql, Object[] param ,ObjectMapper mppper);
	


}
