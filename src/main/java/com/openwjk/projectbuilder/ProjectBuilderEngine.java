package com.openwjk.projectbuilder;


import com.openwjk.projectbuilder.config.DefaultConfiguration;
import com.openwjk.projectbuilder.exception.ProjectBuilderException;
import com.openwjk.projectbuilder.handle.IBuilderHandler;
import com.openwjk.projectbuilder.handle.BuilderProcHandleFactory;
import com.openwjk.projectbuilder.help.StringProcHelper;

/**
 * @author junkai.wang
 * @Title: ProjectBuilderEngine
 * @Description: 项目创建的引擎入口
 * @date 2023/7/6
 */
public class ProjectBuilderEngine {

    private DefaultConfiguration defaultConfiguration;

    public static class ProjectBuilderEngineInstance{
        private static final ProjectBuilderEngine INSTANCE = new ProjectBuilderEngine();
    }

    private ProjectBuilderEngine(){
        defaultConfiguration = new DefaultConfiguration();
    }

    /**
     * 引擎功能-根据模板类型、模板名称创建项目，并输出到指定目录
     * @param appProjectName
     * @param appTemplateName
     * @param targetBasePath
     */
    public static void createProject(String appProjectName,
                                     String appTemplateName,
                                     String targetBasePath){
        try {
            String templateBasePath = ProjectBuilderEngineInstance.INSTANCE.defaultConfiguration.getTemplatePath(appTemplateName);
            BuilderHandleContext builderHandleContext = new BuilderHandleContext();

            builderHandleContext.getDirReplaceStrMap().put("appProjectName", appProjectName);
            builderHandleContext.getDirReplaceStrMap().put("appProjectGroupName", StringProcHelper.keepEnglishChar(appProjectName));
            builderHandleContext.getDirReplaceStrMap().put("appProjectWaveName", StringProcHelper.keepEnglishFirstUpCase(appProjectName));

            builderHandleContext.getFileReplaceStrMap().put("appProjectName", appProjectName);
            builderHandleContext.getFileReplaceStrMap().put("appProjectGroupName", StringProcHelper.keepEnglishChar(appProjectName));
            builderHandleContext.getFileReplaceStrMap().put("appProjectWaveName", StringProcHelper.keepEnglishFirstUpCase(appProjectName));

            builderHandleContext.setSourceBasePath(templateBasePath);
            builderHandleContext.setTargetBasePath(targetBasePath);
            ProjectBuilderEngineInstance.INSTANCE.createProjectIns(builderHandleContext);
        } catch (ProjectBuilderException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过递归的手段将模板创建项目实例
     * @param builderHandleContext
     * @throws ProjectBuilderException
     */
    private void createProjectIns(BuilderHandleContext builderHandleContext) throws ProjectBuilderException {
        IBuilderHandler builderHandler = BuilderProcHandleFactory.getInstance().getDefaultBuilderhandle();
        builderHandler.builderProcess(builderHandleContext);
    }
}
