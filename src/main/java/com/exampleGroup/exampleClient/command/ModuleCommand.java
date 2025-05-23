package com.exampleGroup.exampleClient.command;

import com.exampleGroup.exampleClient.module.Module;
import com.exampleGroup.exampleClient.setting.Setting;
import com.exampleGroup.exampleClient.setting.settings.ListSetting;
import com.exampleGroup.exampleClient.setting.settings.SliderSetting;
import com.exampleGroup.exampleClient.setting.settings.ToggleSetting;
import com.exampleGroup.exampleClient.utility.KeyUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

import static com.exampleGroup.exampleClient.logging.Logger.sendChatMessage;

public class ModuleCommand implements ICommand {

    Module module;

    public ModuleCommand(Module module) {
        this.module = module;
    }

    @Override
    public String getCommandName() {
        return module.getCommandName();
    }

    @Override
    public String getCommandUsage(EntityPlayerSP sender) {
        return "§cInvalid Usage.";
    }

    @Override
    public void processCommand(EntityPlayerSP sender, String[] args) {
        if (args.length < 2) {
            sendChatMessage(getCommandUsage(sender));
            return;
        }

        if (args[1].equals("t")) {
            module.toggle();
            return;
        }

        for (Setting setting : module.getSettings()) {
            if (!setting.getName().equals(args[1])) continue;
            if (setting instanceof ToggleSetting) {
                handleToggle((ToggleSetting) setting, args.length < 3 ? "" : args[2]);
                return;
            } else if (setting instanceof SliderSetting && args.length > 2) {
                handleSlider((SliderSetting) setting, args[2]);
                return;
            } else if (setting instanceof ListSetting && args.length > 2) {
                handleList((ListSetting) setting, args[2]);
                return;
            } else {
                sendChatMessage(getCommandUsage(sender));
                return;
            }
        }

        if (args.length < 3) {
            sendChatMessage(getCommandUsage(sender));
            return;
        }

        if (args[1].equals("bind")) {
            bind(args[2]);
            return;
        }
    }

    @Override
    public String[][] getTabCompletion(String input) {

        ArrayList<String> settingNames = new ArrayList<String>();
        for (Setting setting : module.getSettings()) {
            settingNames.add(setting.getName());
        }

        settingNames.add("bind");
        settingNames.add("t");

        String[] args1 = settingNames.toArray(new String[0]);

        return new String[][]{args1};
    }

    private void bind(String key) {
        int index = Keyboard.getKeyIndex(key);
        sendChatMessage("" + index);
        if (index == 0) {
            index = KeyUtils.getIndexFromKeyName(key);
            sendChatMessage(key + " : " + index);
        }
        sendChatMessage(EnumChatFormatting.GREEN + "Set bind for " + module.getName() + " to §o" + Keyboard.getKeyName(index) + "§r§a.");
        module.setKey(index);
    }

    private void handleToggle(ToggleSetting setting, String input) {
        if (input.equalsIgnoreCase("true")) {
            setting.setEnabled(true);
            sendChatMessage(EnumChatFormatting.GREEN + "Set " + setting.getName() + " to §o" + setting.isEnabled() + "§r§a.");
        } else if (input.equalsIgnoreCase("false")) {
            setting.setEnabled(false);
            sendChatMessage(EnumChatFormatting.GREEN + "Set " + setting.getName() + " to §o" + setting.isEnabled() + "§r§a.");
        } else {
            setting.setEnabled(!setting.isEnabled());
            sendChatMessage("Toggled §o" + setting.getName() + "§r.");
        }
    }

    private void handleSlider(SliderSetting setting, String input) {
        double output;
        try {
            output = Double.parseDouble(input);
            setting.setCurrent(output);
            sendChatMessage(EnumChatFormatting.GREEN + "Set value for " + setting.getName() + " to §o" + setting.getCurrent() + "§r§a.");
        } catch (NumberFormatException e) {
            sendChatMessage(EnumChatFormatting.RED + "Couldn't read number.");
        }
    }

    private void handleList(ListSetting setting, String input) {
        for (int i = 0; i < setting.getSettings().length; i++) {
            if (!setting.getSettings()[i].equalsIgnoreCase(input)) continue;
            setting.setCurrent(setting.getSettings()[i]);
            sendChatMessage(EnumChatFormatting.GREEN + "Set argument for " + setting.getName() + " to §o" + setting.getCurrent() + "§r§a.");
            return;
        }
        sendChatMessage(EnumChatFormatting.RED + "The argument passed wasn't valid.");
    }
}
