package com.yucun.mastercardsforkids.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by yucunli on 2015-09-26.
 */
public class Task {
    String objectId;
    String name;

    public Task(String objectId, String name) {
        this.objectId = objectId;
        this.name = name;
    }

    public Task() {
        this.objectId = "";
        this.name = "";
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
