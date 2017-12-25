package com.eg.ccm.entity;

import java.util.List;

public class SmallRat extends Rat {
	
	private int age;
	private int count;
	
	public SmallRat() {
	}
	
	public SmallRat(int count, int age) {
		this.count = count;
		this.age = age;
	}
	
	public void growUp(RatNest ratNest) {
		List<SmallRat> smallMaleRatList = ratNest.getSmallMaleRatList();
		List<SmallRat> smallFemaleRatList = ratNest.getSmallFemaleRatList();
		List<Rat> femaleRatList = ratNest.getFemaleRatList();
		List<Rat> maleRatList = ratNest.getMaleRatList();
		for (SmallRat smallFemaleRat: smallFemaleRatList) {
			if (smallFemaleRat.getAge() >= 3 && smallFemaleRat.getSex().equals("female")) {
				femaleRatList.add(smallFemaleRat);
				smallFemaleRatList.remove(smallFemaleRat);
			}
		}
		for (SmallRat smallMaleRat : smallMaleRatList) {
			if (smallMaleRat.getAge() >= 3 && smallMaleRat.getSex().equals("male")) {
				maleRatList.add(smallMaleRat);
				smallMaleRatList.remove(smallMaleRat);
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
	
	
}
