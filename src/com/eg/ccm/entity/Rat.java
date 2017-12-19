package com.eg.ccm.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 第一批公老鼠因为是成年的，所以默认年龄3个月
 * 
 * @author gqliu
 * 
 */
public class Rat implements RatCommonFeature {

	private int age;
	private int count;
	private String sex;
	// 多少胎数
	private int fetus;
	private static Map<Rat, Integer> map = new HashMap<Rat, Integer>();
	private SmallRat maleSmallRat;
	private SmallRat femaleSmallRat;
	
	public Rat() {
	}

	public Rat(int count, int age, String sex) {
		this.count = count;
		this.age = age;
		this.sex = sex;
	}

	/*// 2年3个月后死亡
	public static void dead(SmallRat smallMaleRat) {
		// map.get(key)
		if (smallMaleRat != null) {
			Integer smallMaleCount = map.get(smallMaleRat);
			smallMaleCount = 0;
			smallMaleRat = null;
		}
	}

	// 每次产的一窝老鼠，new 一个对象，然后以HashMap（老鼠对象, 数量); 2年三个月后，从HashMap中将这个对象移除。
	public static void afterThreeMonth(SmallRat smallMaleRat) {
		if (smallMaleRat != null) {
			map.put(smallMaleRat, smallMaleRat.getCount());
		}
		// 开始计算时间，2年3个月后老鼠死亡
	}*/

	public void bear(RatNest ratNest, Rat femaleRat, int fetus) {
		maleSmallRat = new SmallRat();
		maleSmallRat.setSex("male");
		maleSmallRat.setAge(0);
		femaleSmallRat = new SmallRat();
		femaleSmallRat.setFetus(femaleRat, 1);
		femaleSmallRat.setSex("female");
		femaleSmallRat.setAge(0);
		if (femaleRat.getSex().equals("female")) {
			//int taishu = fetus + 4;
			if (fetus >= 1 && fetus <= 11) {
				int maleCount = 0;
				int femaleCount = 0;
				Random r = new Random();
				for (int x = 0; x < femaleRat.getCount() * (fetus + 4); x++) {
					int num = r.nextInt() + 1;
					if (num % 2 == 0) {
						maleSmallRat.setCount(++maleCount);
					} else {
						femaleSmallRat.setCount(++femaleCount);
					}
				}
			}
			if (fetus > 11 && fetus < 22) {
				int maleCount = 0;
				int femaleCount = 0;
				Random r = new Random();
				for (int x = 0; x < femaleRat.getCount() * (26 - fetus); x++) {
					int num = r.nextInt() + 1;
					if (num % 2 == 0) {
						maleSmallRat.setCount(++maleCount);
					} else {
						femaleSmallRat.setCount(++femaleCount);
					}
				}
			}
			List<SmallRat> smallMaleRatList = ratNest.getSmallMaleRatList();
			List<SmallRat> smallFemaleRatList = ratNest.getSmallFemaleRatList();
			smallMaleRatList.add(maleSmallRat);
			smallFemaleRatList.add(femaleSmallRat);
		}
		System.out.println(maleSmallRat.getCount());
		System.out.println(femaleSmallRat.getCount());
		//afterThreeMonth(maleSmallRat);
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public void dead(RatNest ratNest) {
		List<Rat> femaleRatList = ratNest.getFemaleRatList();
		List<Rat> maleRatList = ratNest.getMaleRatList();
		List<SmallRat> smallMaleRatList = ratNest.getSmallMaleRatList();
		List<SmallRat> smallFemaleRatList = ratNest.getSmallFemaleRatList();
		for (SmallRat smallFemaleRat : smallFemaleRatList) {
			if (smallFemaleRat.getAge() >= 23) {
				smallFemaleRatList.remove(smallFemaleRat);
			}
		}
		for (SmallRat smallMaleRat : smallMaleRatList) {
			if (smallMaleRat.getAge() >= 23) {
				smallFemaleRatList.remove(smallMaleRat);
			}
		}
		for (Rat maleRat : maleRatList) {
			if (maleRat.getAge() >= 23) {
				smallFemaleRatList.remove(maleRat);
			}
		}
		for (Rat femaleRat : femaleRatList) {
			if (femaleRat.getAge() >= 23) {
				smallFemaleRatList.remove(femaleRat);
			}
		}
	}

	@Override
	public void afterThreeMonth(Rat rat, SmallRat smallRat) {
		if (smallRat != null && rat != null) {
			rat.setCount(rat.getCount() + smallRat.getCount());
			map.put(rat, rat.getCount());
			System.out.println("----------------------");
			System.out.println(smallRat.getCount());
		}
	}

	public int getFetus(Rat femaleRat) {
		if ("female".equals(femaleRat.getSex())) {
			return fetus;
		}
		return -1;
	}

	public void setFetus(Rat femaleRat, int fetus) {
		if ("female".equals(femaleRat.getSex())) {
			this.fetus = fetus;
		}
	}
	
	
}
