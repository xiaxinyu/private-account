package com.account.core;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class StringTool {
    public static final String EMPTY = "";
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

    public static String mergeString(String strValue1, String strValue2, String strSeparator) {
        String result = "";
        if (!isNullOrEmpty(strValue1) && !isNullOrEmpty(strValue2)) {
            result = strValue1.toString() + strSeparator + strValue2.toString();
        } else {
            result = strValue1 + strValue2;
        }
        return result;
    }

    public static boolean compareString(String strValue, String cValue) {
        if (isNullOrEmpty(strValue)) {
            strValue = EMPTY;
        }
        if (isNullOrEmpty(cValue)) {
            cValue = EMPTY;
        }
        return strValue.equalsIgnoreCase(cValue);
    }

    public static String SqlQ(String strTmp) {
        String strT = strTmp;
        strT = strT.replace("'", "''");
        strT = "'" + strT + "'";
        return strT;
    }

    public static String PadLeft(String str, int len, String strFill) {
        String strReturn = str;
        for (int i = 1; i <= len - str.length(); i++) {
            strReturn = strFill + strReturn;
        }
        return strReturn;
    }

    public static String PadRigth(String str, int len, String strFill) {
        String strReturn = str;
        for (int i = 1; i <= len - str.length(); i++) {
            strReturn = strReturn + strFill;
        }
        return strReturn;
    }

    public static String changeObjToString(Object obj) {
        if (obj == null)
            return "";
        return obj.toString();
    }

    public static long changeObjToLong(Object obj) {
        if (obj == null || StringTool.compareString(obj.toString(), ""))
            return 0l;
        double f = Double.parseDouble(obj.toString());
        return (long) f;
    }

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

    public static int changeObjToInt(Object obj) {
        if (obj == null || StringTool.compareString(obj.toString(), ""))
            return 0;
        float f = Float.parseFloat(obj.toString().trim());
        return (int) f;
    }

    public static String generateID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
