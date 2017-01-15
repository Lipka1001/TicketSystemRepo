package com.kubistalipowska.ticketsystem.entities;

/**
 * Created by wilek on 2017-01-15.
 */
public class ItemEntity {
    private String fielnd_name;
    private String value;

    public ItemEntity(String fielnd_name, String value) {
        this.fielnd_name = fielnd_name;
        this.value = value;
    }

    public String getFielnd_name() {
        return fielnd_name;
    }

    public void setFielnd_name(String fielnd_name) {
        this.fielnd_name = fielnd_name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
