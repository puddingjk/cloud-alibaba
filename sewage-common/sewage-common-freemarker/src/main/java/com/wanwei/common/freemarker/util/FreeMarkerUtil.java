package com.wanwei.common.freemarker.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

/**
 * @ClassName : FreeMarkerUtil
 * @Description :
 * @Author : LuoHongyu
 * @Date : 2021-10-19 22:20
 */
@Slf4j
public class FreeMarkerUtil {

    public static final String UTF_8 = "UTF-8";

    /***
     *
     * @author luoHongyu
     * @date 2021-10-23 14:57
     * @param dataMap 待填充数据
     * @param templateName 模板文件名称
     * @param filePath 模板文件路径
     * @param fileName 生成的 word 文件名称
     * @return void
     */
    public static void createWord(Map dataMap, String templateName, String filePath, String fileName) {
        try {
            // 创建配置实例
            Configuration configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            // 设置编码
            configuration.setDefaultEncoding("UTF-8");
            // ftl模板文件
            configuration.setClassForTemplateLoading(FreeMarkerUtil.class, "/templates/");
            // 获取模板
            Template template = configuration.getTemplate(templateName);
            // 输出文件
            File outFile = new File(filePath + File.separator + fileName);
            // 如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            // 将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), UTF_8));
            // 生成文件
            template.process(dataMap, out);
            // 关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("模板导出失败：{}",e);
        }
    }

    /**
     * 生成 word 文件
     *
     * @param dataMap      待填充数据
     * @param templateName 模板文件名称
     * @param filePath     模板文件路径
     * @param fileName     生成的 word 文件名称
     * @param response     响应流
     */
    public static void createWord(Map dataMap, String templateName, String filePath, String fileName, HttpServletResponse response) {
        // 创建配置实例
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        // 设置编码
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        // ftl模板文件
        configuration.setClassForTemplateLoading(FreeMarkerUtil.class, filePath);
        try {
            // 获取模板
            Template template = configuration.getTemplate(templateName);
            fileName = new String(fileName.getBytes(), "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            // 定义输出类型
            response.setContentType("application/msword");
            Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            // 生成文件
            template.process(dataMap, out);
            out.flush();
            out.close();
        } catch (Exception e) {
           log.error("模板导出失败：{}",e);
        }
    }
}
