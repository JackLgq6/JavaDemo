package com.eg.ccm.timer;

import java.util.Calendar;

public class TimeUtil {
	
	private Calendar calendar;
	private int year = 2017;
	private int month = 12;
	private int day = 12;

	public void initTime() {
		calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		System.out.println(calendar.get(Calendar.YEAR) + "-" + 
		calendar.get(Calendar.MONTH) + "-" + 
		calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public void startTime() {
		day++;
		calendar.set(year, month, day);
		System.out.println(calendar.get(Calendar.YEAR) + "-" + 
				calendar.get(Calendar.MONTH) + "-" + 
				calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public static void main(String[] args) {
		TimeUtil tu = new TimeUtil();
		tu.initTime();
		tu.startTime();
	}
}
