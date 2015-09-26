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
}
