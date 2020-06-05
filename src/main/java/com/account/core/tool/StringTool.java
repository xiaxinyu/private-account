package com.account.core.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Summer.Xia on 08/27/2014.
 */
@Slf4j
public class StringTool {
    /**
     * empty character
     */
    public static final String EMPTY = "";
    /**
     * string is null
     */
    public static final String NULL = null;

    public static String cleanStr(String strValue) {
        if (StringUtils.isBlank(strValue)) {
            return "";
        }

        String value = strValue.replaceAll("\"", "");
        value = value.replaceAll("\\(", "");
        value = value.replaceAll("\\)", "");
        value = StringUtils.trim(value);
        if (StringUtils.isBlank(value)) {
            return "";
        }

        return value;
    }

    /**
     * 判断字符串是否为空
     *
     * @param strValue
     * @return
     */
    public static boolean isNullOrEmpty(String strValue) {
        boolean boolFlag = false;
        if (strValue == null) {
            boolFlag = true;
        } else {
            strValue = strValue.trim();
            if (strValue.equals("") || strValue.length() == 0) {
                boolFlag = true;
            }
        }
        return boolFlag;
    }

    /**
     * 将两个字符串按分隔符凭借
     *
     * @param strValue1    字符串1
     * @param strValue2    字符串2
     * @param strSeparator 分隔符
     * @return
     */
    public static String mergeString(String strValue1, String strValue2, String strSeparator) {
        String result = "";
        if (!isNullOrEmpty(strValue1) && !isNullOrEmpty(strValue2)) {
            result = strValue1.toString() + strSeparator + strValue2.toString();
        } else {
            result = strValue1 + strValue2;
        }
        return result;
    }

    /**
     * 判断2个字符串内容是否相等
     *
     * @param strValue
     * @param cValue
     * @return
     */
    public static boolean compareString(String strValue, String cValue) {
        if (isNullOrEmpty(strValue)) {
            strValue = EMPTY;
        }
        if (isNullOrEmpty(cValue)) {
            cValue = EMPTY;
        }
        return strValue.equalsIgnoreCase(cValue);
    }

    /**
     * 给字符串添加 '' 主要用于SQL语句的拼接
     *
     * @param strTmp
     * @return
     */
    public static String SqlQ(String strTmp) {
        String strT = strTmp;
        strT = strT.replace("'", "''");
        strT = "'" + strT + "'";
        return strT;

    }

    /**
     * 右对齐此实例中的字符，在左边用指定的 Unicode 字符填充以达到指定的总长度。
     *
     * @param str     初始字符串
     * @param len     结果字符串中的字符数，等于原始字符数加上任何其他填充字符。
     * @param strFill Unicode 填充字符。
     * @return 返回新的字符串
     */
    public static String PadLeft(String str, int len, String strFill) {
        String strReturn = str;
        for (int i = 1; i <= len - str.length(); i++) {
            strReturn = strFill + strReturn;
        }

        return strReturn;
    }

    /**
     * 左对齐此实例中的字符，在右边用指定的 Unicode 字符填充以达到指定的总长度。
     *
     * @param str     初始字符串
     * @param len     结果字符串中的字符数，等于原始字符数加上任何其他填充字符。
     * @param strFill Unicode 填充字符。
     * @return 返回新的字符串
     */
    public static String PadRigth(String str, int len, String strFill) {
        String strReturn = str;
        for (int i = 1; i <= len - str.length(); i++) {
            strReturn = strReturn + strFill;
        }

        return strReturn;
    }

    /**
     * 将object对象转换成string
     *
     * @param obj
     * @return
     */
    public static String changeObjToString(Object obj) {
        if (obj == null)
            return "";

        return obj.toString();
    }


    /**
     * 将object对象转换成long
     *
     * @param obj
     * @return
     */
    public static long changeObjToLong(Object obj) {
        if (obj == null || StringTool.compareString(obj.toString(), ""))
            return 0l;

        double f = Double.parseDouble(obj.toString());

        return (long) f;
    }

    /**
     * 将object对象转换成double
     *
     * @param obj
     * @return
     */
    public static Double changeObjToDouble(String obj) {
        String value = obj;
        if (StringUtils.isBlank(value)) {
            return 0.0;
        }
        value = value.replaceAll("\"", "");
        value = StringUtils.trim(value);
        if (StringUtils.isBlank(value)) {
            return 0.0;
        }
        double f = Double.parseDouble(value);
        return f;
    }

    /**
     * 将object对象转换成int
     *
     * @param obj
     * @return
     */
    public static int changeObjToInt(Object obj) {
        if (obj == null || StringTool.compareString(obj.toString(), ""))
            return 0;

        float f = Float.parseFloat(obj.toString().trim());

        return (int) f;
    }

    /**
     * String转成HTML
     *
     * @param strOriginal
     * @return
     */
    public static String parseForHtml(String strOriginal) {
        return parseForHtml(strOriginal, false);
    }

    /**
     * String转成HTML
     *
     * @param strOriginal
     * @param isNullReplaceDoubleLine
     * @return
     */
    public static String parseForHtml(String strOriginal, boolean isNullReplaceDoubleLine) {
        strOriginal = strOriginal.replace("<", "&lt;");//替换<
        strOriginal = strOriginal.replace(">", "&gt;");//替换>
        strOriginal = strOriginal.replace("\r\n", "<br/>");//替换回车
        strOriginal = strOriginal.replace(" ", "&nbsp;");//替换空格
        if (strOriginal == "" && isNullReplaceDoubleLine)
            strOriginal += "--";
        return strOriginal;
    }

    /**
     * HTML转成String
     *
     * @param strOriginal
     * @return
     */
    public static String parseHtmlForString(String strOriginal) {
        return parseHtmlForString(strOriginal, false);
    }

    /**
     * HTML转成String
     *
     * @param strOriginal
     * @param isNullReplaceDoubleLine
     * @return
     */
    public static String parseHtmlForString(String strOriginal, boolean isNullReplaceDoubleLine) {
        strOriginal = strOriginal.replace("&lt;", "<");//替换<
        strOriginal = strOriginal.replace("&gt;", ">");//替换>
        strOriginal = strOriginal.replace("<br/>", "\r\n");//替换回车
        strOriginal = strOriginal.replace("&nbsp;", " ");//替换空格
        if (strOriginal == "" && isNullReplaceDoubleLine)
            strOriginal += "--";
        return strOriginal;
    }

    /**
     * 用替换Windows中的文件名中不支持的符号(\ / : * ? " < > |)
     *
     * @param fileName
     * @return
     */
    public static String sfReplaceFileNameSymol(String fileName) {
        String strRet = fileName;
        strRet = strRet.replace("\\", " ");
        strRet = strRet.replace("/", " ");
        strRet = strRet.replace(":", "：");
        strRet = strRet.replace("*", "*");
        strRet = strRet.replace("?", "？");
        strRet = strRet.replace("<", "＜");
        strRet = strRet.replace(">", "＞");
        strRet = strRet.replace("|", "｜");
        strRet = strRet.replace("&", "");
        strRet = strRet.replace("'", "‘");

        return strRet;
    }

    /**
     * generate unique id
     *
     * @return String
     */
    public static String generateID() {
        return DateTool.changeDateToString(new Date(), "yyyyMMddHHmmss")
                + UUID.randomUUID().toString().replace("-", "");
    }
}
