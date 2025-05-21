package com.exampleGroup.exampleClient.setting.settings;

import com.exampleGroup.exampleClient.module.Module;
import com.exampleGroup.exampleClient.setting.Setting;

public class ToggleSetting extends Setting {

    private final String name;
    private boolean isEnabled;
    private Module module;

    public ToggleSetting(String name, boolean isEnabled) {
        super(name);
        this.name = name;
        this.isEnabled = isEnabled;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
