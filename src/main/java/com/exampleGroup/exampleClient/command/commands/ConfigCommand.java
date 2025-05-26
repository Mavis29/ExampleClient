package com.exampleGroup.exampleClient.command.commands;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.command.ICommand;
import com.exampleGroup.exampleClient.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import static com.exampleGroup.exampleClient.logging.Logger.sendChatMessage;

public class ConfigCommand implements ICommand {

    @Override
    public String getCommandName() {
        return "config";
    }

    @Override
    public String getCommandUsage(EntityPlayerSP sender) {
        return "§cconfig <create|delete|load> <configName>\n" +
                "§cconfig <list>";
    }

    @Override
    public void processCommand(EntityPlayerSP sender, String[] args) {

        if (args.length == 1) {
            sendChatMessage("§aCurrent config: " + ExampleClient.CONFIG_MANAGER.getCurrentConfigName());
            return;
        }

        if (args.length == 2) {
            if (args[1].equals("list")) {
                listConfigs();
                return;
            }
        }

        if (args.length < 3) {
            sendChatMessage(getCommandUsage(sender));
            return;
        }

        if (args[1].equals("create")) {
            createConfig(args[2]);
        } else if (args[1].equals("delete")) {
            deleteConfig(args[2]);
        } else if (args[1].equals("load")) {
            loadConfig(args[2]);
        } else {
            sendChatMessage(getCommandUsage(sender));
        }
    }

    @Override
    public String[][] getTabCompletion(String input) {
        String[] args1 = new String[]{"create", "delete", "load", "list"};
        return new String[][]{args1};
    }

    private void createConfig(String name) {
        if (!name.endsWith(".cfg")) name += ".cfg";
        try {
            ExampleClient.CONFIG_MANAGER.createConfig(name);
        } catch (Exception e) {
            sendChatMessage("§cThe config most likely already exists.");
            return;
        }
        sendChatMessage("§aCreated config §o" + name + "§r§a.");
    }

    private void deleteConfig(String name) {
        if (!name.endsWith(".cfg")) name += ".cfg";
        try {
            ExampleClient.CONFIG_MANAGER.deleteConfig(name);
        } catch (Exception e) {
            sendChatMessage("§cThe config doesn't most likely doesn't exist.");
            return;
        }

        sendChatMessage("§aDeleted config §o" + name + "§r§a.");
    }

    private void loadConfig(String name) {
        if (!name.endsWith(".cfg")) name += ".cfg";
        ExampleClient.CONFIG_MANAGER.loadConfig(name);
        ExampleClient.MODULE_MANAGER.onConfigUpdate();
        for (Module module : ExampleClient.MODULE_MANAGER.getModules()) {
            module.onConfigUpdate();
        }
    }

    private void listConfigs() {
        ArrayList<String> configs = ExampleClient.CONFIG_MANAGER.getConfigNames();
        String output = "§a§lCONFIGS:\n";

        for (String cfg : configs) {
            output += " §8- §a" + cfg + "\n";
        }
        output = StringUtils.chop(output);
        sendChatMessage(output);
    }
}
