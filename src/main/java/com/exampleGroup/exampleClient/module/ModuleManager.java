package com.exampleGroup.exampleClient.module;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.command.commands.ModuleCommand;
import com.exampleGroup.exampleClient.module.modules.render.TestModule;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {

    HashMap<Module, Boolean> modules = new HashMap<Module, Boolean>();
    private final Minecraft mc = Minecraft.getMinecraft();

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

    public ArrayList<Module> getModules() {
        return new ArrayList<Module>(modules.keySet());
    }

    public boolean isModuleEnabled(Module module) {
        return modules.get(module);
    }

    public void setModuleEnabled(Module module, boolean enabled) {
        modules.put(module, enabled);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        if (key == ExampleClient.CLICK_GUI.getKey()) {
            mc.displayGuiScreen(ExampleClient.CLICK_GUI);
            return;
        }
        for (Module module : modules.keySet()) {
            if (module.getKey() != key) return;
            module.toggle();
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        int button = Mouse.getEventButton();
        if (button == -1) return;
        if (button == ExampleClient.CLICK_GUI.getKey()) {
            mc.displayGuiScreen(ExampleClient.CLICK_GUI);
            return;
        }
        for (Module module : modules.keySet()) {
            if (module.getKey() != button) return;
            module.toggle();
        }
    }

    public void onConfigUpdate() {
        for (Module module : modules.keySet()) {
            boolean enabled = ExampleClient.CONFIG_MANAGER.isEnabled(module);
            module.setEnabled(enabled);
        }
    }
}
