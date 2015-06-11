package com.lazy.st.db.service;

import java.util.List;

import com.lazy.st.db.service.mapper.StCodeMapper;
import com.lazy.st.entity.StCode;

public class StService extends JdbcService {

	/**
	 * 获取设备列表: 这个数据库是老gps系统的数据库
	 * 如果工程为egps2,则采用新的方法获取数据 getValidDeviceListEgps2()
	 */
	public List<StCode> queryAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("t.code, t.name, t.type, t.last_date ");//获取host_curstatus最新的定位数据 add by bgc 
		sb.append(" from st t ");
		// sb.append("where t.status=1 and t.hostid = h.hostid(+)");

		logger.debug("" + sb.toString());
		long begin = System.currentTimeMillis();
		List<StCode> list = (List<StCode>) baseDaoImp
				.query(sb.toString(), null, new StCodeMapper());
		if (logger.isDebugEnabled()) {
			logger.debug("find StCode, take time(ms):"
					+ (System.currentTimeMillis() - begin));
		}
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<StCode> list = new StService().queryAll();
		for(StCode st:list) {
			logger.info(st);
		}
	}

}
