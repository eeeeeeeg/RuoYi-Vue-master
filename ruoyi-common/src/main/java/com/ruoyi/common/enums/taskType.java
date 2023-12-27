package com.ruoyi.common.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * 数据分析模块内相关配置
 * 配置模块如果新增的名称是 AnalysisTypeConfigEnum 01-08则，只允许修改，不许新增，
 * 是09-10则校验数值为格式“数字-数字”
 */
public enum taskType {

    taskType1("1", "0 0 10 * * ?", "日巡检任务"),
    taskType2("2", "0 0 0 ? * MON", "周巡检任务"),
    taskType3("3", "0 0 0 1 1/1 ?", "月巡检任务"),
    taskType4("4", "0 0 0 1 1/3 ?", "季度巡检任务"),
    taskType5("5", "0 0 0 1 1 ? *", "年巡检任务"),
    taskType6("6", "", "专项巡检任务");


    private String status;
    private String confName;
    private String message;

    taskType(String status, String confName, String message) {
        this.status = status;
        this.confName = confName;
        this.message = message;
        TypeHolder.STATUS_CACHE.put(status, this);
        TypeHolder.CONFNAME_CACHE.put(status, this);
        TypeHolder.MESSAGE_CACHE.put(message, this);
    }

    public String getStatus() {
        return status;
    }

    public String getConfName() {
        return confName;
    }

    public String getMessage() {
        return message;
    }


    static class TypeHolder {
        private static final Map<String, taskType> STATUS_CACHE = new HashMap<>(6);
        private static final Map<String, taskType> CONFNAME_CACHE = new HashMap<>(6);
        private static final Map<String, taskType> MESSAGE_CACHE = new HashMap<>(6);
    }

    public static taskType fromStatus(String status) {
        return TypeHolder.STATUS_CACHE.get(status);
    }

    public static taskType fromConfName(String confName) {
        return TypeHolder.CONFNAME_CACHE.get(confName);
    }

    public static taskType fromMessage(String message) {
        return TypeHolder.MESSAGE_CACHE.get(message);
    }
}