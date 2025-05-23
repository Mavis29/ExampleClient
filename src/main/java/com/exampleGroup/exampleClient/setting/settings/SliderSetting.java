package com.exampleGroup.exampleClient.setting.settings;

import com.exampleGroup.exampleClient.setting.Setting;

public class SliderSetting extends Setting {

    private final String name;
    private double min, max, current;

    public SliderSetting(String name, double min, double max, double current) {
        super(name);
        this.name = name;
        this.min = min;
        this.max = max;
        this.current = current;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

}
