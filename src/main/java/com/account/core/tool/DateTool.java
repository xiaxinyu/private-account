package com.account.core.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.account.core.exception.DateParseException;

/**
 * Created by Summer.Xia on 08/27/2014.
 */
public class DateTool {
	public final static String DF_MM_DD_YYYY = "MM-dd-yyyy";
	public final static String DF_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public final static String DF_YYYYMMDD = "yyyyMMdd";
	public final static String DF_YYYY_MM_DD = "yyyy-MM-dd";
	public final static String DF_YYYY = "yyyy";
	
	public static Date addMonths(Date date, int months) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, months);
			return cal.getTime();
		}
		return null;
	}
	
	public static Date addYears(Date date, int years) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, years);
			return cal.getTime();
		}
		return null;
	}
	
	public static String changeDateToString(Date dateVlue, String strFormat) {
		String strReturnDate = "";
		if (StringTool.isNullOrEmpty(strFormat)) {
			strFormat = "yyyy-MM-dd HH:mm:ss";
		}
		try {
			if(dateVlue!=null){
				strReturnDate = new SimpleDateFormat(strFormat).format(dateVlue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturnDate;
	}
	
	public static Date changeStringToDate(String str,String strFormat) throws DateParseException{
		if(!StringTool.isNullOrEmpty(str)){
			str=str.replaceAll("/", "-");
		}
		if (StringTool.isNullOrEmpty(strFormat)) {
			strFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			throw new DateParseException(e);
		}
		return date;
	}
	
	public static String getMonthCode(String month){
		String result = "";
		if(!StringTool.isNullOrEmpty(month)){
			if("Janunary".equals(month)){
				result = "01";
			}else if("February".equals(month)){
				result = "02";
			}else if("March".equals(month)){
				result = "03";
			}else if("April".equals(month)){
				result = "04";
			}else if("May".equals(month)){
				result = "05";
			}else if("June".equals(month)){
				result = "06";
			}else if("July".equals(month)){
				result = "07";
			}else if("August".equals(month)){
				result = "08";
			}else if("September".equals(month)){
				result = "09";
			}else if("October".equals(month)){
				result = "10";
			}else if("November".equals(month)){
				result = "11";
			}else if("December".equals(month)){
				result = "12";
			}
		}
		return result;
	}
}
