package com.lazy.st.db.jdbc;

import java.sql.ResultSet;

public interface ObjectMapper {

	public Object mapping(ResultSet rs);
	
}
