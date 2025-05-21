package com.exampleGroup.exampleClient.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.ArrayList;

public class ConfigManager {

    private File dir;

    private ArrayList<String> configs = new ArrayList<>();

    public ConfigManager() {
        dir = new File(Loader.instance().getConfigDir(), "ExampleClient");
        loadDir(dir);
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

        //reload();
        //save();
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
}
