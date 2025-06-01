package com.exampleGroup.exampleClient.logging;

import com.exampleGroup.exampleClient.ExampleClient;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Logger {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final String PREFiX = ExampleClient.PREFIX;

    public static void sendChatMessage(String message) {
        if (mc.thePlayer == null || mc.theWorld == null) return;
        mc.thePlayer.addChatMessage(new ChatComponentText(PREFiX + " " + message));
    }
}
