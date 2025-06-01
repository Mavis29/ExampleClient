package com.exampleGroup.exampleClient.config;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.logging.Logger;
import com.exampleGroup.exampleClient.module.Module;
import com.exampleGroup.exampleClient.setting.Setting;
import com.exampleGroup.exampleClient.setting.settings.ListSetting;
import com.exampleGroup.exampleClient.setting.settings.SliderSetting;
import com.exampleGroup.exampleClient.setting.settings.ToggleSetting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.ArrayList;

public class ConfigManager {

    private File dir;
    
    private String currentConfigName;
    private Configuration currentConfig;

    public ConfigManager() {
        dir = new File(Loader.instance().getConfigDir(), "ExampleClient");
        loadDir(dir);
        try {
            createConfig("default.cfg");
        } catch (Exception e) {
            loadConfig("default.cfg");
        }
    }

    public void createConfig(String fileName) throws Exception {
        File configFile;
        Configuration config;

        configFile = new File(dir, fileName);
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new Exception("File already exists.");
        }

        config = new Configuration(configFile);
        config.load();
        currentConfig = config;
        currentConfigName = fileName;

        reload();
        save(config);
    }

    public void loadConfig(String configFileName) {
        File configFile;
        Configuration config;

        configFile = new File(dir, configFileName);
        if (!configFile.exists()) {
            Logger.sendChatMessage("§cCouldn't load the config.");
            return;
        }

        config = new Configuration(configFile);
        config.load();
        currentConfig = config;
        currentConfigName = config.getConfigFile().getName();

        reload();
        save(config);

        Logger.sendChatMessage("§aSet config to §o" + currentConfigName + "§r§a.");
    }

    public void reload() {
        for (Module module : ExampleClient.MODULE_MANAGER.getModules()) {
            Property property = currentConfig.get(module.getName(), "enabled", false);
            for (Setting setting : module.getSettings()) {
                if (setting instanceof ToggleSetting) {
                    property = currentConfig.get(module.getName(), setting.getName(), ((ToggleSetting) setting).isEnabled());
                } else if (setting instanceof SliderSetting) {
                    property = currentConfig.get(module.getName(), setting.getName(), ((SliderSetting) setting).getCurrent());
                } else if (setting instanceof ListSetting) {
                    property = currentConfig.get(module.getName(), setting.getName(), ((ListSetting) setting).getCurrent());
                }
            }
        }
    }

    public void save(Configuration config) {
        config.save();
    }

    public void update() {
        for (Module module : ExampleClient.MODULE_MANAGER.getModules()) {
            Property property;
            for (Setting setting : module.getSettings()) {
                if (setting instanceof ToggleSetting) {
                    property = currentConfig.get(module.getName(), setting.getName(), ((ToggleSetting) setting).isEnabled());
                    property.set(((ToggleSetting) setting).isEnabled());
                } else if (setting instanceof SliderSetting) {
                    property = currentConfig.get(module.getName(), setting.getName(), ((SliderSetting) setting).getCurrent());
                    property.set(((SliderSetting) setting).getCurrent());
                } else if (setting instanceof ListSetting) {
                    property = currentConfig.get(module.getName(), setting.getName(), ((ListSetting) setting).getCurrent());
                    property.set(((ListSetting) setting).getCurrent());
                }
            }
        }
        save(currentConfig);
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(Module module, Setting setting) {
        String name = setting.getName();
        Property property;
        if (setting instanceof ToggleSetting) {
            property = currentConfig.get(module.getName(), setting.getName(), ((ToggleSetting) setting).isEnabled());
            return (T) Boolean.valueOf(property.getBoolean());
        } else if (setting instanceof SliderSetting) {
            property = currentConfig.get(module.getName(), setting.getName(), ((SliderSetting) setting).getCurrent());
            return (T) Double.valueOf(property.getDouble());
        } else if (setting instanceof ListSetting) {
            property = currentConfig.get(module.getName(), setting.getName(), ((ListSetting) setting).getCurrent());
            return (T) String.valueOf(property.getString());
        }
        return null;
    }

    public void setModuleEnabled(Module module, boolean enabled) {
        Property property = currentConfig.get(module.getName(), "enabled", false);
        property.set(enabled);
        save(currentConfig);
    }

    public boolean isEnabled(Module module) {
        Property property = currentConfig.get(module.getName(), "enabled", false);
        return property.getBoolean();
    }

    public ArrayList<String> getConfigNames() {
        File[] files = dir.listFiles();
        ArrayList<String> output = new ArrayList<String>();
        if (files == null) return output;
        for (File file : files) {
            if (file.getName().endsWith(".cfg")) output.add(file.getName());
        }
        return output;
    }

    public void loadDir(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void deleteConfig(String fileName) throws Exception {
        File configFile = new File(dir, fileName);
        System.out.println(dir.getPath() + "\\ configFile.getName()");

        if (configFile.exists()) {
            try {
                configFile.delete();
            } catch (SecurityException e) {
                System.out.println("Security Exception...");
            }
        } else {
            throw new Exception("File doesn't exist");
        }
    }

    public String getCurrentConfigName() {
        return currentConfigName;
    }

    public Configuration getCurrentConfig() {
        return currentConfig;
    }
}
