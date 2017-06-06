package com.example.shuangxiang.ysvideodemo.feedback.bean;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class FeedbackInfo {





    /**
     * content : 这个一个问题反馈的内容
     * phone : 18612345678
     *  imgPaths  : files/fd40689623344ade98550f4270b98f9
     */


    private String content;
    private String phone;
    private String imgPaths;

    public String getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(String imgPaths) {
        this.imgPaths = imgPaths;
    }

    public FeedbackInfo(String content, String phone, String imgPaths) {
        this.content = content;
        this.phone = phone;
        this.imgPaths = imgPaths;
    }
    public FeedbackInfo(String content, String phone) {
        this.content = content;
        this.phone = phone;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "FeedbackInfo{" +
                "content='" + content + '\'' +
                ", phone='" + phone + '\'' +
                ", imgPaths='" + imgPaths + '\'' +
                '}';
    }
}
