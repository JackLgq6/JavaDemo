package com.eg.ccm.entity;

import java.util.List;
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
	
	public Rat() {
	}

	public Rat(int count, int age, String sex) {
		this.count = count;
		this.age = age;
		this.sex = sex;
	}

	/**
	 * 成年母老鼠产小老鼠
	 * @param ratNest 老鼠窝
	 * @param femaleRat 生育母老鼠
	 * @param fetus 胎数
	 */
	public void bear(RatNest ratNest, Rat femaleRat, int fetus) {
		SmallRat maleSmallRat = new SmallRat();
		maleSmallRat.setSex("male");
		maleSmallRat.setAge(0);
		SmallRat femaleSmallRat = new SmallRat();
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
		System.out.println("产小公老鼠数量：" + maleSmallRat.getCount());
		System.out.println("产小母老鼠数量：" + femaleSmallRat.getCount());
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

	/**
	 * 老鼠死亡
	 */
	@Override
	public void dead(RatNest ratNest) {
		List<Rat> femaleRatList = ratNest.getFemaleRatList();
		List<Rat> maleRatList = ratNest.getMaleRatList();
		/*List<SmallRat> smallMaleRatList = ratNest.getSmallMaleRatList();
		List<SmallRat> smallFemaleRatList = ratNest.getSmallFemaleRatList();*/
		/*for (SmallRat smallFemaleRat : smallFemaleRatList) {
			if (smallFemaleRat.getAge() >= 23) {
				smallFemaleRatList.remove(smallFemaleRat);
				System.out.println("-----------------------小母老鼠死亡");
			}
		}
		for (SmallRat smallMaleRat : smallMaleRatList) {
			if (smallMaleRat.getAge() >= 23) {
				smallFemaleRatList.remove(smallMaleRat);
				System.out.println("-----------------------小公老鼠死亡");
			}
		}*/
		for (Rat maleRat : maleRatList) {
			if (maleRat.getAge() >= 30) {
				maleRatList.remove(maleRat);
//				System.out.println("移除的公老鼠数量：" + maleRat.getCount());
				maleRat = null;
			}
		}
		for (Rat femaleRat : femaleRatList) {
			if (femaleRat.getAge() >= 30) {
				femaleRatList.remove(femaleRat);
//				System.out.println("移除的母老鼠数量：" + femaleRat.getCount());
				femaleRat = null;
			}
		}
	}

	@Override
	public void afterThreeMonth(RatNest ratNest) {
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
