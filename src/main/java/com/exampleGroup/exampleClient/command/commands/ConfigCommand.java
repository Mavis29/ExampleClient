package com.exampleGroup.exampleClient.command.commands;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.command.ICommand;
import net.minecraft.client.entity.EntityPlayerSP;

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
        if (args.length < 3) {
            sendChatMessage(getCommandUsage(sender));
            return;
        }

        if (args[1].equals("create")) {
            createConfig(args[2]);
        } else if (args[1].equals("delete")) {
            deleteConfig(args[2]);
        } else if (args[1].equals("load")) {
            // TODO: create config loading
        } else if (args[1].equals("list")) {
            // TODO: create config list
        }
    }

    @Override
    public String[][] getTabCompletion(String input) {
        return ICommand.super.getTabCompletion(input);
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
}
