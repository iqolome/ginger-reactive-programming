/**
 * 
 */
package com.ginger.steam;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

/**
 * @Description: 学院
 * @author 姜锋
 * @date 2019年4月8日 下午6:32:15
 * @version V1.0
 */

	enum Grader {
		ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN;
	}

	enum Gender {
		MALE, FEMALE
	}

	class Student {
		private String name;
		private Integer age;
		private Gender gender;
		private Grader grader;

		/**
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name 要设置的 name
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return age
		 */
		public Integer getAge() {
			return age;
		}
		/**
		 * @param age 要设置的 age
		 */
		public void setAge(Integer age) {
			this.age = age;
		}
		/**
		 * @return gender
		 */
		public Gender getGender() {
			return gender;
		}
		/**
		 * @param gender 要设置的 gender
		 */
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		/**
		 * @return grader
		 */
		public Grader getGrader() {
			return grader;
		}
		/**
		 * @param grader 要设置的 grader
		 */
		public void setGrader(Grader grader) {
			this.grader = grader;
		}
		
		/*
		 * （非 Javadoc）
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + ", gender=" + gender + ", grader=" + grader
					+ ", toString()=" + super.toString() + "]";
		}
		/**
		 * @param name
		 * @param age
		 * @param gender
		 * @param grader
		 */
		public Student(String name, Integer age, Gender gender, Grader grader) {
			super();
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.grader = grader;
		}
	}
	
	public class CollectionDemo {	
	public static void main(String[] args) {
		List<Student> students = Arrays.asList(
				new Student("唐三 ",100,Gender.MALE,Grader.ONE),
				new Student("小舞 ",96,Gender.FEMALE,Grader.ONE),
				new Student("戴沐白 ",95,Gender.MALE,Grader.ONE),
				new Student("奥斯卡 ",92,Gender.MALE,Grader.ONE),
				new Student("马红俊 ",93,Gender.MALE,Grader.ONE),
				new Student("宁荣荣 ",94,Gender.FEMALE,Grader.ONE),
				new Student("朱竹清 ",93,Gender.FEMALE,Grader.ONE),
				new Student("白沉香 ",73,Gender.FEMALE,Grader.FIVE),
				new Student("唐昊 ",97,Gender.MALE,Grader.TWO),
				new Student("阿银 ",70,Gender.FEMALE,Grader.FIVE),
				new Student("千仞雪 ",100,Gender.FEMALE,Grader.TWO),
				new Student("比比东 ",100,Gender.FEMALE,Grader.TWO),
				new Student("胡列娜 ",70,Gender.FEMALE,Grader.FIVE),
				new Student("玉小刚 ",50,Gender.MALE,Grader.SIX),
				new Student("弗兰德 ",83,Gender.MALE,Grader.THREE),
				new Student("赵无极 ",81,Gender.MALE,Grader.THREE),
				new Student("宁风致 ",75,Gender.MALE,Grader.FIVE),
				new Student("独孤博 ",92,Gender.MALE,Grader.TWO),
				new Student("波赛西 ",99,Gender.FEMALE,Grader.ONE),
				new Student("唐晨 ",50,Gender.MALE,Grader.SIX),
				new Student("千道流 ",99,Gender.MALE,Grader.ONE),
				new Student("玉小刚 ",50,Gender.MALE,Grader.SIX),
				new Student("柳二龙 ",81,Gender.FEMALE,Grader.THREE)
				);
		
	//得到所有学生的年龄列表
		
		//s -> s.getAge() 尽量使用方法引用,不会多生成一个类似lambda$0这样的函数
		List<Integer> ages = 
				students.stream()
				.map(Student::getAge)
				.collect(Collectors.toList());
		System.out.println("所有学生的年龄"+ages);
		
		
		
		//统计汇总信息
		IntSummaryStatistics agesSummaryStatistics = 
				students.stream()
				.collect(Collectors.summarizingInt(Student::getAge));
		
		System.out.println("年龄汇总信息: "+agesSummaryStatistics);
		
		//分块
		Map<Boolean, List<Student>> genders = students.stream().collect(Collectors.partitioningBy(s ->s.getGender() == Gender.MALE));
		MapUtils.verbosePrint(System.out, "男女生列表", genders);
		//System.out.println("男女生列表"+genders);
		
		
		//分组
		Map<Grader, List<Student>> grades = students.stream().collect(Collectors.groupingBy(Student::getGrader));
		MapUtils.verbosePrint(System.out, "班级分组列表", grades);
		
		//得到所有班级学生的个数
		Map<Grader, Long> gradesCount =
				students.stream().collect(
						Collectors.groupingBy(Student::getGrader,
						Collectors.counting()
						)
					);
		MapUtils.verbosePrint(System.out, "班级分组列表", gradesCount);
	}
}
