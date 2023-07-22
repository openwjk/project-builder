package com.openwjk.projectbuilder.help;

/**
 * @author junkai.wang
 * @Title: 字符处理类
 * @Description: 字符处理类
 * @date 2023/7/14
 */
public class StringProcHelper {
    public static String keepEnglishChar(String strSource){
        // 去除所有空格，制表符
        strSource = strSource.replaceAll("[\\s*|\t|\r|\n]", "");
        // 字符传小写，去除所有非英文的内容
        String result = strSource.toLowerCase().replaceAll("[^a-z]", "");
        return result;
    }

    /**
     * 首字母大写
     * @param strSource
     * @return
     */
    public static String keepEnglishFirstUpCase(String strSource){
        String result = keepEnglishChar(strSource);
        return (new StringBuilder()).append(Character.toUpperCase(result.charAt(0))).append(result.substring(1)).toString();
    }
}
