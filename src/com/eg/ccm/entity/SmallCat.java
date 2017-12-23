package com.eg.ccm.entity;

import java.util.List;


public class SmallCat extends Cat {

	private int age;
	private int count;
	private String sex;

	public SmallCat() {
		super();
	}

	public SmallCat(int age, int count, String sex) {
		super();
		this.age = age;
		this.count = count;
		this.sex = sex;
	}
	
	public void growUp(CatNest catNest) {
		List<Cat> maleCatList = catNest.getMaleCatList();
		List<Cat> femaleCatList = catNest.getFemaleCatList();
		List<SmallCat> smallMaleCatList = catNest.getSmallMaleCatList();
		List<SmallCat> smallFemaleCatList = catNest.getSmallFemaleCatList();
		for (SmallCat smallFemaleCat : smallFemaleCatList) {
			if (smallFemaleCat.getAge() >= 5 && smallFemaleCat.getSex().equals("female")) {
				femaleCatList.add(smallFemaleCat);
				smallFemaleCatList.remove(smallFemaleCat);
			}
		}
		for (SmallCat smallMaleCat : smallMaleCatList) {
			if (smallMaleCat.getAge() >= 5 && smallMaleCat.getSex().equals("male")) {
				maleCatList.add(smallMaleCat);
				smallMaleCatList.remove(smallMaleCat);
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

}
