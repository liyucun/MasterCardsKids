package com.yucun.mastercardsforkids.model;

import android.media.Image;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class UserTask {
    private String taskName;
    private String taskDescription;
    private Image taskIcon;
    private boolean status=false;
    public String getTaskName() {
        return taskName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;

    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Image getTaskIcon() {
        return taskIcon;
    }

    public void setTaskIcon(Image taskIcon) {
        this.taskIcon = taskIcon;
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskIcon=" + taskIcon +
                ", status=" + status +
                '}';
    }

    public UserTask(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }
}
