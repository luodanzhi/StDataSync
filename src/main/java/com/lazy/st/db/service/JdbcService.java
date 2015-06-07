package com.lazy.st.db.service;

import org.apache.log4j.Logger;

import com.lazy.st.db.jdbc.JdbcBaseDAO;
import com.lazy.st.db.jdbc.JdbcBaseDAOImpl;


/** 
 * 数据库表并不是很多， 所以直接调用jdbc保存
 * @author grind
 *
 */
public class JdbcService {
	private static final Logger logger = Logger
			.getLogger(JdbcService.class);

	// 数据库操作对象
	protected static JdbcBaseDAO baseDaoImp = null;
	
	public JdbcService() {
		baseDaoImp = new JdbcBaseDAOImpl();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
