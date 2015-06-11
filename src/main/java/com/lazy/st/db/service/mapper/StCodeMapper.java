package com.lazy.st.db.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lazy.st.db.jdbc.ObjectMapper;
import com.lazy.st.entity.StCode;

public class StCodeMapper implements ObjectMapper {

	public Object mapping(ResultSet rs) {
        try {
        	StCode st = new StCode();
        	st.setCode(rs.getString("code"));
        	st.setName(rs.getString("name"));
        	st.setType(rs.getString("type"));
        	st.setLastDate(rs.getDate("last_date"));

            return st;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

}
