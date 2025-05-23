package com.exampleGroup.exampleClient.setting.settings;

import com.exampleGroup.exampleClient.setting.Setting;

public class ListSetting extends Setting {

    private final String name;
    private final String[] settings;
    private String current;

    public ListSetting(String name, String[] settings, String current) {
        super(name);
        this.name = name;
        this.settings = settings;
        this.current = current;
    }

    @Override
    public String getName() {
        return name;
    }

    public String[] getSettings() {
        return settings;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getCurrent() {
        return current;
    }
}
