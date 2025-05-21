package com.exampleGroup.exampleClient.setting;

public abstract class Setting {

    String name;

    public Setting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}