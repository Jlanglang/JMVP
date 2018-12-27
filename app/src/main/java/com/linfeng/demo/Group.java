package com.linfeng.demo;

import java.util.ArrayList;

public class Group {
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<Group> getChild() {
        return child;
    }

    public void setChild(ArrayList<Group> child) {
        this.child = child;
    }

    public Group getPreant() {
        return preant;
    }

    public void setPreant(Group preant) {
        this.preant = preant;
    }

    public String getRemakes() {
        return remakes;
    }

    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

    String key;
    ArrayList<Group> child;//下级列表
    Group preant;//上一级
    String remakes;//备注
}
