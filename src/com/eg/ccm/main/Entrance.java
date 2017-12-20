package com.eg.ccm.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.eg.ccm.entity.Rat;
import com.eg.ccm.entity.RatNest;
import com.eg.ccm.entity.SmallRat;
import com.eg.ccm.timer.TimeUtil;

/**
 * 
	本次自由编程题目，除了继续每人独立完成上次没有完成的题目之外，还有如下新的题目，请各位独立完成。12月25上午一起将你们的程序打包发给我。
	农场除了家畜之外还有些不速之客（田鼠）， 农场的主人为了解决这些忧患养4只猫。
	农夫发现农田里面有10个老鼠窝。
	1，假设每一窝老鼠有1只成年公鼠，1只成年母鼠。5只小老鼠，且是第一胎。小老鼠1个月了。
	1.老鼠生长三个月成熟，受孕21天生产小老鼠。第一胎5只小老鼠，以后每胎加1只，直到一胎达到15个，以后每胎减少一只，直到一胎产5个时。母鼠停止生育。
	2.老鼠的最大寿命2年6个月，然后自然死亡。
	
	3.4只猫中2母2公，猫都1岁了。 猫每8个月发情交尾，母猫怀孕2个月后生育，每次生育8只小猫。小猫15个月成年。
	4.猫每5天可以抓到1只老鼠。小猫10个月就可以抓老鼠了。10岁之后不再有抓老鼠的能力。
	5.猫寿命为15年，然后自然死亡。
	6.农夫最多养15只猫。多余的将送人，其中留下小猫不超过20%，成年公猫不超过20%。
	7.生育的猫和老鼠的性别随机。
	
	现在是2017年12月12日，请问
	1.农场什么时候能够把老鼠清理完？
	2.老鼠最多的时候会有多少只？
	3.农场的猫最多一次抓了多少只老鼠？
	4.请输出老鼠和猫数量的变化情况（表格或者图片）。
 * @author gqliu
 *
 */
public class Entrance {
	
	public static void main(String[] args) throws Exception {
		/*FileOutputStream fos = new FileOutputStream("src/SmallMaleRat.properties");
		Properties prop = new Properties();
		for (Integer i = 1, j = 5; i <= 11; i++, j++) {
			//prop.setProperty(i.toString(), value)
			for (int x = 0; j < j; x++) {
				
			}
		}*/
		/*Rat rat = new Rat();
		rat.setSex("female");
		rat.setCount(100);
		rat.bear(rat, 0, 11);*/
		CopyOnWriteArrayList<Rat> maleRatList = new CopyOnWriteArrayList<>();
		CopyOnWriteArrayList<Rat> femaleRatList = new CopyOnWriteArrayList<>();
		CopyOnWriteArrayList<SmallRat> smallMaleRatList = new CopyOnWriteArrayList<>();
		CopyOnWriteArrayList<SmallRat> smalllFemaleRatList = new CopyOnWriteArrayList<>();
		
		Rat maleRat = new Rat(1, 3, "male");
		Rat femaleRat = new Rat(1, 3, "female");
		femaleRat.setFetus(femaleRat, 2);
		SmallRat maleSmallRat = new SmallRat();
		maleSmallRat.setAge(1);
		maleSmallRat.setSex("male");
		SmallRat femaleSmallRat = new SmallRat();
		femaleSmallRat.setAge(1);
		femaleSmallRat.setSex("female");
		femaleSmallRat.setFetus(femaleSmallRat, 1);
		Random r = new Random();
		int maleSmallCount = 0;
		int femaleSmallCount = 0;
		for (int i = 0; i < 5; i++) {
			int num = r.nextInt() + 1;
			if (num % 2 == 0) {
				maleSmallRat.setCount(++maleSmallCount);
			} else {
				femaleSmallRat.setCount(++femaleSmallCount);
			}
		}
		System.out.println("初始成年母老鼠数量：" + femaleRat.getCount() + "," + "成年公老鼠数量：" + maleRat.getCount() + 
				", 小母老鼠数量：" + femaleSmallRat.getCount() + "， 小公老鼠数量：" + maleSmallRat.getCount());
		maleRatList.add(maleRat);
		femaleRatList.add(femaleRat);
		smallMaleRatList.add(maleSmallRat);
		smalllFemaleRatList.add(femaleSmallRat);
		RatNest ratNest = new RatNest(10, maleRatList, femaleRatList, smallMaleRatList, smalllFemaleRatList);
		TimeUtil.setDate(2017, 11, 11);
		TimeUtil.startTime(ratNest);
	}
}
