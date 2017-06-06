package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class MyDeviceInfo {

    /**
     * endRow : 1
     * firstPage : 1
     * hasNextPage : true
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : false
     * lastPage : 5
     * list : [{"addr":"","area":null,"cameraId":"","compute":null,"createBy":"92ca19eb-387a-4e8b-a2f3-df4b0f8103a8","createDate":"2017-05-07 11:01:34","dataTemplateId":"f23d45d16e96456ba88130ebf6ed7407","defaultTableId":null,"delFlag":"0","deviceNo":1,"id":"a49ee15e45e2465b9d419cdc8028e771","latitude":"","longitude":"","name":"TEST1","onlineStatus":"OFFLINE","orgId":"","picPath":null,"plcId":"deviceId0174","relatedDeviceId":null,"scadaId":"","statusName":null,"tenantId":"b3ea4c5282944b41970bdee5ae7ad2b9","updateBy":"92ca19eb-387a-4e8b-a2f3-df4b0f8103a8","updateDate":"2017-05-08 08:45:17"}]
     * navigatePages : 8
     * navigatepageNums : [1,2,3,4,5]
     * nextPage : 2
     * orderBy : null
     * pageNum : 1
     * pageSize : 1
     * pages : 5
     * prePage : 0
     * size : 1
     * startRow : 1
     * total : 5
     */

    private int endRow;
    private int firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private int navigatePages;
    private int nextPage;
    private Object orderBy;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int prePage;
    private int size;
    private int startRow;
    private int total;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public Object getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Object orderBy) {
        this.orderBy = orderBy;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
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

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
        /**
         * addr :
         * area : null
         * cameraId :
         * compute : null
         * createBy : 92ca19eb-387a-4e8b-a2f3-df4b0f8103a8
         * createDate : 2017-05-07 11:01:34
         * dataTemplateId : f23d45d16e96456ba88130ebf6ed7407
         * defaultTableId : null
         * delFlag : 0
         * deviceNo : 1
         * id : a49ee15e45e2465b9d419cdc8028e771
         * latitude :
         * longitude :
         * name : TEST1
         * onlineStatus : OFFLINE
         * orgId :
         * picPath : null
         * plcId : deviceId0174
         * relatedDeviceId : null
         * scadaId :
         * statusName : null
         * tenantId : b3ea4c5282944b41970bdee5ae7ad2b9
         * updateBy : 92ca19eb-387a-4e8b-a2f3-df4b0f8103a8
         * updateDate : 2017-05-08 08:45:17
         */

        private String addr;
        private Object area;
        private String cameraId;
        private Object compute;
        private String createBy;
        private String createDate;
        private String dataTemplateId;
        private Object defaultTableId;
        private String delFlag;
        private int deviceNo;
        private String id;
        private String latitude;
        private String longitude;
        private String name;
        private String onlineStatus;
        private String orgId;
        private Object picPath;
        private String plcId;
        private Object relatedDeviceId;
        private String scadaId;
        private Object statusName;
        private String tenantId;
        private String updateBy;
        private String updateDate;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public String getCameraId() {
            return cameraId;
        }

        public void setCameraId(String cameraId) {
            this.cameraId = cameraId;
        }

        public Object getCompute() {
            return compute;
        }

        public void setCompute(Object compute) {
            this.compute = compute;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getDataTemplateId() {
            return dataTemplateId;
        }

        public void setDataTemplateId(String dataTemplateId) {
            this.dataTemplateId = dataTemplateId;
        }

        public Object getDefaultTableId() {
            return defaultTableId;
        }

        public void setDefaultTableId(Object defaultTableId) {
            this.defaultTableId = defaultTableId;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public int getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(int deviceNo) {
            this.deviceNo = deviceNo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(String onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public Object getPicPath() {
            return picPath;
        }

        public void setPicPath(Object picPath) {
            this.picPath = picPath;
        }

        public String getPlcId() {
            return plcId;
        }

        public void setPlcId(String plcId) {
            this.plcId = plcId;
        }

        public Object getRelatedDeviceId() {
            return relatedDeviceId;
        }

        public void setRelatedDeviceId(Object relatedDeviceId) {
            this.relatedDeviceId = relatedDeviceId;
        }

        public String getScadaId() {
            return scadaId;
        }

        public void setScadaId(String scadaId) {
            this.scadaId = scadaId;
        }

        public Object getStatusName() {
            return statusName;
        }

        public void setStatusName(Object statusName) {
            this.statusName = statusName;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    }
}
