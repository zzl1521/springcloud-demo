package com.my.demo.springcloud.rabbitmq.base;

/**
 * Created by zhangzhile on 2018/4/8.
 */
public class TaskMessage {

    private String message;
    private long period;    //间隔时间 秒
    private int periodMultiple= 1;  //间隔时间倍数
    private int totalCount;   //总次数
    private int executeCount; //已执行次数

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public int getPeriodMultiple() {
        return periodMultiple;
    }

    public void setPeriodMultiple(int periodMultiple) {
        this.periodMultiple = periodMultiple;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(int executeCount) {
        this.executeCount = executeCount;
    }
}
