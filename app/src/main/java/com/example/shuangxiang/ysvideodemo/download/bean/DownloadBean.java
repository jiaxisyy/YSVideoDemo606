package com.example.shuangxiang.ysvideodemo.download.bean;

/**
 * Created by shuang.xiang on 2017/5/3.
 */

public class DownloadBean {
    private long total;
    private long bytesReaded;
    public DownloadBean(long total, long bytesReaded) {
        this.total = total;
        this.bytesReaded = bytesReaded;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getBytesReaded() {
        return bytesReaded;
    }

    public void setBytesReaded(long bytesReaded) {
        this.bytesReaded = bytesReaded;
    }
}
