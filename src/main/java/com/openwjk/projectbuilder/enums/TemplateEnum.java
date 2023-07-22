package com.openwjk.projectbuilder.enums;

public enum TemplateEnum {
    BASE("BASE", "base", "web,service,dao");
    private String code;
    private String directory;
    private String desc;

    TemplateEnum(String code, String directory, String desc) {
        this.code = code;
        this.directory = directory;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
