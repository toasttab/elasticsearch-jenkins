package com.speedledger.measure.jenkins;

import java.util.HashMap;
import java.util.List;
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
    private long timeInQueue;
    private HashMap<String, Long> timeInStages;
    private String causeOfBuild;
    private List<String> usersWithChanges;
    private long sizeOfWorkspace;
    private long sizeOfArtifacts;

    public Build() {
    }

    public Build(String timestamp, int number, String jobName, String result, long startTime, long duration,
                 long timeInQueue, HashMap<String, Long> timeInStages, String causeOfBuild, List<String> usersWithChanges,
                 long sizeOfWorkspace, long sizeOfArtifacts) {
        this.timestamp = timestamp;
        this.number = number;
        this.jobName = jobName;
        this.result = result;
        this.startTime = startTime;
        this.duration = duration;
        this.timeInQueue = timeInQueue;
        this.timeInStages = timeInStages;
        this.causeOfBuild = causeOfBuild;
        this.usersWithChanges = usersWithChanges;
        this.sizeOfWorkspace = sizeOfWorkspace;
        this.sizeOfArtifacts = sizeOfArtifacts;
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

    public long getTimeInQueue() {
        return timeInQueue;
    }

    public void setTimeInQueue(long timeInQueue) {
        this.timeInQueue = timeInQueue;
    }

    public HashMap<String, Long> getTimeInStages() {
        return timeInStages;
    }

    public void setTimeInStages(HashMap<String, Long> timeInStages) {
        this.timeInStages = timeInStages;
    }

    public String getCauseOfBuild() {
        return causeOfBuild;
    }

    public void setCauseOfBuild(String causeOfBuild) {
        this.causeOfBuild = causeOfBuild;
    }

    public List<String> getUsersWithChanges() {
        return usersWithChanges;
    }

    public void setUsersWithChanges(List<String> usersWithChanges) {
        this.usersWithChanges = usersWithChanges;
    }

    public long getSizeOfWorkspace() {
        return sizeOfWorkspace;
    }

    public void setSizeOfWorkspace(long sizeOfWorkspace) {
        this.sizeOfWorkspace = sizeOfWorkspace;
    }

    public long getSizeOfArtifacts() {
        return sizeOfArtifacts;
    }

    public void setSizeOfArtifacts(long sizeOfArtifacts) {
        this.sizeOfArtifacts = sizeOfArtifacts;
    }

    @Override
    public String toString() {
        return "Build{" +
                "@timestamp='" + timestamp + '\'' +
                ", number=" + number +
                ", jobName='" + jobName + '\'' +
                ", result='" + result + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", timeInQueue=" + timeInQueue +
                ", timeInStages=" + timeInStages +
                ", causeOfBuild='" + causeOfBuild + '\'' +
                ", usersWithChanges=" + usersWithChanges +
                ", sizeOfWorkspace=" + sizeOfWorkspace +
                ", sizeOfArtifacts=" + sizeOfArtifacts +
                '}';
    }
}
