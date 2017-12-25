package com.eg.ccm.entity;

import java.util.List;
import java.util.Random;

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
		smallMaleCat.setSex("male");
		SmallCat smallFemaleCat = new SmallCat();
		smallFemaleCat.setAge(0);
		smallFemaleCat.setSex("female");
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
		System.out.println("产小公猫数量：" + smallMaleCat.getCount());
		System.out.println("产小母猫数量：" + smallFemaleCat.getCount());
	}
	
//	猫每5天可以抓到1只老鼠。小猫10个月就可以抓老鼠了。10岁之后不再有抓老鼠的能力
	public void catchMounse(RatNest ratNest, int catCount) {
		System.out.println("可以抓老鼠的猫数量： " + catCount);
		List<Rat> femaleRatList = ratNest.getFemaleRatList();
		List<Rat> maleRatList = ratNest.getMaleRatList();
		List<SmallRat> smallMaleRatList = ratNest.getSmallMaleRatList();
		List<SmallRat> smallFemaleRatList = ratNest.getSmallFemaleRatList();
		Random r = new Random();
		for (int i = 0; i < catCount; i++) {
			int number = r.nextInt(100) + 1;
			//System.out.println("随机数：" + number);
			if (number % 3 == 0) {
				for (SmallRat smallFemaleRat : smallFemaleRatList) {
					int smallFemaleCount = smallFemaleRat.getCount();
					if (smallFemaleCount == 0) {
						smallFemaleRatList.remove(smallFemaleRat);
						continue;
					}
					smallFemaleRat.setCount(--smallFemaleCount);
//					System.out.println("3的倍数，小母老鼠数量减1");
				}
			} else if ( number % 4 == 0) {
				for (Rat maleRat : maleRatList) {
					int maleRatCount = maleRat.getCount();
					if (maleRatCount == 0) {
						maleRatList.remove(maleRat);
						continue;
					}
					maleRat.setCount(--maleRatCount);
//					System.out.println("4的倍数，成年公老鼠数量减1");
				}
			} else if (number % 5 == 0) {
				for (SmallRat smallMaleRat : smallMaleRatList) {
					int smallMaleRatCount = smallMaleRat.getCount();
					if (smallMaleRatCount == 0) {
						smallMaleRatList.remove(smallMaleRat);
						continue;
					}
					smallMaleRat.setCount(--smallMaleRatCount);
//					System.out.println("5的倍数，小公老鼠数量减1");
				}
			} else {
				for (Rat femaleRat : femaleRatList) {
					int femaleCount = femaleRat.getCount();
					if (femaleCount == 0) {
						femaleRatList.remove(femaleRat);
						continue;
					}
					femaleRat.setCount(--femaleCount);
//					System.out.println("其他，成年母老鼠数量减1");
				}
			}
		}
	}
	
	//农夫最多养15只猫。多余的将送人，其中留下小猫不超过20%，成年公猫不超过20%。
	public void giveToOthers(CatNest catNest) {
		List<Cat> maleCatList = catNest.getMaleCatList();
		List<Cat> femaleCatList = catNest.getFemaleCatList();
		List<SmallCat> smallMaleCatList = catNest.getSmallMaleCatList();
		List<SmallCat> smallFemaleCatList = catNest.getSmallFemaleCatList();
		//计算各类猫的总数量
		int maleCatCount = Cat.totalMaleCatCount(catNest);
		int femaleCatCount = Cat.totalFeMaleCatCount(catNest);
		int smallMaleCatCount = Cat.totalSmallMaleCatCount(catNest);
		int smallFemaleCatCount = Cat.totalSmallFeMaleCatCount(catNest);
		
		int totalCat = maleCatCount + femaleCatCount + smallMaleCatCount + smallFemaleCatCount;
		int leaveCat = 15;
		if (totalCat > leaveCat) {
			if ((smallMaleCatCount + smallFemaleCatCount) > (leaveCat * 0.2)) {
				int leaveSmallCatCount = (int)(leaveCat * 0.2);
				int toBeLeaveCount = 0;
				while (true) {
					int sfCount = 0;
					int smCount = 0;
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
							smCount = smallMaleCat.getCount();
							if (smCount < 3) {
								if (smCount == 1 && sfCount == 1) {
									break;
								} else if (smCount == 1 && sfCount == 2) {
									break;
								} else if (smCount == 2 && sfCount == 1) {
									break;
								}	else {
									smallMaleCat.setCount(1);
									smCount = 1;
									break;
								}
							} else {
								switch (sfCount) {
								case 1:
									smallMaleCat.setCount(2);
									smCount = 2;
									break;
								case 2:
									smallMaleCat.setCount(1);
									smCount = 1;
									break;
								default:
									break;
								}
							}
							toBeLeaveCount = smCount + sfCount;
							System.out.println("留下来的小公猫数量：" + smCount + ", 留下来的小母猫的数量：" + sfCount);
							System.out.println("将留下来的小猫的数量：" + toBeLeaveCount);
						} else if (smallMaleCatList.size() > 1) {
							smallMaleCatList.remove(smallMaleCat);
						}
					}
					if (toBeLeaveCount <= leaveSmallCatCount) {
						break;
					}
				}
			}
			if (femaleCatCount > (leaveCat * 0.6)) {
				int toBeLeaveFemaleCat = (int)(leaveCat * 0.6);
				System.out.println("集合大小：" + femaleCatList.size());
				for (Cat femaleCat : femaleCatList) {
					//System.out.println("集合的大小：" + femaleCatList.size());
					if (femaleCatList.size() == 1) {
						int fcCount = femaleCat.getCount();
						System.out.println("集合大小为1，成年母猫数量：" + fcCount);
						if (fcCount <= toBeLeaveFemaleCat) {
							System.out.println("留下成年母猫数量：" + fcCount);
							break;
						} else {
							femaleCat.setCount(toBeLeaveFemaleCat);
							System.out.println("留下成年母猫数量：" + toBeLeaveFemaleCat);
							break;
						}
					} else if (femaleCatList.size() > 1) {
						int minFemaleCatIndex = getMinCat(femaleCatList);
						femaleCatList.remove(minFemaleCatIndex);
						System.out.println("移除集合元素Cat");
					}
				}
			}
			if (maleCatCount > (leaveCat * 0.2)) {
				int toBeLeaveMaleCat = (int)(leaveCat * 0.2);
				for (Cat maleCat : maleCatList) {
					if (maleCatList.size() == 1) {
						int mcCount = maleCat.getCount();
						if (mcCount <= toBeLeaveMaleCat) {
							System.out.println("留下成年公猫的数量：" + mcCount);
							break;
						} else {
							maleCat.setCount(toBeLeaveMaleCat);
							System.out.println("留下成年公猫的数量：" + toBeLeaveMaleCat);
							break;
						}
					} else if (maleCatList.size() > 1) {
						int minIndex = getMinCat(maleCatList);
						maleCatList.remove(minIndex);
					}
				}
			}
		}
	}
	
	public int getMinCat(List<Cat> catList) {
		Cat minCat = catList.get(0);
		int min = 0;
		for (int i = 0; i < catList.size(); i++) {
			if (catList.get(i).equals(minCat)) {
				minCat = catList.get(i);
				min = i;
			}
		}
		return min;
	}
	
	public boolean equals(Cat cat) {
		return this.count < cat.getCount();
	}
	
	public static int totalMaleCatCount(CatNest catNest) {
		List<Cat> maleCatList = catNest.getMaleCatList();
		int totalCount = 0;
		for (Cat maleCat : maleCatList) {
			totalCount += maleCat.getCount();
		}
		return totalCount;
	}
	
	public static int totalFeMaleCatCount(CatNest catNest) {
		List<Cat> femaleCatList = catNest.getFemaleCatList();
		int totalCount = 0;
		for (Cat femaleCat : femaleCatList) {
			totalCount += femaleCat.getCount();
		}
		return totalCount;
	}
	
	public static int totalSmallFeMaleCatCount(CatNest catNest) {
		List<SmallCat> smallFemaleCatList = catNest.getSmallFemaleCatList();
		int totalCount = 0;
		for (Cat smallFemaleCat : smallFemaleCatList) {
			totalCount += smallFemaleCat.getCount();
		}
		return totalCount;
	}
	
	public static int totalSmallMaleCatCount(CatNest catNest) {
		List<SmallCat> smallMaleCatList = catNest.getSmallMaleCatList();
		int totalCount = 0;
		for (Cat smallMaleCat : smallMaleCatList) {
			totalCount += smallMaleCat.getCount();
		}
		return totalCount;
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
	
//	猫每5天可以抓到1只老鼠。小猫10个月就可以抓老鼠了。10岁之后不再有抓老鼠的能力。
	public static int canCatchRatCount(CatNest catNest) {
		int totalCount = 0;
		//成年公猫和成年母猫数量；
		List<Cat> maleCatList = catNest.getMaleCatList();
		List<Cat> femaleCatList = catNest.getFemaleCatList();
		List<SmallCat> smallMaleCatList = catNest.getSmallMaleCatList();
		List<SmallCat> smallFemaleCatList = catNest.getSmallFemaleCatList();
		for (SmallCat smallFemaleCat : smallFemaleCatList) {
			if (smallFemaleCat.getAge() >= 10 && smallFemaleCat.getAge() <= 120) {
				totalCount += smallFemaleCat.getCount();
			}
		}
		for (SmallCat smallMaleCat : smallMaleCatList) {
			if (smallMaleCat.getAge() >= 10 && smallMaleCat.getAge() <= 120) {
				totalCount += smallMaleCat.getCount();
			}
		}
		for (Cat femaleCat : femaleCatList) {
			if (femaleCat.getAge() <= 120) {
				totalCount += femaleCat.getCount();
			}
		}
		for (Cat maleCat : maleCatList) {
			if (maleCat.getAge() <= 120) {
				totalCount += maleCat.getCount();
			}
		}
		return totalCount;
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
