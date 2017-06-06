package com.example.shuangxiang.ysvideodemo.ui.warning.record.bean;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningInfo {

    /**
     * elementTable : null
     * list : [{"elementId":"64c0ad9f8f2843048ea26ba5fee49550","level":"1","latitude":"22.736599","createDateStr":"2017-03-26 16:50:13","uint":"","deviceId":"2dd47401cbcc4cb790dc85785f880bd8","deviceName":"物联网模块","orgIds":[],"tenantId":"sw23a5c0a36421cacf34s2zd332973a","name":"SPD状态信\u2014空开状态 报警消失","id":"e4968278-9d0c-4e7e-b412-b1f12d0fb574","value":false,"status":"1","longitude":"113.861494","createDate":1490518213334},{"elementId":"80f8b13d6c7f4db7aafca4d9ea82af50","level":"1","latitude":"22.736599","createDateStr":"2017-03-26 16:50:13","uint":"","deviceId":"2dd47401cbcc4cb790dc85785f880bd8","deviceName":"物联网模块","orgIds":[],"tenantId":"sw23a5c0a36421cacf34s2zd332973a","name":"SPD状态信息\u2014接地状态 报警消失","id":"e75121a8-2312-49d2-a29f-aef9a411ea68","value":false,"status":"1","longitude":"113.861494","createDate":1490518213323},{"elementId":"123064cd3d134642be4fe1542b14279f","level":"1","latitude":"22.736599","createDateStr":"2017-03-26 16:50:13","uint":"","deviceId":"2dd47401cbcc4cb790dc85785f880bd8","deviceName":"物联网模块","orgIds":[],"tenantId":"sw23a5c0a36421cacf34s2zd332973a","name":"SPD状态信息\u2014劣化状态 报警消失","id":"96a2041d-afb8-4b3f-988c-a0fe5f5c72d2","value":false,"status":"1","longitude":"113.861494","createDate":1490518213310},{"elementId":"64c0ad9f8f2843048ea26ba5fee49550","level":"1","latitude":"22.736599","createDateStr":"2017-03-26 16:47:58","uint":"","deviceId":"2dd47401cbcc4cb790dc85785f880bd8","deviceName":"物联网模块","orgIds":[],"tenantId":"sw23a5c0a36421cacf34s2zd332973a","name":"SPD状态信\u2014空开状态","id":"19c48361-b04d-4b2e-b72f-ff9aa98b6e49","value":true,"status":"1","longitude":"113.861494","createDate":1490518078018},{"elementId":"80f8b13d6c7f4db7aafca4d9ea82af50","level":"1","latitude":"22.736599","createDateStr":"2017-03-26 14:59:12","uint":"","deviceId":"2dd47401cbcc4cb790dc85785f880bd8","deviceName":"物联网模块","orgIds":[],"tenantId":"sw23a5c0a36421cacf34s2zd332973a","name":"SPD状态信息\u2014接地状态","id":"ea3e52ba-6bf2-4711-904c-66556b63c371","value":true,"status":"1","longitude":"113.861494","createDate":1490511552541},{"elementId":"123064cd3d134642be4fe1542b14279f","level":"1","latitude":"22.736599","createDateStr":"2017-03-26 14:59:12","uint":"","deviceId":"2dd47401cbcc4cb790dc85785f880bd8","deviceName":"物联网模块","orgIds":[],"tenantId":"sw23a5c0a36421cacf34s2zd332973a","name":"SPD状态信息\u2014劣化状态","id":"05584f53-1e2f-41a4-a4f0-5dfa44041e97","value":true,"status":"1","longitude":"113.861494","createDate":1490511552051}]
     * pageNum : 1
     * pageSize : 10
     * total : 6
     */

    private Object elementTable;
    private int pageNum;
    private int pageSize;
    private int total;
    private List<ListBean> list;

    public Object getElementTable() {
        return elementTable;
    }

    public void setElementTable(Object elementTable) {
        this.elementTable = elementTable;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * elementId : 64c0ad9f8f2843048ea26ba5fee49550
         * level : 1
         * latitude : 22.736599
         * createDateStr : 2017-03-26 16:50:13
         * uint :
         * deviceId : 2dd47401cbcc4cb790dc85785f880bd8
         * deviceName : 物联网模块
         * orgIds : []
         * tenantId : sw23a5c0a36421cacf34s2zd332973a
         * name : SPD状态信—空开状态 报警消失
         * id : e4968278-9d0c-4e7e-b412-b1f12d0fb574
         * value : false
         * status : 1
         * longitude : 113.861494
         * createDate : 1490518213334
         */

        private String elementId;
        private String level;
        private String latitude;
        private String createDateStr;
        private String uint;
        private String deviceId;
        private String deviceName;
        private String tenantId;
        private String name;
        private String id;
        private boolean value;
        private String status;
        private String longitude;
        private long createDate;
        private List<?> orgIds;

        public String getElementId() {
            return elementId;
        }

        public void setElementId(String elementId) {
            this.elementId = elementId;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getCreateDateStr() {
            return createDateStr;
        }

        public void setCreateDateStr(String createDateStr) {
            this.createDateStr = createDateStr;
        }

        public String getUint() {
            return uint;
        }

        public void setUint(String uint) {
            this.uint = uint;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public List<?> getOrgIds() {
            return orgIds;
        }

        public void setOrgIds(List<?> orgIds) {
            this.orgIds = orgIds;
        }
    }
}
