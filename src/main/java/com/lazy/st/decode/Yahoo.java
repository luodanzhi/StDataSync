package com.lazy.st.decode;

import com.lazy.st.entity.StData;
import com.lazy.st.lib.DateUtil;

public class Yahoo {

	/** 2015-05-19,16.37,17.09,16.35,17.04,242251200,17.04 */
	/** Date,Open,High,Low,Close,Volume,Adj Close */
	public static StData parseFromStr(String content) {
		String[] strArr = content.split(",");
		if (strArr == null || strArr.length == 0) {
			return null;
		}
		StData stData = new StData();
		int index = 0;
		stData.setDate(DateUtil.parseStrDate(strArr[index++], "yyyy-MM-dd"));
		stData.setOpen(Double.valueOf(strArr[index++]));
		stData.setHigh(Double.valueOf(strArr[index++]));
		stData.setLow(Double.valueOf(strArr[index++]));
		stData.setClose(Double.valueOf(strArr[index++]));
		stData.setVolume(Double.valueOf(strArr[index++])/1000000);
		
		return stData;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(parseFromStr("2015-05-19,16.37,17.09,16.35,17.04,242251200,17.04"));
	}

}
