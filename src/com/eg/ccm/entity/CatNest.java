package com.eg.ccm.entity;

import java.util.List;

public class CatNest {

	private int count;
	private List<Cat> maleCatList;
	private List<Cat> femaleCatList;
	private List<SmallCat> smallMaleCatList;
	private List<SmallCat> smallFemaleCatList;

	public CatNest() {

	}

	public CatNest(int count, List<Cat> maleCatList, List<Cat> femaleCatList,
			List<SmallCat> smallMaleCatList, List<SmallCat> smallFemaleCatList) {
		super();
		this.count = count;
		this.maleCatList = maleCatList;
		this.femaleCatList = femaleCatList;
		this.smallMaleCatList = smallMaleCatList;
		this.smallFemaleCatList = smallFemaleCatList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Cat> getMaleCatList() {
		return maleCatList;
	}

	public void setMaleCatList(List<Cat> maleCatList) {
		this.maleCatList = maleCatList;
	}

	public List<Cat> getFemaleCatList() {
		return femaleCatList;
	}

	public void setFemaleCatList(List<Cat> femaleCatList) {
		this.femaleCatList = femaleCatList;
	}

	public List<SmallCat> getSmallMaleCatList() {
		return smallMaleCatList;
	}

	public void setSmallMaleCatList(List<SmallCat> smallMaleCatList) {
		this.smallMaleCatList = smallMaleCatList;
	}

	public List<SmallCat> getSmallFemaleCatList() {
		return smallFemaleCatList;
	}

	public void setSmallFemaleCatList(List<SmallCat> smallFemaleCatList) {
		this.smallFemaleCatList = smallFemaleCatList;
	}

}
