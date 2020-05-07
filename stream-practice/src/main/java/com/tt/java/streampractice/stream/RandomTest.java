package com.tt.java.streampractice.stream;

import com.alibaba.fastjson.JSONArray;
import com.tt.java.streampractice.stream.dto.VoteOptionDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-5-6 0006 15:23
 */
public class RandomTest {

    public static void main(String[] args) {
        String optionIds = "1,2,4,6";
        List<Long> collect = Arrays.stream(optionIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
        System.out.println(collect);


        String voteOptions = "[{\"name\":\"赤名莉香\"},{\"name\":\"三上悠亚\"},{\"name\":\"郑家斌\"},{\"name\":\"赵振妽\"}]";
        List<VoteOptionDto> voteOptionDtos = JSONArray.parseArray(voteOptions, VoteOptionDto.class);
        voteOptionDtos.forEach(v -> v.setId((long) voteOptionDtos.indexOf(v)));
        String s = JSONArray.toJSONString(voteOptionDtos);
        System.out.println(s);



    }

}
