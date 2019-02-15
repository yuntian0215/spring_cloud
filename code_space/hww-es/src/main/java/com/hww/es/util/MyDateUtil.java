package com.hww.es.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {
	/**
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 * @throws ParseException
	 * 根据2个字符串时间yyyy-MM-dd算天数差
	 */
	public static Integer DateDifference(String day1,String day2) throws ParseException {
        //时间转换类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(day1);
        Date date2 = sdf.parse(day2);
        //将转换的两个时间对象转换成Calendard对象
        Calendar can1 = Calendar.getInstance();
        can1.setTime(date1);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(date2);
        //拿出两个年份
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);
        //天数
        int days = 0;
        Calendar can = null;
        //如果can1 < can2
        //减去小的时间在这一年已经过了的天数
        //加上大的时间已过的天数
        if(can1.before(can2)){
            days -= can1.get(Calendar.DAY_OF_YEAR);
            days += can2.get(Calendar.DAY_OF_YEAR);
            can = can1;
        }else{
            days -= can2.get(Calendar.DAY_OF_YEAR);
            days += can1.get(Calendar.DAY_OF_YEAR);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2-year1); i++) {
            //获取小的时间当前年的总天数
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            //再计算下一年。
            can.add(Calendar.YEAR, 1);
        }
        return days;
    }
	
	//得到明天的日期，入参yyyy-MM-dd
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}
	
	public static String getDate(String dateRange) {
		Integer num = null;
		switch (dateRange) {
			case "now":
				num = 0;
				break;
			case "now-1d":
				num = -1;
				break;
			case "now-7d":
				num = -7;
				break;
			case "now-30d":
				num = -30;
				break;
			case "now-1y":
				num = -365;
				break;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(calendar.getTime()).toString());
		return sdf.format(calendar.getTime()).toString();
	}
	

	public static String getHourTime(int number) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, number);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(calendar.getTime()).toString());
		return sdf.format(calendar.getTime()).toString();
	}
	
	public static void main(String[]  aaa) {
		getHourTime(-1);
	}
}
