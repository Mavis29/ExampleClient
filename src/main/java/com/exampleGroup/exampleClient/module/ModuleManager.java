package com.exampleGroup.exampleClient.module;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.command.ModuleCommand;
import com.exampleGroup.exampleClient.module.modules.render.TestModule;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.HashMap;

public class ModuleManager {

    HashMap<Module, Boolean> modules = new HashMap<Module, Boolean>();

    public ModuleManager() {
        registerModules();
    }

    public void registerModules() {
        register(new TestModule(), false);
    }

    public void register(Module module, boolean enabled) {
        modules.put(module, enabled);
        ExampleClient.COMMAND_MANAGER.registerCommand(new ModuleCommand(module));
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        //Logger.sendChatMessage("Keyboard event: " + key);
        for (Module module : modules.keySet()) {
            if (module.getKey() != key) return;
            module.toggle();
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        int button = Mouse.getEventButton();
        if (button == -1) return;
        //Logger.sendChatMessage("Mouse event: " + button);
        for (Module module : modules.keySet()) {
            if (module.getKey() != button) return;
            module.toggle();
        }
    }
}
