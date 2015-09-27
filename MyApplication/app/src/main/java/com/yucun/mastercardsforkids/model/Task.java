package com.yucun.mastercardsforkids.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by yucunli on 2015-09-26.
 */
public class Task {
    String objectId;
    String name;
    Boolean enabled;

    public Task(String objectId, String name, Boolean enabled) {
        this.objectId = objectId;
        this.name = name;
        this.enabled = enabled;
    }

    public Task() {
        this.objectId = "";
        this.name = "";
        this.enabled = true;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
