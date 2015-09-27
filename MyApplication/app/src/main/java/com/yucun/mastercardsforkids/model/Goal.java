package com.yucun.mastercardsforkids.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by yucunli on 2015-09-26.
 */

public class Goal{
    String objectId;
    String name;
    int amount;

    public Goal(String objectId, String name, int amount) {
        this.objectId = objectId;
        this.name = name;
        this.amount = amount;
    }

    public Goal() {
        this.objectId = "";
        this.name = "";
        this.amount = 0;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
