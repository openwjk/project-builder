package com.openwjk.projectbuilder.handle.impl;


import com.openwjk.projectbuilder.BuilderHandleContext;
import com.openwjk.projectbuilder.exception.ProjectBuilderException;
import com.openwjk.projectbuilder.handle.IBuilderHandler;
import com.openwjk.projectbuilder.help.DirFileProcHelper;

import java.io.File;
import java.util.Map;

/**
 * @author junkai.wang
 * @Title: 默认的生成器，采用IO操作解决文件夹和文件组成的模板生成项目
 * @Description: 默认的生成器，采用IO操作解决文件夹和文件组成的模板生成项目
 * @date 2023/7/13
 */
public class DefaultBuilderHandler implements IBuilderHandler {
    public static final String BUILDER_HANDLE_DEFAULT_NAME = "defalut";

    @Override
    public String getBuilderHandlerUkName() {
        return DefaultBuilderHandler.BUILDER_HANDLE_DEFAULT_NAME;
    }

    @Override
    public void builderProcess(BuilderHandleContext builderHandleContext) throws ProjectBuilderException {
        if(null == builderHandleContext){
            throw new ProjectBuilderException("Builder context is null!");
        }
        process(builderHandleContext.getSourceBasePath(), builderHandleContext);
    }

    private void process(String path, BuilderHandleContext builderHandleContext) throws ProjectBuilderException {
        File[] fileAndDirs = DirFileProcHelper.getFileAndDirListFromSourceDir(path);
        if (null == fileAndDirs) {
            return;
        }
        for(File file : fileAndDirs){
            if (file.isDirectory()) {
                String sourceAbsolutePath = file.getAbsolutePath();
                String sourceFileName = null;
                String sourceDirPath = getReplacedSourceDirPath(sourceAbsolutePath, false, sourceFileName);
                String targetDirPath = replaceSourceDirPathToTargetDirPath(sourceDirPath, builderHandleContext);
                DirFileProcHelper.makeTargetDirectory(targetDirPath);
                process(sourceDirPath,builderHandleContext);
            }else if(file.isFile()){
                String sourceAbsolutePath = file.getAbsolutePath();
                String sourceFileName = file.getName();
                String sourceDirPath = getReplacedSourceDirPath(sourceAbsolutePath, true, sourceFileName);
                String targetDirPath = replaceSourceDirPathToTargetDirPath(sourceDirPath, builderHandleContext);
                String targetFileName = sourceFileName;
                makeDirectoryAndFile(sourceDirPath, sourceFileName, targetDirPath, targetFileName, builderHandleContext);
            }
        }
    }


    /**
     * 获取路径的目录
     * @param sourceAbsolutePath
     * @param isFile
     * @param sourceFileName
     * @return
     */
    private String getReplacedSourceDirPath(String sourceAbsolutePath, boolean isFile, String sourceFileName){
        String sourceDirPath = null;
        if (isFile) {
            sourceDirPath = sourceAbsolutePath.replace("\\" + sourceFileName, "");
        }else{
            sourceDirPath = sourceAbsolutePath;
        }
        return sourceDirPath;
    }

    /**
     * 计算生成目标目录的路径
     * @param sourceDirPath
     * @return
     */
    private String replaceSourceDirPathToTargetDirPath(String sourceDirPath, BuilderHandleContext builderHandleContext) throws ProjectBuilderException {
        if(null == builderHandleContext || null == builderHandleContext.getTargetBasePath() || null == builderHandleContext.getSourceBasePath()){
            throw new ProjectBuilderException("BuilderHandleContext or sourcebasepath or targetbasepath is null!");
        }
        String result = sourceDirPath.replace(builderHandleContext.getSourceBasePath(), builderHandleContext.getTargetBasePath());

        if(null == builderHandleContext.getDirReplaceStrMap() || builderHandleContext.getDirReplaceStrMap().size()==0){
            return result;
        }
        for ( Map.Entry<String, String> entry: builderHandleContext.getDirReplaceStrMap().entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * 替换生成目标代码文件
     * @param sourceDirPath
     * @param sourceFileName
     * @param targetDirPath
     * @param targetFileName
     */
    private void makeDirectoryAndFile(String sourceDirPath,
                                      String sourceFileName,
                                      String targetDirPath,
                                      String targetFileName,
                                      BuilderHandleContext builderHandleContext) throws ProjectBuilderException {
        String sourceContent = DirFileProcHelper.readContentFromSourceFile(sourceDirPath, sourceFileName);
        String newContent = getReplacedContent(sourceContent, builderHandleContext);

        for ( Map.Entry<String, String> entry: builderHandleContext.getDirReplaceStrMap().entrySet()) {
            targetFileName = targetFileName.replace(entry.getKey(), entry.getValue());
        }

        if (DirFileProcHelper.makeTargetDirectory(targetDirPath)) {
            if (DirFileProcHelper.makeTargetFile(targetDirPath, targetFileName)) {
                DirFileProcHelper.writeNewContentToTargetFile(targetDirPath, targetFileName, newContent);
            }
        }
    }

    private String getReplacedContent(String sourceContent,
                                      BuilderHandleContext builderHandleContext) throws ProjectBuilderException {
        if(null == builderHandleContext) {
            throw new ProjectBuilderException("BuilderHandleContext is null！");
        }
        if(null == builderHandleContext.getDirReplaceStrMap() || builderHandleContext.getDirReplaceStrMap().size()==0){
            return sourceContent;
        }
        for ( Map.Entry<String, String> entry: builderHandleContext.getFileReplaceStrMap().entrySet()) {
            sourceContent = sourceContent.replace( "#{" + entry.getKey() + "}", entry.getValue());
        }
        return sourceContent;
    }
}
