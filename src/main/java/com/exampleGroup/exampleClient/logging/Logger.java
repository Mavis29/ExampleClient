package com.exampleGroup.exampleClient.logging;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Logger {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void sendChatMessage(String message) {
        if (mc.thePlayer == null || mc.theWorld == null) return;
        mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }

}
