package com.eg.ccm.entity;

import java.util.HashMap;
import java.util.Map;

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
		if (rat.getSex().equals("female")) {
			for (Integer i = 1, j = 5; i <= 11; i++, j++) {
				for (int x = 0; x < j; x++) {
					if (fetus = i)
				}
			}
		}
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
		}
	}
}
