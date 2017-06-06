package com.example.shuangxiang.ysvideodemo.ui.home.product.bean;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/24.
 */

public class ProductInfo {

    /**
     * endRow : 5
     * firstPage : 1
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * lastPage : 1
     * list : [{"description":"网线实现分布式控制、组合形式多样化、产品型号丰富，满足客户不同需求、芯片级的设计、与主模块连接，通过云端可实现远程监控或者控制","imgPath":"files/2f529a8bac4b464fabd90e2e0f9cf27e","name":"KR"},{"description":"安卓系统、带PLC功能、通讯功能高度整合、可带定位功能、本机IO灵活配置、网口扩展远程IO、汽车级电容触摸屏工业级显示屏入和继电器输出、可带定位功能、体积小巧 ","imgPath":"files/23b613c63b68405bbf8587541ca2a520","name":"KP"},{"description":"通讯功能高度整合、可带定位功能、网口扩展远程IO、体积小巧、功能强悍","imgPath":"files/3fa96c82d2f94ec3b6c9b3383a5a86b4","name":"KT"},{"description":":可编程，碾压一般的DTU和RTU、通讯功能灵活配置、带数字输","imgPath":"files/0430f57a7e114fd5851f8d2a08878021","name":"KN"},{"description":"网线扩展、可支持网线供电、执行与驱动一体化，减少布线成本","imgPath":"files/18f4e88472934619bcba0ccf4e779ef5","name":"KC"}]
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * orderBy : null
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * prePage : 0
     * size : 5
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
         * description : 网线实现分布式控制、组合形式多样化、产品型号丰富，满足客户不同需求、芯片级的设计、与主模块连接，通过云端可实现远程监控或者控制
         * imgPath : files/2f529a8bac4b464fabd90e2e0f9cf27e
         * name : KR
         */

        private String description;
        private String imgPath;
        private String name;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
