package com.openwjk.projectbuilder.config;


import com.openwjk.projectbuilder.exception.ProjectBuilderException;
import com.openwjk.projectbuilder.help.DirFileProcHelper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author junkai.wang
 * @Title: 默认配置项
 * @Description: 默认配置项
 * @date 2023/7/5
 */
public class DefaultConfiguration {
    private String templateBasePath = "/template";

    private Map<String, String> templatePathList = new HashMap<String, String>();

    public DefaultConfiguration(){
        String templateStorePath = this.getClass().getResource(templateBasePath).getPath();
        File[] templateDirList = DirFileProcHelper.getDirListFromSourceDir(templateStorePath);
        if(null == templateDirList){
            return;
        }
        for (File templateDir : templateDirList) {
            templatePathList.put(templateDir.getName(), templateDir.getPath());
        }
    }

    public String getTemplatePath(String templateName) throws ProjectBuilderException {
        if(templatePathList.containsKey(templateName)){
            return templatePathList.get(templateName);
        }
        throw new ProjectBuilderException(String.format("Project template name not define in configuration! { template_name:%s }", templateName));
    }
}
