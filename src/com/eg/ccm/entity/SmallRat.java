package com.eg.ccm.entity;

public class SmallRat extends Rat {
	
	private int age;
	private int count;
	
	public SmallRat() {
	}
	
	public SmallRat(int count, int age) {
		this.count = count;
		this.age = age;
	}
	
	public void threeMonthLater(SmallRat smallMaleRat) {
		Rat.afterThreeMonth(smallMaleRat);
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
