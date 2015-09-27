package com.yucun.mastercardsforkids.model;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import java.util.List;

/**
 * Created by yucunli on 2015-09-26.
 */

public class Profile {
    String objectId;
    int allowance;
    int educash;
    String card;
    List<Task> tasks;
    List<Goal> goals;

    public Profile(String objectId, int allowance, int educash, String card, List<Task> tasks, List<Goal> goals) {
        this.objectId = objectId;
        this.allowance = allowance;
        this.educash = educash;
        this.card = card;
        this.tasks = tasks;
        this.goals = goals;
    }

    public Profile() {
        this.objectId = "";
        this.allowance = 0;
        this.educash = 0;
        this.card = "";
        this.tasks = null;
        this.goals = null;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getEducash() {
        return educash;
    }

    public void setEducash(int educash) {
        this.educash = educash;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
