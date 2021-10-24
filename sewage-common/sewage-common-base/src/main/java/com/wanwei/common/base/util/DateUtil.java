package com.wanwei.common.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @Description:
 * @author      qianbaoku
 * @date        2015-3-20
 */
@Slf4j
public class DateUtil {

	
	private static Map<String, DateFormat> FormatterPool = new HashMap<String, DateFormat>();
	/**yyyy-MM-dd*/
	public static final String STANDARD_FORMAT = "yyyy-MM-dd";
	/**yyyy年MM月dd日*/
    public static final String STANDARD_FORMAT_CN = "yyyy年MM月dd日";
    /**yyyy-MM-dd HH:mm:ss*/
	public static final String YMDHMS_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS_FORMAT_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String YMDHMS_FORMAT_STR = "yyyyMMddHHmmss";


    public static Integer  getSecondTimestamp(String dateStr){

    	if(StringUtils.isBlank(dateStr)){
    		return null;
		}
		Date date = parse(dateStr, YMDHMS_FORMAT);
		String timestamp = String.valueOf(date.getTime()/1000);
		return Integer.valueOf(timestamp);

	}
	/**
	 * 字符串格式化日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parse(String dateStr, SimpleDateFormat format) {
		if (dateStr == null) {
			return null;
		}
		try {
			SimpleDateFormat _format = format == null ?  new SimpleDateFormat(STANDARD_FORMAT)
					: format;
			return _format.parse(dateStr);
		} catch (ParseException e) {
			log.warn("", e);
			return null;
		}
	}

	/**
	 * 日期格式化字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, SimpleDateFormat format) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat _format = format == null ? new SimpleDateFormat(STANDARD_FORMAT)
					: format;
			return _format.format(date);
		} catch (Exception e) {
			log.warn("", e);
			return null;
		}

	}

	/**
	 * 字符串格式化日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parse(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}
		try {
			SimpleDateFormat _format = StringUtils.isBlank(format) ? new SimpleDateFormat(STANDARD_FORMAT)
					: new SimpleDateFormat(format);
			return _format.parse(dateStr);
		} catch (ParseException e) {
			log.warn("", e);
			return null;
		}
	}

	/**
	 * 日期格式化字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat _format = StringUtils.isBlank(format) ? new SimpleDateFormat(STANDARD_FORMAT)
					: new SimpleDateFormat(format);
			return _format.format(date);
		} catch (Exception e) {
			log.warn("", e);
			return null;
		}

	}
	/**
	 * 获取n分钟之后的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date addMinutes(Date date, int amount) {

		return date == null ? null : DateUtils.addMinutes(date,amount);
	}
	/**
	 * 获取n天之后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {

		return date == null ? null : DateUtils.addDays(date, days);
	}
	/**
	 * 获取n月之后的日期
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonths(Date date, int months) {
		
		return date == null ? null : DateUtils.addMonths(date, months);
	}
	/**
	 * 获取n周之后的日期
	 * 
	 * @param date
	 * @param weeks
	 * @return
	 */
	public static Date addWeeks(Date date, int weeks) {
		return date == null ? null : DateUtils.addWeeks(date, weeks);
	}
	/**
	 * 时间格式化
	 * 
	 * @param pattern
	 * @param time
	 * @return
	 */
	public static String formatTime(String pattern, Date time) {
		return getFormatter(pattern).format(time);
	}

	/**
	 * 获取格式化formatter
	 * 
	 * @param pattern
	 * @return
	 */
	private static DateFormat getFormatter(String pattern) {
		DateFormat df = FormatterPool.get(pattern);
		if (df == null) {
			df = new SimpleDateFormat(pattern);
			FormatterPool.put(pattern, df);
		}
		return df;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
		return formatTime(pattern, DateUtil.getCurrentTime());
	}

	/**
	 * 格式化字符串 返回标准 时间格式
	 * @param dataString
	 * @return
	 * @see
	 */
	public static String getFormtDataString(String dataString){
		if(StringUtils.isBlank(dataString) || dataString.length() != 14){
			return "";
		}
		return StringUtils.substring(dataString, 0, 4)+"-"+ StringUtils.substring(dataString,4,6)+
				"-"+ StringUtils.substring(dataString,6,8)+" "+ StringUtils.substring(dataString,8,10)+
				":"+ StringUtils.substring(dataString,10,12)+":"+ StringUtils.substring(dataString,12,14);
	}
	/**
	 * 日期格式化 去掉特殊字符
	 * @date 2018年3月17日
	 * @param dataStr
	 * @return
	 * @see
	 */
	public static String dataFormtString(String dataStr){
		if(StringUtils.isBlank(dataStr)){
			return "";
		}
		dataStr =  dataStr.replaceAll("-","").replaceAll(":","").replaceAll(" ","");
		int count = 14 - dataStr.length();
		for(int i = 0;i<count;i++){
			dataStr+="0";
		}
		return dataStr;
	}
	
	/**
	 * 格式化字符串 返回标准 日期格式 date
	 * @param dataString
	 * @return
	 * @see
	 */
	public static Date getFormtStringToDate(String dataString){
		if(StringUtils.isBlank(dataString) || dataString.length() != 14){
			return null;
		}
		return DateUtil.parse(DateUtil.getFormtDataString(dataString), YMDHMS_FORMAT);
	}
	
	public static Date longToDate(Long param) {
		Calendar cld = new GregorianCalendar();
		if (param != null) {
			cld.clear();
			cld.setTimeInMillis(param);
			Date d = cld.getTime();
			return d;
		} else {
			return null;
		}
	}
	/**
	 * 根据开始结束时间计算具体的跨月情况
	 * @author zhouxincheng
	 * @date 2017年11月8日
	 * @param stime开始时间
	 * @param etime结束时间
	 * @return
	 * @throws ParseException
	 * @see
	 */
	@SuppressWarnings("deprecation")
	public  static  List<String> getYYYYMMBetweenStartAndEndTime(Date stime,Date etime) throws ParseException{
		Date d1 = new Date(stime.getTime());
		Date d2 = new Date(etime.getTime());
		d1.setDate(1);
		d2.setDate(1);
		Calendar dd = Calendar.getInstance();//定义日期实例
		dd.setTime(d1);//设置日期起始时间
		List<String> yyyyMM = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		yyyyMM.add(sdf.format(dd.getTime()));
		while(!dd.getTime().after(d2)){//判断是否到结束日期
			String str = sdf.format(dd.getTime());
			if(!yyyyMM.contains(str)){
				yyyyMM.add(str);
			}
			dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
		}
		return yyyyMM;
	}
	
	/**
	 * 根据开始结束时间计算具体的跨月情况
	 * @author chensenjun
	 * @date 2018年03月21日
	 * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
	 * @see
	 */
	   public static int daysBetween(String smdate,String bdate) throws ParseException{  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(sdf.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));     
	    }  
}
