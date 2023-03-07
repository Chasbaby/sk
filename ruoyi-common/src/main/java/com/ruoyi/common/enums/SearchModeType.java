package com.ruoyi.common.enums;

/**
 * 搜索类型枚举
 *
 * @author Chas
 * @date 2023-2
 */
public enum SearchModeType {

    /**
     * mysql
     */
    MYSQL("mysql", "mySqlSearchStrategyImpl"),
    /**
     * elasticsearch
     */
    ELASTICSEARCH("elasticsearch", "esSearchStrategyImpl");

    /**
     * 模式
     */
    private final String mode;

    /**
     * 策略
     */
    private final String strategy;

    SearchModeType(String mode, String strategy) {
        this.mode = mode;
        this.strategy = strategy;
    }

    public String getMode() {
        return mode;
    }

    public String getStrategy() {
        return strategy;
    }

    /**
     * 获取策略
     *
     * @param mode 模式
     * @return {@link String} 搜索策略
     */
    public static String getStrategy(String mode) {
        for (SearchModeType value : SearchModeType.values()) {
            if (value.getMode().equals(mode)) {
                return value.getStrategy();
            }
        }
        return null;
    }
}
