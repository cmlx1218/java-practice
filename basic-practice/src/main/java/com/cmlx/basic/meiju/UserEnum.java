package com.cmlx.basic.meiju;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * @Author CMLX
 * @Date -> 2021/5/8 14:19
 * @Desc ->
 **/
public class UserEnum {

    @Getter
    @AllArgsConstructor
    public enum Sex {
        Man(1, "男"),
        WOMAN(2, "女");

        public Integer code;
        public String msg;

        private static HashMap<Integer, Sex> data = new HashMap<>();

        static {
            for (Sex d : Sex.values()) {
                data.put(d.code, d);
            }
        }

        public static Sex parse(Integer code) {
            if (data.containsKey(code)) {
                return data.get(code);
            }
            return null;
        }
    }


    /**
     * sql公共符号
     */
    @Getter
    @AllArgsConstructor
    public enum SqlCommonMethod {
        Equal("="),
        UnEqual("!="),
        GreaterThan(">"),
        LessThan("<"),
        GreaterThanOrEqual(">="),
        LessThanOrEqual("<="),
        Instr("instr");

        private String val;

    }

}
