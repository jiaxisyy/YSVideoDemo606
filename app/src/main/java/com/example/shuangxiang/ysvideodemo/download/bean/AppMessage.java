package com.example.shuangxiang.ysvideodemo.download.bean;

/**
 * Created by shuang.xiang on 2017/4/11.
 */

public class AppMessage {

    @Override
    public String toString() {
        return "AppMessage{" +
                "appName='" + appName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", version='" + version + '\'' +
                ", versionCode=" + versionCode +
                '}';
    }

    /**
     * appName : techray-coic
     * fileName : techray-coic.apk
     * filePath : /apkfile/techray-coic.apk
     * version : 0.1.1
     * versionCode : 2
     */

    private String appName;
    private String fileName;
    private String filePath;
    private String version;
    private int versionCode;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}
