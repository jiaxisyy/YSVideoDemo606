package com.example.shuangxiang.ysvideodemo.common;

/**
 * Created by shuang.xiang on 2017/4/6.
 * 常量表
 */

public class Constants {

    public interface Define {
        boolean ISLOGTEST = true;
        String COOKIE = "cookie";
        String UPDATE_FAILED = "更新失败";
        String MYSERVICE_TITLE = "我的客服";
        String FIRST_START = "first_start";
        String USERNAME = "username";
        String PASSWORD = "password";
        String ADMINPASSWORD = "admin_password";
        String ADMINPASSWORDERROR = "管理员密码错误,请重新输入";

        String DEVICELIST = "设备列表";
        String DEVICEMAP = "设备地图";
        String MONITORING_MAIN = "主监控";
        String MONITORING_FLOW = "流程监控";
        String MONITORING_VIDEO = "视频监控";

        long START_TIME = 3000;//启动页跳转时间为3秒
//        String BASE_URL = "http://58.250.204.112:58010/userconsle/";//外网
        String BASE_URL = "http://kawakp.chinclouds.com/userconsle/";//阿里云
        //内网
//    private static final String BASEURL = "http://10.199.198.55:58010/userconsle/";
        //外网
//    private static final String BASEURL = "http://58.250.204.112:58010/userconsle/";


        //设备列表的默认设置
        int DEFAULTPAGENUM = 1;
        int DEFAULTPAGESIZE = 100;
        int MAXPAGESIZE = 100;
        String WARNINGMAP = "预警地图";
        String WARNINGRECORD = "预警记录";
        int CONNECT_TIME_OUT = 5 * 1000 * 60;
        int RXBUS_COOKIE_CODE = 1;
        int RXBUS_MYDEVICELISTP_CODE = 2;
        int RXBUS_MYDEVICEMAP_TO_DATASHOW_CODE = 3;
        String MYDEVICE_TO_SECONDHOME_ID = "mydevice_to_secondhome_id";//设备列表返回的ID
        String MYDEVICE_TO_SECONDHOME_DATATEMPLATEID = "mydevice_to_secondhome_datatemplateid";
        String MYDEVICE_TO_SECONDHOME_TBTITLE = "mydevice_to_secondhome_tbtitle";
        int DATASHOW_CENTER_SPACINGINPIXELS = 80;
        String SERVERDATAERROR = "服务器数据错误";
        String SERVERQUERSTERROR = "服务器请求失败";
        String ANALYZETIMETYPE_HOUR = "1h";
        String ANALYZETIMETYPE_DAY = "1d";
        String ANALYZETIMETYPE_WEEK = "1w";
        String ANALYZETIMETYPE_MONTH = "1M";
        String ANALYZETIMETYPE_QUARTER = "1q";
        String ANALYZETIMETYPE_YEAR = "1y";
        String JSONARRAYNAME = "list";
        String JSON_NAN = "NaN";
        int WARNINGRECORDTYPE_ALL = 0;//预警监测所有的设备
        int WARNINGRECORDTYPE_ONE = 1;//预警监测单独的设备


        String SEARCH_NOTHING = "无符合搜索条件的信息";
        String STARTDOWNLOADFILE = "开始下载文件";
        //*****************数据获取时间长短
        int REFRESH_INTERVAL = 2;//刷新数据间隔
        String APK_NAME = "kawa";
    }

}
