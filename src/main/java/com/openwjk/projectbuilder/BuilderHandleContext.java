package com.openwjk.projectbuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author junkai.wang
 * @Title: 生成处理过程的上下文
 * @Description: 生成处理过程的上下文
 * @date 2023/7/13
 */
public class BuilderHandleContext {
    /**
     * 模板路径地址
     */
    private String sourceBasePath;

    /**
     * 输出路径地址
     */
    private String targetBasePath;

    /**
     * 目录替换参量
     */
    private Map<String, String> dirReplaceStrMap = new HashMap<String, String>();

    /**
     * 文件替换参量
     */
    private Map<String, String> fileReplaceStrMap = new HashMap<String, String>();

    public String getSourceBasePath() {
        return sourceBasePath;
    }

    public void setSourceBasePath(String sourceBasePath) {
        this.sourceBasePath = sourceBasePath;
    }

    public String getTargetBasePath() {
        return targetBasePath;
    }

    public void setTargetBasePath(String targetBasePath) {
        this.targetBasePath = targetBasePath;
    }

    public Map<String, String> getDirReplaceStrMap() {
        return dirReplaceStrMap;
    }

    public void setDirReplaceStrMap(Map<String, String> dirReplaceStrMap) {
        this.dirReplaceStrMap = dirReplaceStrMap;
    }

    public Map<String, String> getFileReplaceStrMap() {
        return fileReplaceStrMap;
    }

    public void setFileReplaceStrMap(Map<String, String> fileReplaceStrMap) {
        this.fileReplaceStrMap = fileReplaceStrMap;
    }
}
