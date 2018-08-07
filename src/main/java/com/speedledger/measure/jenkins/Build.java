package com.speedledger.measure.jenkins;

import java.util.Map;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Jenkins build.
 */

public class Build {
    public transient static final DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private String timestamp;
    private int number;
    private String jobName;
    private String result;
    private long startTime;
    private long duration;
    private long queueTime;

    public Build() {
    }

    public Build(String timestamp, int number, String jobName, String result, long startTime, long duration, long queueTime) {
        this.timestamp = timestamp;
        this.number = number;
        this.jobName = jobName;
        this.result = result;
        this.startTime = startTime;
        this.duration = duration;
        this.queueTime = queueTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = DATE_FORMATTER.format(timestamp.getTime());
    }

    public long getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(long queueTime) {
        this.queueTime = queueTime;
    }


    @Override
    public String toString() {
        return "Build{" +
                "@timestamp" + timestamp +
                ", number=" + number +
                ", jobName='" + jobName + '\'' +
                ", result='" + result + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", queueTime=" + queueTime +
                '}';
    }
}
