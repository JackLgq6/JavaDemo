package com.eg.ccm.entity;

import java.util.HashMap;
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
	private int fetus = 2;
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

	public void bear(Rat rat, int day, int fetus) {
		maleSmallRat = new SmallRat();
		maleSmallRat.setSex("male");
		femaleSmallRat = new SmallRat();
		femaleSmallRat.setSex("female");
		if (rat.getSex().equals("female")) {
			//int taishu = fetus + 4;
			if (fetus >= 1 && fetus <= 11) {
				int maleCount = 0;
				int femaleCount = 0;
				Random r = new Random();
				for (int x = 0; x < rat.getCount() * (fetus + 4); x++) {
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
				for (int x = 0; x < rat.getCount() * (26 - fetus); x++) {
					int num = r.nextInt() + 1;
					if (num % 2 == 0) {
						maleSmallRat.setCount(++maleCount);
					} else {
						femaleSmallRat.setCount(++femaleCount);
					}
				}
			}
		}
		System.out.println(maleSmallRat.getCount());
		System.out.println(femaleSmallRat.getCount());
		afterThreeMonth(maleSmallRat);
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
	public void dead(Rat rat) {
		SmallRat smallRat = (SmallRat) rat;
		if (smallRat != null) {
			Integer smallRatCount = map.get(smallRat);
			smallRatCount = 0;
			smallRat = null;
		}
	}

	@Override
	public void afterThreeMonth(Rat rat) {
		SmallRat smallRat = (SmallRat) rat;
		if (smallRat != null) {
			map.put(smallRat, smallRat.getCount());
			System.out.println("----------------------");
			System.out.println(smallRat.getCount());
		}
	}
}
