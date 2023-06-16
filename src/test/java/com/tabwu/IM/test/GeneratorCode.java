package com.tabwu.IM.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.Collections;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/9 15:55
 * @DESCRIPTION:
 */
public class GeneratorCode {
    @Test
    public void test() {
        FastAutoGenerator.create("jdbc:mysql://123.57.193.24:3306/MI", "root", "136436")
                .globalConfig(builder -> {
                    builder.author("tabwu") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\86159\\Desktop\\IM\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.tabwu") // 设置父包名
                            .moduleName("IM") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\86159\\Desktop\\IM\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.controllerBuilder().enableRestStyle();
                    builder.addInclude("group", "msgRecord", "talkList", "user", "userFriend", "userGroup"); // 设置需要生成的表名
//                            .addTablePrefix("yls_", "yls_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
