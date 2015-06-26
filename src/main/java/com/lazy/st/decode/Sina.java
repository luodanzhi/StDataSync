package com.lazy.st.decode;

import com.lazy.st.entity.StData;
import com.lazy.st.lib.DateUtil;

public class Sina {

    /** sh,4399.933,4527.779,4377.256,4414.484,4325.514,0,0,1186
86239,172652678401,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2015-06-26,09:53:55,00 */
    /** Date,Open,High,Low,Close,Volume,Adj Close */
    public static StData parseFromStr(String content) {
        String[] strArr = content.split(",");
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StData stData = new StData();
        int index = 1;
        //stData.setDate(DateUtil.parseStrDate(strArr[index++], "yyyy-MM-dd"));
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
