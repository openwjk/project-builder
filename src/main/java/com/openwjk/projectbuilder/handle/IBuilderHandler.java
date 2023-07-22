package com.openwjk.projectbuilder.handle;


import com.openwjk.projectbuilder.BuilderHandleContext;
import com.openwjk.projectbuilder.exception.ProjectBuilderException;

/**
 * @author junkai.wang
 * @Title: 处理逻辑基类接口
 * @Description: 处理逻辑基类接口
 * @date 2023/7/13
 */
public interface IBuilderHandler {
    String getBuilderHandlerUkName();

    void builderProcess(BuilderHandleContext builderHandleContext) throws ProjectBuilderException;
}
