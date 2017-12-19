package com.eg.ccm.entity;

import java.util.ArrayList;
import java.util.List;

public class RatNest {

	private int count;
	private List<Rat> maleRatList;
	private List<Rat> femaleRatList;
	private List<SmallRat> smallMaleRatList;
	private List<SmallRat> smallFemaleRatList;

	public RatNest() {

	}

	public RatNest(int count, List<Rat> maleRatList, List<Rat> femaleRatList,
			List<SmallRat> smallMaleRatList, List<SmallRat> smallFemaleRatList) {
		super();
		this.count = count;
		this.maleRatList = maleRatList;
		this.femaleRatList = femaleRatList;
		this.smallMaleRatList = smallMaleRatList;
		this.smallFemaleRatList = smallFemaleRatList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Rat> getMaleRatList() {
		return maleRatList;
	}

	public void setMaleRatList(List<Rat> maleRatList) {
		this.maleRatList = maleRatList;
	}

	public List<Rat> getFemaleRatList() {
		return femaleRatList;
	}

	public void setFemaleRatList(List<Rat> femaleRatList) {
		this.femaleRatList = femaleRatList;
	}

	public List<SmallRat> getSmallMaleRatList() {
		return smallMaleRatList;
	}

	public void setSmallMaleRatList(List<SmallRat> smallMaleRatList) {
		this.smallMaleRatList = smallMaleRatList;
	}

	public List<SmallRat> getSmallFemaleRatList() {
		return smallFemaleRatList;
	}

	public void setSmallFemaleRatList(List<SmallRat> smallFemaleRatList) {
		this.smallFemaleRatList = smallFemaleRatList;
	}

}
