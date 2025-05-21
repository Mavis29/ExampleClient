package com.exampleGroup.exampleClient.command;

import com.exampleGroup.exampleClient.module.Module;
import com.exampleGroup.exampleClient.setting.Setting;
import com.exampleGroup.exampleClient.setting.settings.ToggleSetting;
import net.minecraft.client.entity.EntityPlayerSP;
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

    /*
    TODO: this...
    Ideas:
    .module bind KEY - binds module to a key
    .module [setting] [value] sets setting to value
     */
    @Override
    public String getCommandUsage(EntityPlayerSP sender) {
        return "";
    }

    @Override
    public void processCommand(EntityPlayerSP sender, String[] args) {
        if (args.length < 2) {
            // TODO: send basic usage and stuff
            return;
        }

        if (args[1].equals("t")) {
            module.toggle();
            return;
        }

        for (Setting setting : module.getSettings()) {
            if (!setting.getName().equals(args[1])) continue;
            if (!(setting instanceof ToggleSetting)) continue;
            ((ToggleSetting) setting).setEnabled(!((ToggleSetting) setting).isEnabled());
            sendChatMessage("Toggled §o" + setting.getName() + "§r.");
        }

        if (args.length < 3) {
            // TODO: send basic usage and stuff / error
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

        String[] args1 = settingNames.toArray(new String[0]);

        return new String[][]{args1};
    }

    private void bind(String key) {
        int index = Keyboard.getKeyIndex(key);
        sendChatMessage("" + index);

        
    }
}
