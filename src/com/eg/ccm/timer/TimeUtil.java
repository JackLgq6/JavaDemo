package com.eg.ccm.timer;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.eg.ccm.entity.Rat;
import com.eg.ccm.entity.RatNest;
import com.eg.ccm.entity.SmallRat;

public class TimeUtil {
	
	private static final Calendar mCalendar = Calendar.getInstance();
	
	public static void setDate(int year, int month, int day) {
		mCalendar.set(Calendar.YEAR, year);
		mCalendar.set(Calendar.MONTH, month);
		mCalendar.set(Calendar.DAY_OF_MONTH, day);
	}
	
	public static int getYear() {
		int year = mCalendar.get(Calendar.YEAR);
		return year;
	}
	
	public static int getMonth() {
		int month = mCalendar.get(Calendar.MONTH);
		return month;
	}
	
	/*public void initTime() {
		calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		System.out.println(calendar.get(Calendar.YEAR) + "-" + 
		calendar.get(Calendar.MONTH + 1) + "-" + 
		calendar.get(Calendar.DAY_OF_MONTH));
	}
	*/
	/**
	 * 1，假设每一窝老鼠有1只成年公鼠，1只成年母鼠。5只小老鼠，且是第一胎。小老鼠1个月了。
	1.老鼠生长三个月成熟，受孕21天生产小老鼠。第一胎5只小老鼠，以后每胎加1只，直到一胎达到15个，以后每胎减少一只，直到一胎产5个时。母鼠停止生育。
	2.老鼠的最大寿命2年6个月，然后自然死亡。
	 * @param ratNest
	 */
	public static void startTime(RatNest ratNest) {
		/*int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH) + 1;
		int day = mCalendar.get(Calendar.DAY_OF_MONTH);
		//mCalendar.set(Calendar.DAY_OF_MONTH, ++day);
		System.out.println(year + "-" + month + "-" + day);*/
		int days = 0;
		int flag = 21;
		int monthFlag = 27;
		int months = 0;
		List<Rat> femaleRatList = ratNest.getFemaleRatList();
		List<Rat> maleRatList = ratNest.getMaleRatList();
		List<SmallRat> smallMaleRatList = ratNest.getSmallMaleRatList();
		List<SmallRat> smallFemaleRatList = ratNest.getSmallFemaleRatList();
		//tu.setDate(2017, 11, 11);
		for (;;) {
			//日期加1天
			mCalendar.add(Calendar.DATE, 1);
			days++;
			//每隔21天成熟母老鼠生小老鼠
			if (days >= 21 && days % flag == 0) {
				/*int year = mCalendar.get(Calendar.YEAR);
				int month = mCalendar.get(Calendar.MONTH) + 1;
				int date = mCalendar.get(Calendar.DAY_OF_MONTH);
				System.out.println(year + "-" + month + "-" + date);*/
				for (Rat femaleRat : femaleRatList) {
					femaleRat.bear(ratNest, femaleRat, femaleRat.getFetus(femaleRat));
				}
				for (SmallRat smallFemaleRat : smallFemaleRatList) {
					if (smallFemaleRat.getSex().equals("female") && smallFemaleRat.getAge() >= 3) {
						smallFemaleRat.bear(ratNest, smallFemaleRat, smallFemaleRat.getFetus(smallFemaleRat));
						int fetus = smallFemaleRat.getFetus(smallFemaleRat);
						smallFemaleRat.setFetus(smallFemaleRat, ++fetus);
					}
				}
			}
			if (days >= 30 && days % 30 == 0) {
				months++;
				int smallFemaleRatCount = 0;
				for (SmallRat smallFemaleRat : smallFemaleRatList) {
					int age = smallFemaleRat.getAge();
					smallFemaleRat.setAge(++age);
					smallFemaleRatCount += smallFemaleRat.getCount();
				}
				for (SmallRat smallMaleRat : smallMaleRatList) {
					int age = smallMaleRat.getAge();
					smallMaleRat.setAge(++age);
				}
				for (Rat maleRat : maleRatList) {
					int age = maleRat.getAge();
					maleRat.setAge(++age);
				}
				for (Rat femaleRat : femaleRatList) {
					int age = femaleRat.getAge();
					femaleRat.setAge(++age);
				}
				System.out.println("每个月成年母老鼠数量：" + );
			}
			if (months >= 27 && months % monthFlag == 0) {
				Rat rat = new Rat();
				rat.dead(ratNest);
			}
			if (isEnd()) {
				break;
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		TimeUtil tu = new TimeUtil();
		/*for (;;) {
//			mCalendar.set(Calendar.DAY_OF_MONTH, ++day); 为什么这样就不行？
			//日期加1天
			mCalendar.add(Calendar.DATE, 1);
			int year = mCalendar.get(Calendar.YEAR);
			int month = mCalendar.get(Calendar.MONTH) + 1;
			int date = mCalendar.get(Calendar.DAY_OF_MONTH);
			System.out.println(year + "-" + month + "-" + date);
			System.out.println("-----------------------------------------------");
			if (year == 2018 && month == 10 && date == 20) {
				System.out.println(year + "-" + month + "-" + date);
				break;
			}
			if (isEnd()) {
				break;
			}
		}*/
		
		
	}

	private static boolean isEnd() {
		int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH) + 1;
		int date = mCalendar.get(Calendar.DAY_OF_MONTH);
		if (year == 2030 && month == 12 && date == 31) {
			return true;
		}
		return false;
	}
}
