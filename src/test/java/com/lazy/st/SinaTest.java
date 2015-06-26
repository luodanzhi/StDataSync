package com.lazy.st;

import com.lazy.st.lib.HttpUtil;

public class SinaTest {

    /**
     * @param args
     */
    public static void main(String[] args){
        String content = HttpUtil.clientGet("http://hq.sinajs.cn/list=sh000001");
        String[] strArr = content.split(",");
        if (strArr == null || strArr.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("open=").append(strArr[1]).append(",");
        sb.append("yesterday=").append(strArr[2]).append(",");
        sb.append("cur=").append(strArr[3]).append(",");
        sb.append("high=").append(strArr[4]).append(",");
        sb.append("low=").append(strArr[5]).append(",");
        System.out.println(sb.toString());
    }

}
