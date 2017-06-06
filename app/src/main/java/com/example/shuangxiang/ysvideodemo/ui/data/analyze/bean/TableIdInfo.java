package com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean;

/**
 * Created by shuang.xiang on 2017/5/22.
 */

public class TableIdInfo {

    /**
     * changeSend : true
     * dataTemplateId : c5924c60446f412eb74d3b8e04163d31
     * id : 193887ee21ac4eca857b73d8e36f32bd
     * name : 报警表
     * tableNo : 2
     * tenantId : b3ea4c5282944b41970bdee5ae7ad2b9
     * timePeriod : 60
     */

    private boolean changeSend;
    private String dataTemplateId;
    private String id;
    private String name;
    private int tableNo;
    private String tenantId;
    private int timePeriod;

    public boolean isChangeSend() {
        return changeSend;
    }

    public void setChangeSend(boolean changeSend) {
        this.changeSend = changeSend;
    }

    public String getDataTemplateId() {
        return dataTemplateId;
    }

    public void setDataTemplateId(String dataTemplateId) {
        this.dataTemplateId = dataTemplateId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }
}
