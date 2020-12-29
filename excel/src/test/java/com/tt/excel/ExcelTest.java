package com.tt.excel;

import com.tt.excel.pojo.UserInfoDto;
import com.tt.excel.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author cmlx
 * @Date -> 2020/12/29 18:30
 * @Desc -> 测试 EasyExcel导出Excel和生成Excel
 **/
@Slf4j
@SpringBootTest
public class ExcelTest {

    @Test
    public void testReadLessThan1000Row() {
        String filePath = "D:\\project\\practice\\java\\document\\easyExcel\\userInfo.xlsx";
        List<UserInfoDto> userInfoDtoList = ExcelUtil.readLessThan1000Row(UserInfoDto.class, filePath);
        userInfoDtoList.forEach(System.out::println);
    }

    @Test
    public void testReadMoreThan1000Row() {
        String filePath = "D:\\project\\practice\\java\\document\\easyExcel\\userInfo.xlsx";
        List<UserInfoDto> userInfoDtoList = ExcelUtil.readMoreThan1000Row(UserInfoDto.class, filePath);
        userInfoDtoList.forEach(System.out::println);
    }

}
