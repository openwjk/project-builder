package com.openwjk.projectbuilder.exception;

/**
 * @author junkai.wang
 * @Title: ProjectBuilderException
 * @Description: 模板创建的统一异常基类
 * @date 2023/7/8
 */
public class ProjectBuilderException extends Exception {
    public ProjectBuilderException(String message){ super(message); }

    public ProjectBuilderException(Throwable cause) {
        super(cause);
    }

    public ProjectBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
