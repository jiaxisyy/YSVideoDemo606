package com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/22.
 */

public class StatisticsInfo {

    /**
     * elementTable : {"id":"table1","deviceModelId":"model1","tableCode":"49","displayName":"运行控制","timePeriod":30,"elements":[{"id":"element1_1","tableId":"table1","number":1,"displayName":"氧气压力","address":"D212","valueType":"REAL","unit":"Kg/cm2","level":null,"processMode":null,"modbusAddress":"ANQ=","addressing":9,"addressOffset":212},{"id":"element1_2","tableId":"table1","number":2,"displayName":"累计流量","address":"D264","valueType":"REAL","unit":"m3","level":null,"processMode":null,"modbusAddress":"AQg=","addressing":9,"addressOffset":264},{"id":"element1_3","tableId":"table1","number":3,"displayName":"氧气流量","address":"D228","valueType":"REAL","unit":"L/min","level":null,"processMode":null,"modbusAddress":"AOQ=","addressing":9,"addressOffset":228},{"id":"element1_4","tableId":"table1","number":4,"displayName":"氧气浓度","address":"D244","valueType":"REAL","unit":"%","level":null,"processMode":null,"modbusAddress":"APQ=","addressing":9,"addressOffset":244},{"id":"element1_5","tableId":"table1","number":5,"displayName":"启动","address":"M10","valueType":"BOOL","unit":null,"level":null,"processMode":null,"modbusAddress":"B9o=","addressing":3,"addressOffset":10},{"id":"element1_6","tableId":"table1","number":6,"displayName":"A机（状态）","address":"M11","valueType":"BOOL","unit":null,"level":null,"processMode":null,"modbusAddress":"B9s=","addressing":3,"addressOffset":11},{"id":"element1_7","tableId":"table1","number":7,"displayName":"停止","address":"M101","valueType":"BOOL","unit":null,"level":null,"processMode":null,"modbusAddress":"CDU=","addressing":3,"addressOffset":101}]}
     * list : [{"date":"1465797600000","D264":1.8224299065420562},{"date":"1465801200000","D264":5},{"date":"1465804800000","D264":4.7727272727272725},{"date":"1465808400000","D264":0},{"date":"1465812000000","D264":0},{"date":"1465815600000","D264":0}]
     */

    private ElementTableBean elementTable;
    private List<ListBean> list;

    public ElementTableBean getElementTable() {
        return elementTable;
    }

    public void setElementTable(ElementTableBean elementTable) {
        this.elementTable = elementTable;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ElementTableBean {
        /**
         * id : table1
         * deviceModelId : model1
         * tableCode : 49
         * displayName : 运行控制
         * timePeriod : 30
         * elements : [{"id":"element1_1","tableId":"table1","number":1,"displayName":"氧气压力","address":"D212","valueType":"REAL","unit":"Kg/cm2","level":null,"processMode":null,"modbusAddress":"ANQ=","addressing":9,"addressOffset":212},{"id":"element1_2","tableId":"table1","number":2,"displayName":"累计流量","address":"D264","valueType":"REAL","unit":"m3","level":null,"processMode":null,"modbusAddress":"AQg=","addressing":9,"addressOffset":264},{"id":"element1_3","tableId":"table1","number":3,"displayName":"氧气流量","address":"D228","valueType":"REAL","unit":"L/min","level":null,"processMode":null,"modbusAddress":"AOQ=","addressing":9,"addressOffset":228},{"id":"element1_4","tableId":"table1","number":4,"displayName":"氧气浓度","address":"D244","valueType":"REAL","unit":"%","level":null,"processMode":null,"modbusAddress":"APQ=","addressing":9,"addressOffset":244},{"id":"element1_5","tableId":"table1","number":5,"displayName":"启动","address":"M10","valueType":"BOOL","unit":null,"level":null,"processMode":null,"modbusAddress":"B9o=","addressing":3,"addressOffset":10},{"id":"element1_6","tableId":"table1","number":6,"displayName":"A机（状态）","address":"M11","valueType":"BOOL","unit":null,"level":null,"processMode":null,"modbusAddress":"B9s=","addressing":3,"addressOffset":11},{"id":"element1_7","tableId":"table1","number":7,"displayName":"停止","address":"M101","valueType":"BOOL","unit":null,"level":null,"processMode":null,"modbusAddress":"CDU=","addressing":3,"addressOffset":101}]
         */

        private String id;
        private String deviceModelId;
        private String tableCode;
        private String displayName;
        private int timePeriod;
        private List<ElementsBean> elements;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceModelId() {
            return deviceModelId;
        }

        public void setDeviceModelId(String deviceModelId) {
            this.deviceModelId = deviceModelId;
        }

        public String getTableCode() {
            return tableCode;
        }

        public void setTableCode(String tableCode) {
            this.tableCode = tableCode;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public int getTimePeriod() {
            return timePeriod;
        }

        public void setTimePeriod(int timePeriod) {
            this.timePeriod = timePeriod;
        }

        public List<ElementsBean> getElements() {
            return elements;
        }

        public void setElements(List<ElementsBean> elements) {
            this.elements = elements;
        }

        public static class ElementsBean {
            /**
             * id : element1_1
             * tableId : table1
             * number : 1
             * displayName : 氧气压力
             * address : D212
             * valueType : REAL
             * unit : Kg/cm2
             * level : null
             * processMode : null
             * modbusAddress : ANQ=
             * addressing : 9
             * addressOffset : 212
             */

            private String id;
            private String tableId;
            private int number;
            private String displayName;
            private String address;
            private String valueType;
            private String unit;
            private Object level;
            private Object processMode;
            private String modbusAddress;
            private int addressing;
            private int addressOffset;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTableId() {
                return tableId;
            }

            public void setTableId(String tableId) {
                this.tableId = tableId;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getValueType() {
                return valueType;
            }

            public void setValueType(String valueType) {
                this.valueType = valueType;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public Object getLevel() {
                return level;
            }

            public void setLevel(Object level) {
                this.level = level;
            }

            public Object getProcessMode() {
                return processMode;
            }

            public void setProcessMode(Object processMode) {
                this.processMode = processMode;
            }

            public String getModbusAddress() {
                return modbusAddress;
            }

            public void setModbusAddress(String modbusAddress) {
                this.modbusAddress = modbusAddress;
            }

            public int getAddressing() {
                return addressing;
            }

            public void setAddressing(int addressing) {
                this.addressing = addressing;
            }

            public int getAddressOffset() {
                return addressOffset;
            }

            public void setAddressOffset(int addressOffset) {
                this.addressOffset = addressOffset;
            }
        }
    }

    public static class ListBean {
        /**
         * date : 1465797600000
         * D264 : 1.8224299065420562
         */

        private String date;
        @SerializedName("D264")
        private double value;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}
