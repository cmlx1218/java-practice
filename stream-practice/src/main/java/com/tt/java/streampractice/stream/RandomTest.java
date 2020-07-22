package com.tt.java.streampractice.stream;

import com.alibaba.fastjson.JSONArray;
import com.tt.java.streampractice.stream.dto.VoteOptionDto;
import org.apache.commons.lang.StringEscapeUtils;

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
        //String optionIds = "1,2,4,6";
        //List<Long> collect = Arrays.stream(optionIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
        //System.out.println(collect);
        //
        //
        //String voteOptions = "[{\"name\":\"赤名莉香\"},{\"name\":\"三上悠亚\"},{\"name\":\"郑家斌\"},{\"name\":\"赵振妽\"}]";
        //List<VoteOptionDto> voteOptionDtos = JSONArray.parseArray(voteOptions, VoteOptionDto.class);
        //voteOptionDtos.forEach(v -> v.setId((long) voteOptionDtos.indexOf(v)));
        //String s = JSONArray.toJSONString(voteOptionDtos);
        //System.out.println(s);

        //String key = "app/fa2e4b91259019e475e6dd251caeb299.mp4";
        //System.out.println(key.indexOf("/"));
        //System.out.println(key.indexOf("."));
        //System.out.println();
        //System.out.println("app/hls" + key.substring(key.indexOf("/"), key.indexOf(".")) + "$(count)");
        //String url = "http://devmedia.aimymusic.com/app/bfc0ba6fac308d3f3b99168c8c0969dc.mp4";
        //String substring = url.substring(url.lastIndexOf("/") - 3);
        //System.out.println(substring);

        String url = "http:\\/\\/devmedia.aimymusic.com\\/app\\/dd5244aa4964574c103cc3767e5f4f13.mp4";
        String urll = "http://devmedia.aimymusic.com/app/dd5244aa4964574c103cc3767e5f4f13.mp4";
        String s = StringEscapeUtils.unescapeJava(urll);
        System.out.println(s);

        //System.out.println(url);
        //url = url.replaceAll("\\\\/","/");
        //System.out.println(url);

    }

}
