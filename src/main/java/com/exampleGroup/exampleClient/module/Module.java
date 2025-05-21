package com.exampleGroup.exampleClient.module;

import com.exampleGroup.exampleClient.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;

public abstract class Module {

    protected final static Minecraft mc = Minecraft.getMinecraft();

    ArrayList<Setting> settings = new ArrayList<Setting>();
    private final String name, commandName, description;
    private final Category category;
    private final boolean moveable;
    private int key;
    private boolean enabled = false;
    private int hudX, hudY;
    private long lastToggled;

    public Module() {
        ModuleInfo info = getClass().getAnnotation(ModuleInfo.class);
        Validate.notNull(info, "CONFUSED ANNOTATION EXCEPTION");
        this.name = info.name();
        this.commandName = info.commandName();
        this.description = info.description();
        this.category = info.category();
        this.moveable = info.moveable();
        lastToggled = 0;
    }

    public void onEnable() {
        if (mc.thePlayer == null || mc.theWorld == null) return;
        mc.thePlayer.addChatMessage(new ChatComponentText(name + " enabled!"));
    }

    public void onDisable() {
        if (mc.thePlayer == null || mc.theWorld == null) return;
        mc.thePlayer.addChatMessage(new ChatComponentText(name + " disabled!"));
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        if (lastToggled + 100 > System.currentTimeMillis()) return;
        this.enabled = enabled;
        lastToggled = System.currentTimeMillis();
        if (enabled) {
            MinecraftForge.EVENT_BUS.register(this);
            onEnable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            onDisable();
        }
    }

    public void registerSetting(Setting setting) {
        this.settings.add(setting);
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public String getCommandName() {
        return commandName;
    }

    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public void setHudPos(int hudX, int hudY) {
        this.hudX = hudX;
        this.hudY = hudY;
    }

    public int[] getHudPos() {
        return new int[]{hudX, hudY};
    }
}
