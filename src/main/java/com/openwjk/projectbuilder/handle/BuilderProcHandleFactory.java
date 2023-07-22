package com.openwjk.projectbuilder.handle;


import com.openwjk.projectbuilder.exception.ProjectBuilderException;
import com.openwjk.projectbuilder.handle.impl.DefaultBuilderHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author junkai.wang
 * @Title: 处理方式工厂类
 * @Description: 处理方式工厂类
 * @date 2023/7/13
 */
public class BuilderProcHandleFactory {

    private Map<String, IBuilderHandler> builderHandlerMap = new HashMap<String, IBuilderHandler>();

    private BuilderProcHandleFactory(){
        builderHandlerMap.put(DefaultBuilderHandler.BUILDER_HANDLE_DEFAULT_NAME, new DefaultBuilderHandler());
    }

    public static class BuilderProcHandleFactoryInstance{
        private static final BuilderProcHandleFactory INSTANCE = new BuilderProcHandleFactory();
    }

    public static BuilderProcHandleFactory getInstance(){
        return BuilderProcHandleFactoryInstance.INSTANCE;
    }

    public IBuilderHandler getDefaultBuilderhandle(){
        return builderHandlerMap.get(DefaultBuilderHandler.BUILDER_HANDLE_DEFAULT_NAME);
    }

    public IBuilderHandler getBuilderHandle(String builderHandleName) throws ProjectBuilderException {
        if(builderHandlerMap.containsKey(builderHandleName)){
            return builderHandlerMap.get(builderHandleName);
        }
        throw new ProjectBuilderException(String.format("BuilderHandle not define or not register! { builderHandle_name:%s }", builderHandleName));
    }
}
