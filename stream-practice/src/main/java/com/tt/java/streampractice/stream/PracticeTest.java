package com.tt.java.streampractice.stream;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.tt.java.streampractice.stream.dto.Student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-4-27 0027 18:50
 */
public class PracticeTest {

    public static void main(String[] args) {
        //初始化数据
        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
            }
        };

        //TODO 1、映射  map  mapToInt mapToLong mapToDouble
        //获取武汉大学所有学生的名字
        List<String> names = students.stream().filter(student -> "武汉大学".equals(student.getSchool())).map(Student::getName).collect(Collectors.toList());
        System.out.println(names);
        //获取武汉大学所有学生的年龄和
        int sum = students.stream().filter(student -> "武汉大学".equals(student.getSchool())).mapToInt(Student::getAge).sum();
        System.out.println("武汉大学所有学生的年龄和是" + sum);

        //TODO 2、查找 allMatch anyMatch noneMatch findFirst findAny
        //allMatch: 检测是否全部满足指定的参数行为
        boolean b = students.stream().allMatch(student -> student.getAge() > 18 && student.getGrade() > 0);
        System.out.println(b);
        //anyMatch: 检测是否存在一个或者多个满足指定的参数行为
        boolean b1 = students.stream().anyMatch(student -> student.getAge() > 18 && student.getGrade() > 2);
        System.out.println(b1);
        //noneMatch: 检测是否存在不满足指定的参数行为
        boolean b2 = students.stream().noneMatch(student -> student.getAge() > 18 && student.getGrade() > 1);
        System.out.println(b2);
        //findFirst: 返回第一个满足指定的参数行为
        Optional<Student> first = students.stream().filter(student -> student.getAge() > 21).findFirst();
        System.out.println(first.get());
        //findAll: 返回任意一个满足指定的参数行为
        Optional<Student> any = students.stream().filter(student -> student.getAge() > 21).findAny();
        System.out.println(any.get());

        //TODO 3、规约 reduce
        // 前面例子中的方法
        int totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();
        // 归约操作
        int totalAge1 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, (a, c) -> a + c);

        // 进一步简化
        int totalAge2 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, Integer::sum);

        // 采用无初始值的重载版本，需要注意返回Optional
        Optional<Integer> totalAge3 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(Integer::sum);  // 去掉初始值

        //TODO 4、求年龄最大值和最小值
        Optional<Student> collect = students.stream().collect(Collectors.maxBy((s1, s2) -> s1.getAge() - s2.getAge()));
        Optional<Student> collect1 = students.stream().collect(Collectors.maxBy(Comparator.comparing(Student::getAge)));
        Optional<Student> collect2 = students.stream().max((s1, s2) -> s1.getAge() - s2.getAge());
        Optional<Student> min = students.stream().min((s1, s2) -> s1.getAge() - s2.getAge());
        Optional<Student> collect3 = students.stream().collect(Collectors.minBy(Comparator.comparing(Student::getAge)));

        //TODO 5、分组 groupBy
        Map<String, List<Student>> collect4 = students.stream().collect(Collectors.groupingBy(Student::getSchool));
        System.out.println(collect4);
        // 多级分组
        Map<String, Map<String, List<Student>>> collect5 = students.stream().collect(Collectors.groupingBy(Student::getSchool, Collectors.groupingBy(Student::getMajor)));
        System.out.println(collect5);

        //TODO 6、分区 partitioningBy
        // 将学生分为武汉大学和非武汉大学学生
        Map<Boolean, List<Student>> collect6 = students.stream().collect(Collectors.partitioningBy(student -> "武汉大学".equals(student.getSchool())));
        System.out.println(collect6);

        //TODO 7、排序 sorted
        Map<Integer, String> map = ImmutableMap.of(1, "zq", 2, "cmlx", 3, "zzr", 4, "xxx", 5, "ooo");
        System.out.println("原始的map：" + map);
        System.out.println("key降序：" + sortByKey(map, true, 2));
        System.out.println("key升序：" + sortByKey(map, false, 2));
        System.out.println("value降序：" + sortByValue(map, true, 2));
        System.out.println("value升序：" + sortByValue(map, false, 2));
    }


    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean isDesc, int limit) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue().reversed()).limit(limit)
                    .forEach(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }

        return result;
    }

    private static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc, int limit) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed()).limit(limit)
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

}
