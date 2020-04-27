package com.tt.java.streampractice.stream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-4-27 0027 18:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    /**
     * 学号
     */
    private long id;

    private String name;

    private int age;

    /**
     * 年级
     */
    private int grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;

}
