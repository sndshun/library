package com.sndshun.library.common.config;

/**
 * @Author mr.sun
 * @Creation 2022 2022/4/15 17:43
 * @Desc:
 */
public enum UploadModuleConfig {
    /**
     * 图书封面
     */
    BOOK_COVER("book_cover","这是一个图书封面"),

    /**
     * 富文本插入图片
     */
    BOOK_CONTENT("book_content","这是一个富文本插入图片"),

    /**
     * 用户头像
     */
    USER_PROFILE("user_profile","这是一个用户头像"),
    ;

    private String moduleName;
    private String description;

    UploadModuleConfig(String moduleName, String description) {
        this.moduleName = moduleName;
        this.description = description;
    }
    public String getModuleName() {
        return moduleName;
    }

    public String getDescription() {
        return description;
    }
}
