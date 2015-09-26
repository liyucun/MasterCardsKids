package com.yucun.mastercardsforkids.model;

import android.media.Image;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class UserTask {
    private String taskName;
    private String taskDescription;
    private Image taskIcon;

    public String getTaskName() {
        return taskName;
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
                '}';
    }

    public UserTask(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }
}
