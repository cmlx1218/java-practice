package com.tt.excel.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

/**
 * @Author cmlx
 * @Date -> 2020/12/28 17:59
 * @Desc -> EasyExcel工具类：导出Java对象到Excel和导入Excel数据到Java对象工具类
 * <p>
 * UtilityClass注解作用:
 * 1.类会标记成final最终类
 * 2.属性会标记成静态属性
 * 3.会生成一个私有的无参构造函数,并抛出一个UnsupportedOperationException异常
 * 4.方法会标记成静态方法
 **/
@Slf4j
@UtilityClass
public class ExcelUtil {

    private final Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        // 设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    /**
     * 读取少于1000行数据，，默认读第一个Sheet【1】，从一行开始读取【0】
     *
     * @param clazz    返回类型
     * @param filePath 绝对路径
     * @param <T>      限定参数
     * @return Excel导出数据
     */
    public <T> List<T> readLessThan1000Row(Class<T> clazz, String filePath) {
        return readLessThan1000RowBySheet(clazz, filePath, null);
    }

    /**
     * 读取少于1000行数据，自定义Sheet
     * initSheet:
     * sheetNo: sheet页码，默认为1
     * headLineMun：从第几行开始读取数据，默认为0，表示从第一行开始读取
     * clazz：返回数据List<T> 中 T 的类名
     */
    public <T> List<T> readLessThan1000RowBySheet(Class<T> clazz, String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }
        // 初始化Sheet参数
        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            T t1 = clazz.newInstance();
            fileStream = new FileInputStream(filePath);
            List<Object> read = EasyExcelFactory.read(fileStream, sheet);
            List<T> result = new ArrayList<>();
            List<String> keys = (List<String>) read.get(0);
            for (int i = 1; i < read.size(); i++) {
                Map<String, Object> json = new HashMap<>();
                if (read.get(i) instanceof List) {
                    for (int j = 0; j < ((List) read.get(i)).size(); j++) {
                        json.put(keys.get(j), ((List) read.get(i)).get(j));
                    }
                    T t = JSON.parseObject(JSON.toJSONString(json), clazz);
                    result.add(t);
                }
            }
            return result;
        } catch (Exception e) {
            log.info("找不到文件或文件路径错误，文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件读取失败，失败原因：{}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 读取大于1000行数据，默认读第一个Sheet【1】，从一行开始读取【0】
     *
     * @param clazz    返回java泛型类
     * @param filePath 读取Excel绝对路径
     * @param <T>      入参限制
     * @return
     */
    public <T> List<T> readMoreThan1000Row(Class<T> clazz, String filePath) {
        return readMoreThan1000RowBySheet(clazz, filePath, null);
    }

    /**
     * 读取大于1000行数据，自定义Sheet
     *
     * @param clazz    返回java泛型类
     * @param filePath 读取Excel绝对路径
     * @param sheet    自定义Sheet
     * @param <T>      入参限制
     * @return
     */
    public <T> List<T> readMoreThan1000RowBySheet(Class<T> clazz, String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }

        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            ExcelListener excelListener = new ExcelListener();
            EasyExcelFactory.readBySax(fileStream, sheet, excelListener);
            List<Object> datas = excelListener.getDataList();
            List<T> result = new ArrayList<>();
            List<String> keys = (List<String>) datas.get(0);
            for (int i = 1; i < datas.size(); i++) {
                Map<String, Object> json = new HashMap<>();
                if (datas.get(i) instanceof List) {
                    for (int j = 0; j < ((List) datas.get(i)).size(); j++) {
                        json.put(keys.get(j), ((List) datas.get(i)).get(j));
                    }
                    T t = JSON.parseObject(JSON.toJSONString(json), clazz);
                    result.add(t);
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误，文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件读取失败，失败原因：{}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 简易生成Excel
     *
     * @param filePath 生成表格的存放路径 -> 绝对路径
     * @param data     数据源
     * @param head     表头
     */
    public <T> void writeBySimple(String filePath, List<List<T>> data, List<String> head) {

    }


    /**
     * 简易生成Excel
     *
     * @param filePath 生成表格的存放路径 -> 绝对路径，如：/opt/download/document/excel/userInfo.xlsx
     * @param data     数据源
     * @param head     表头
     * @param sheet    sheet excel 页面样式
     */
    public void writeSimpleBySheet(String filePath, List<List<Object>> data, List<String> head, Sheet sheet) {
        sheet = sheet != null ? sheet : initSheet;

        if (head != null) {
            final List<List<String>> list = new ArrayList<List<String>>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write1(data, sheet);
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误，文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }
                if (outputStream != null) {

                    outputStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件导出失败，失败原因：{}", e.getMessage());
            }
        }
    }


    /**
     * 生成Excel
     *
     * @param filePath 生成表格的存放路径 -> 绝对路径，如：/opt/download/document/excel/userInfo.xlsx
     * @param data     数据源
     */
    public void writeWithTemplate(String filePath, List<? extends BaseRowModel> data) {

    }

    /**
     * 生成Excel
     *
     * @param filePath 生成表格的存放路径 -> 绝对路径，如：/opt/download/document/excel/userInfo.xlsx
     * @param data     数据源
     * @param sheet    Excel页面样式
     */
    public void writeWithTemplateAndSheet(String filePath, List<? extends BaseRowModel> data, Sheet sheet) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        sheet = sheet != null ? sheet : initSheet;
        sheet.setClazz(data.get(0).getClass());

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(data, sheet);
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误，文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件导出失败，失败原因：{}", e.getMessage());
            }
        }
    }


    /************************************ 匿名内部类开始，可以提取出去 **********************************/
    @Data
    class MultipleSheetProperty {
        private List<? extends BaseRowModel> data;

        private Sheet sheet;
    }

    @Getter
    @Setter
    class ExcelListener extends AnalysisEventListener {

        private List<Object> dataList = new ArrayList<>();

        /**
         * 逐行解析
         *
         * @param object
         * @param context
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            if (object != null) {
                dataList.add(object);
            }
        }

        /**
         * 解析完所有数据后会调用这个方法
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 解析结束销毁不用的资源
        }
    }
    /************************匿名内部类结束，可以提取出去***************************/


}
































