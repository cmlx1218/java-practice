package com.tt.java.streampractice.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-4-27 0027 16:12
 */
public class BasicTest {

    public static void main(String[] args) {
        //TODO 1、把集合转换成stream流
        List list = new ArrayList();
        Stream stream = list.stream();
        Stream<List> list1 = Stream.of(list);

        Set set = new HashSet();
        Stream stream1 = set.stream();

        Map<String, String> map = new HashMap();
        Stream<Map.Entry<String, String>> stream2 = map.entrySet().stream();
        Stream<String> stream3 = map.keySet().stream();
        Stream<String> stream4 = map.values().stream();

        int[] ids = {1, 2, 3, 4, 5};
        Stream<int[]> ids1 = Stream.of(ids);
        IntStream stream5 = Arrays.stream(ids);


        //TODO 2、逐一处理  forEach
        Map<Integer, String> student = new HashMap<>();
        /*for (int i = 0;i<1000000;i++){
            student.put(i,"赤名莉香");
        }*/
        student.put(1, "赤名莉香");
        student.put(6, "赤香");
        student.put(7, "赤莉香");
        student.put(5, "赤名强");
        student.put(2, "永尾完治");
        student.put(3, "三上健一");
        student.put(4, "长崎尚子");


        student.entrySet().forEach(m -> {
            System.out.println("开始了");
            System.out.println(m.getKey() + ":" + m.getValue());
        });
        // 当forEach里面只有一行代码的时候
        long l = System.currentTimeMillis();
        student.entrySet().forEach(m -> System.out.println(m.getKey() + ":" + m.getValue()));
        long l1 = System.currentTimeMillis();


        // 比较stream().forEach() 和 forEach()
        long l2 = System.currentTimeMillis();
        student.entrySet().stream().forEach(m -> System.out.println(m.getKey() + ":" + m.getValue()));
        long l3 = System.currentTimeMillis();
        System.out.println("forEach耗时：" + (l1 - l));
        System.out.println("stream().forEach耗时：" + (l3 - l2));

        //TODO 3、过滤  filter
        // Stream流属于管道流，使用完之后关闭，流到下一个Stream流
        student.entrySet().stream().filter(m -> {
            return m.getValue().startsWith("赤名");
        }).forEach(m -> System.out.println(m.getKey() + ":" + m.getValue()));

        //TODO 4、映射  map
        String[] uids = {"1", "2", "3", "4"};
        List<String> strings = Arrays.asList(uids);
        strings.stream().map(Integer::valueOf).forEach(System.out::println);

        Arrays.stream(uids).map(Integer::valueOf).collect(Collectors.toList()).forEach(System.out::println);

        String ss = "1,2,3,4,5,6";
        Arrays.stream(ss.split(",")).map(Integer::valueOf).collect(Collectors.toList()).forEach(System.out::println);

        //TODO 5、count  统计个数
        long count = student.entrySet().stream().filter(m -> m.getValue().contains("赤")).count();
        System.out.println("包含赤的个数为:" + count);

        //TODO 6、limit  取用前几个
        student.entrySet().stream().filter(m -> m.getValue().contains("赤")).limit(3).forEach(System.out::println);

        student.entrySet().stream().forEach(System.out::println);

        String[] names = {"xx","ss","ioi","sss","ss"};
        Arrays.asList(names).stream().forEach(System.out::println);

        //TODO 7、skip  跳过前几个
        Arrays.stream(names).skip(2).forEach(System.out::println);
        System.out.println("------------------");

        //TODO 8、concat  组合流
        Stream.concat(Arrays.stream(names).limit(3),Arrays.stream(names).skip(1)).forEach(System.out::println);

        //TODO 9、distinct  去重
        Arrays.stream(names).distinct().collect(Collectors.toList()).forEach(System.out::println);

    }

}
