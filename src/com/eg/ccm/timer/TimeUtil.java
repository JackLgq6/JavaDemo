package com.eg.ccm.timer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.eg.ccm.entity.Cat;
import com.eg.ccm.entity.CatNest;
import com.eg.ccm.entity.Rat;
import com.eg.ccm.entity.RatNest;
import com.eg.ccm.entity.SmallCat;
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
	
	/**
	 * 1，假设每一窝老鼠有1只成年公鼠，1只成年母鼠。5只小老鼠，且是第一胎。小老鼠1个月了。
	1.老鼠生长三个月成熟，受孕21天生产小老鼠。第一胎5只小老鼠，以后每胎加1只，直到一胎达到15个，以后每胎减少一只，直到一胎产5个时。母鼠停止生育。
	2.老鼠的最大寿命2年6个月，然后自然死亡。
	 * @param ratNest
	 */
	public static void startTime(RatNest ratNest, CatNest catNest) {
		File file = new File("src/rat.txt");
		File file2 = new File("src/cat.txt");
		BufferedWriter writer = null;
		BufferedWriter writer2 = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer2 = new BufferedWriter(new FileWriter(file2));
			String str = "月份" + "\t" + "成年母老鼠数量" + "\t" + "成年公老鼠数量" + "\t" + "小母老鼠数量" + "\t" + "小公老鼠数量" +"\r\n";
			String str_cat = "月份" + "\t" + "成年母猫数量" + "\t" + "成年公猫数量" + "\t" + "小母猫数量" + "\t" + "小公猫数量" +"\r\n";
			writer.write(str);
			writer2.write(str_cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int days = 0;
		int flag = 21;
		int months = 0;
		int maxRatCount = 0;
		List<Rat> femaleRatList = ratNest.getFemaleRatList();
		List<Rat> maleRatList = ratNest.getMaleRatList();
		List<SmallRat> smallMaleRatList = ratNest.getSmallMaleRatList();
		List<SmallRat> smallFemaleRatList = ratNest.getSmallFemaleRatList();
		List<Cat> femaleCatList = catNest.getFemaleCatList();
		List<Cat> maleCatList = catNest.getMaleCatList();
		List<SmallCat> smallFemaleCatList = catNest.getSmallFemaleCatList();
		List<SmallCat> smallMaleCatList = catNest.getSmallMaleCatList();
		Cat cat = new Cat();
		for (;;) {
			//日期加1天
			mCalendar.add(Calendar.DATE, 1);
			days++;
			//猫每5天可以抓到1只老鼠。小猫10个月就可以抓老鼠了。10岁之后不再有抓老鼠的能力。
			if (days >= 5 && days % 5 == 0) {
				int canCatchRatCount = Cat.canCatchRatCount(catNest);
				cat.catchMounse(ratNest, canCatchRatCount);
			}
			//每隔21天成熟母老鼠生小老鼠
			if (days >= 21 && days % flag == 0) {
				//System.out.println("成年母老鼠数量:" + femaleRatList.size());
				for (Rat femaleRat : femaleRatList) {
					int fetus = femaleRat.getFetus(femaleRat);
					femaleRat.bear(ratNest, femaleRat, fetus);
					femaleRat.setFetus(femaleRat, ++fetus);
				}
			}
			
			if (days >= 30 && days % 30 == 0) {
				months++;
				int smallFemaleRatCount = 0;
				int smallMaleRatCount = 0;
				int maleRatCount = 0;
				int femaleRatCount = 0;
				int smallFemaleCatCount = 0;
				int smallMaleCatCount = 0;
				int maleCatCount = 0;
				int femaleCatCount = 0;
				SmallCat smallCat = new SmallCat();
				SmallRat smallRat = new SmallRat();
				//两个月以后每个月检查是否有长大的老鼠
				if (months >= 2) {
					smallRat.afterThreeMonth(ratNest);
				}
				if (months >= 15) {
					smallCat.growUp(catNest);
				}
				if (months >= 10) {
					cat.giveToOthers(catNest);
				}
				if (months >= 10 && months % 10 == 0) {
					for (Cat femaleCat : femaleCatList) {
						cat.bear(catNest, femaleCat);
					}
				}
				if (months >= 30) {
					Rat rat = new Rat();
					rat.dead(ratNest);
				}
				
				for (SmallRat smallFemaleRat : smallFemaleRatList) {
					int age = smallFemaleRat.getAge();
					//System.out.println("小母老鼠数量:" + smallFemaleRat.getCount());
					smallFemaleRatCount += smallFemaleRat.getCount();
					smallFemaleRat.setAge(++age);
				}
				
				for (SmallRat smallMaleRat : smallMaleRatList) {
					int age = smallMaleRat.getAge();
					smallMaleRatCount += smallMaleRat.getCount();
					smallMaleRat.setAge(++age);
				}
				
				for (Rat maleRat : maleRatList) {
					int age = maleRat.getAge();
					maleRatCount += maleRat.getCount();
					maleRat.setAge(++age);
				}
				
				for (Rat femaleRat : femaleRatList) {
					int age = femaleRat.getAge();
					femaleRatCount += femaleRat.getCount();
					femaleRat.setAge(++age);
				}
				if (months == 1) {
					maxRatCount = femaleRatCount + maleRatCount + smallFemaleRatCount + smallMaleRatCount;
				}
				System.out.println("第" + months + "月成年母老鼠数量：" + femaleRatCount + ", " + "成年公老鼠数量：" + maleRatCount + ", "
						+ "每个月小母老鼠数量：" + smallFemaleRatCount + ", " + "每个月小公老鼠数量: " + smallMaleRatCount);
				
				String str1 = "" +months + "\t\t" + femaleRatCount + "\t\t\t" + maleRatCount + "\t\t\t" + smallFemaleRatCount + "\t\t\t" +smallMaleRatCount + "\r\n";
				try {
					writer.write(str1);
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (femaleRatCount == 0 && maleRatCount == 0 && smallFemaleRatCount == 0 && smallMaleCatCount == 0) {
					System.out.println("----------------------------------------------------------");
					System.out.print("|");
					System.out.println("第" + months + "个月, 老鼠被清理干净" + "                                     |");
					System.out.print("|");
					System.out.println("老鼠最多的时候有" + maxRatCount + "只" + "                                        |");
					System.out.print("|");
					System.out.println("只要老鼠>=24只, 猫每个月都可以抓到24只老鼠" + "                     |");
					System.out.println("----------------------------------------------------------");
//					System.out.println("==========================================================");
//					System.out.print("|" + "月份" + "\t" + "成年母老鼠数量" + "\t" + "成年公老鼠数量" + "\t" + "小母老鼠数量" + "\t" + "小公老鼠数量");
//					System.out.println("----------------------------------------------------------");
					return;
				}
				for (Cat maleCat : maleCatList) {
					int age = maleCat.getAge();
					maleCat.setAge(++age);
					maleCatCount += maleCat.getCount();
				}
				for (Cat femaleCat : femaleCatList) {
					int age = femaleCat.getAge();
					femaleCat.setAge(++age);
					femaleCatCount += femaleCat.getCount();
				}
				for (SmallCat smallMaleCat : smallMaleCatList) {
					int age = smallMaleCat.getAge();
					smallMaleCat.setAge(++age);
					smallMaleCatCount += smallMaleCat.getCount();
				}
				for (SmallCat smallFemaleCat : smallFemaleCatList) {
					int age = smallFemaleCat.getAge();
					smallFemaleCat.setAge(++age);
					smallFemaleCatCount += smallFemaleCat.getCount();
				}
				System.out.println("第" + months + "月成年公猫数量：" + maleCatCount + ", 每个月成年母猫数量：" + femaleCatCount + 
						"， 每个月小公猫数量：" + smallMaleCatCount + "， 每个月小母猫数量：" + smallFemaleCatCount);
				String str2 = "" +months + "\t\t" + femaleCatCount + "\t\t\t" + maleCatCount + "\t\t\t" + smallFemaleCatCount + "\t\t\t" +smallMaleCatCount + "\r\n";
				try {
					writer2.write(str2);
					writer2.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (isEnd()) {
				break;
			}
		}
	}
	
	private static boolean isEnd() {
		int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH) + 1;
		int date = mCalendar.get(Calendar.DAY_OF_MONTH);
		if (year == 2023 && month == 12 && date == 31) {
			return true;
		}
		return false;
	}
}
