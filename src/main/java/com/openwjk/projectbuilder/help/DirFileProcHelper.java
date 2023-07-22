package com.openwjk.projectbuilder.help;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author junkai.wang
 * @Title: 目录和文件处理工具类
 * @Description: 目录和文件处理工具类
 * @date 2023/7/5
 */
public class DirFileProcHelper {
    /**
     * 获取指定目录下第一级文件和目录
     * @param sourceDirPath
     * @return
     */
    public static File[] getFileAndDirListFromSourceDir(String sourceDirPath){
        File file = new File(sourceDirPath);
        File[] fileList = file.listFiles();
        return fileList;
    }

    /**
     * 获得指定目录下第一级目录
     * @param sourceDirPath
     * @return
     */
    public static File[] getDirListFromSourceDir(String sourceDirPath){
        File[] dirs = getFileAndDirListFromSourceDir(sourceDirPath);
        if (null == dirs) {
            return null;
        }
        List<File> resDirs = new ArrayList<File>();
        for (File dir : dirs) {
            if(dir.isDirectory()){
                resDirs.add(dir);
            }
        }
        return resDirs.toArray(new File[resDirs.size()]);
    }

    /**
     * 创建目录
     * @param dirPath
     */
    public static boolean makeTargetDirectory(String dirPath){
        try {
            File file =new File(dirPath);
            if  (!file .exists()  && !file.isDirectory()){
                file .mkdirs();
                System.out.println(dirPath);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 创建文件
     * @param dirPath
     * @param fileName
     */
    public static boolean makeTargetFile(String dirPath, String fileName){
        try {
            File file = new File(dirPath + "\\" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 读出文件中所有内容
     * @param sourceDirPath
     * @param sourceFileName
     * @return
     */
    public static String readContentFromSourceFile(String sourceDirPath, String sourceFileName){
        String encoding = "utf-8";
        File file = new File(sourceDirPath + "\\" + sourceFileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将指定内容写入文本中
     * @param targetDirPath
     * @param targetFileName
     * @param newContent
     */
    public static void writeNewContentToTargetFile(String targetDirPath, String targetFileName, String newContent){
        FileWriter fw = null;
        try {
            fw = new FileWriter(targetDirPath + "\\" + targetFileName);
            fw.write(newContent);
            System.out.println(targetDirPath + "\\" + targetFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
