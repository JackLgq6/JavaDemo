package com.eg.ccm.entity;

import java.util.List;
import java.util.Random;

import sun.launcher.resources.launcher;

/**
 * 3.4只猫中2母2公，猫都1岁了。 猫每8个月发情交尾，母猫怀孕2个月后生育，每次生育8只小猫。小猫15个月成年。
 * 4.猫每5天可以抓到1只老鼠。小猫10个月就可以抓老鼠了。10岁之后不再有抓老鼠的能力。 5.猫寿命为15年，然后自然死亡。
 * 6.农夫最多养15只猫。多余的将送人，其中留下小猫不超过20%，成年公猫不超过20%。 7.生育的猫和老鼠的性别随机。
 * 
 * @author gqliu
 * 
 */
public class Cat {

	private int age;
	private int count;
	private String sex;

	public Cat() {
		super();
	}

	public Cat(int age, int count, String sex) {
		super();
		this.age = age;
		this.count = count;
		this.sex = sex;
	}

	public void bear(CatNest catNest, Cat femaleCat) {
		SmallCat smallMaleCat = new SmallCat();
		smallMaleCat.setAge(0);
		SmallCat smallFemaleCat = new SmallCat();
		smallFemaleCat.setAge(0);
		Random r = new Random();
		int smallMaleCatCount = 0;
		int smallFemaleCatCount = 0;
		for (int i = 0; i < femaleCat.getCount() * 8; i++) {
			int num = r.nextInt() + 1;
			if (num % 2 == 0) {
				smallMaleCat.setCount(++smallMaleCatCount);
			} else {
				smallFemaleCat.setCount(++smallFemaleCatCount);
			}
		}
		List<SmallCat> smallMaleCatList = catNest.getSmallMaleCatList();
		List<SmallCat> smallFemaleCatList = catNest.getSmallFemaleCatList();
		smallMaleCatList.add(smallMaleCat);
		smallFemaleCatList.add(smallFemaleCat);
	}
	
	public void catchMounse(RatNest ratNest) {
		List<Rat> femaleRatList = ratNest.getFemaleRatList();
		List<Rat> maleRatList = ratNest.getMaleRatList();
		List<SmallRat> smallMaleRatList = ratNest.getSmallMaleRatList();
		List<SmallRat> smallFemaleRatList = ratNest.getSmallFemaleRatList();
		Random r = new Random();
		int number = r.nextInt(100) + 1;
		if (number % 3 == 0) {
			for (SmallRat smallFemaleRat : smallFemaleRatList) {
				int smallFemaleCount = smallFemaleRat.getCount();
				smallFemaleRat.setCount(--smallFemaleCount);
				break;
			}
		} else if ( number % 4 == 0) {
			for (Rat maleRat : maleRatList) {
				int maleRatCount = maleRat.getCount();
				maleRat.setCount(--maleRatCount);
				break;
			}
		} else if (number % 5 == 0) {
			for (SmallRat smallMaleRat : smallMaleRatList) {
				int smallMaleRatCount = smallMaleRat.getCount();
				smallMaleRat.setCount(--smallMaleRatCount);
				break;
			}
		} else {
			for (Rat femaleRat : femaleRatList) {
				int femaleCount = femaleRat.getCount();
				femaleRat.setCount(--femaleCount);
				break;
			}
		}
	}
	
	//农夫最多养15只猫。多余的将送人，其中留下小猫不超过20%，成年公猫不超过20%。
	public void giveToOthers(CatNest catNest) {
		List<Cat> maleCatList = catNest.getMaleCatList();
		List<Cat> femaleCatList = catNest.getFemaleCatList();
		List<SmallCat> smallMaleCatList = catNest.getSmallMaleCatList();
		List<SmallCat> smallFemaleCatList = catNest.getSmallFemaleCatList();
		int maleCatCount = maleCatList.size();
		int femaleCatCount = femaleCatList.size();
		int smallMaleCatCount = smallMaleCatList.size();
		int smallFemaleCatCount = smallFemaleCatList.size();
		int totalCat = maleCatCount + femaleCatCount + smallMaleCatCount + smallFemaleCatCount;
		int leaveCat = 15;
		if (totalCat > leaveCat) {
			if ((smallMaleCatCount + smallFemaleCatCount) > (leaveCat * 0.2)) {
				int leaveSmallCatCount = (int)(leaveCat * 0.2);
				int toBeLeaveCount = 0;
				while (true) {
					int sfCount = 0;
					for (SmallCat smallFemaleCat : smallFemaleCatList) {
						if (smallFemaleCatList.size() == 1) {
							sfCount = smallFemaleCat.getCount();
							if (sfCount < 3) {
								break;
							} else {
								smallFemaleCat.setCount(2);
								sfCount = 2;
								break;
							}
						} else if (smallFemaleCatList.size() > 1) {
							smallFemaleCatList.remove(smallFemaleCat);
						}
					}
					for (SmallCat smallMaleCat : smallMaleCatList) {
						if (smallMaleCatList.size() == 1) {
							int smCount = smallMaleCat.getCount();
							if (smCount < 3) {
								if (smCount == 1 && sfCount == 1) {
									break;
								} else if (smCount == 1 && sfCount == 2) {
									break;
								} else if (smCount == 2 && sfCount == 1) {
									break;
								}
								else {
									smallMaleCat.setCount(1);
									smCount = 1;
									break;
								}
							} else {
								switch (sfCount) {
								case 1:
									smallMaleCat.setCount(2);
									break;
								case 2:
									smallMaleCat.setCount(1);
									break;
								default:
									break;
								}
							}
							toBeLeaveCount = smCount + sfCount;
						} else if (smallMaleCatList.size() > 1) {
							smallMaleCatList.remove(smallMaleCat);
						}
					}
					if (toBeLeaveCount <= leaveSmallCatCount) {
						break;
					}
				}
			}
			if (maleCatCount > (leaveCat * 0.2)) {
				int toBeLeaveMaleCat = (int)(leaveCat * 0.2);
				for (Cat maleCat : maleCatList) {
					if (maleCatList.size() == 1) {
						int mcCount = maleCat.getCount();
						if (mcCount <= toBeLeaveMaleCat) {
							break;
						} else {
							maleCat.setCount(toBeLeaveMaleCat);
							break;
						}
					} else if (maleCatList.size() > 1) {
						maleCatList.remove(maleCat);
					}
				}
			}
		}
	}
	
	//猫寿命为15年，然后自然死亡。
	public void dead(CatNest catNest) {
		List<Cat> maleCatList = catNest.getMaleCatList();
		List<Cat> femaleCatList = catNest.getFemaleCatList();
		for (Cat femaleCat: femaleCatList) {
			if (femaleCat.getAge() >= 15) {
				femaleCatList.remove(femaleCat);
				femaleCat = null;
			}
		}
		for (Cat maleCat : maleCatList) {
			if (maleCat.getAge() >= 15) {
				maleCatList.remove(maleCat);
				maleCat = null;
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
