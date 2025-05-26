package com.exampleGroup.exampleClient.command.commands;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.command.ICommand;
import com.exampleGroup.exampleClient.utility.KeyUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import static com.exampleGroup.exampleClient.logging.Logger.sendChatMessage;


public class ClickGuiCommand implements ICommand {

    @Override
    public String getCommandName() {
        return "gui";
    }

    @Override
    public String getCommandUsage(EntityPlayerSP sender) {
        return "gui [bind]";
    }

    @Override
    public void processCommand(EntityPlayerSP sender, String[] args) {
        if (args.length < 3) {
            sendChatMessage(getCommandUsage(sender));
            return;
        }
        if (!args[1].equalsIgnoreCase("bind")) {
            sendChatMessage(getCommandUsage(sender));
            return;
        }

        bind(args[2]);
    }

    @Override
    public String[][] getTabCompletion(String input) {
        return new String[][]{{"bind"}};
    }

    private void bind(String key) {
        key = key.toUpperCase();
        int index = Keyboard.getKeyIndex(key);
        sendChatMessage("" + index);
        if (index == 0) {
            index = KeyUtils.getIndexFromKeyName(key);
            sendChatMessage(key + " : " + index);
        }
        sendChatMessage(EnumChatFormatting.GREEN + "Set bind for Click Gui to §o" + Keyboard.getKeyName(index) + "§r§a.");
        ExampleClient.CLICK_GUI.setKey(index);
    }
}
