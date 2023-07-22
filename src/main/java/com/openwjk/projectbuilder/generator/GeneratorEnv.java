package com.openwjk.projectbuilder.generator;


import com.openwjk.projectbuilder.ProjectBuilderEngine;
import com.openwjk.projectbuilder.enums.TemplateEnum;

/**
 * @author junkai.wang
 * @Title: 创建环境生成器入口
 * @Description: 创建环境的入口
 * @date 2023/7/22
 */
public class GeneratorEnv {

    /**
     * 工程测试入口
     * @param args
     */
    public static void main(String[] args) {
//        String templateName = args[0];
//        String appName = args[1];
//        String targetBasePath = args[2];
        String templateName = TemplateEnum.BASE.getDirectory();
        String appName = "exam";
        String targetBasePath = "F:\\workspace\\java\\exam";
        ProjectBuilderEngine.createProject(appName,templateName,targetBasePath);
    }
}
