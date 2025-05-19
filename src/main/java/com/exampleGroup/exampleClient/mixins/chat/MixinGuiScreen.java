package com.exampleGroup.exampleClient.mixins.chat;

import com.exampleGroup.exampleClient.command.CommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GuiScreen.class)
public abstract class MixinGuiScreen extends Gui implements GuiYesNoCallback {

    @Shadow
    public Minecraft mc;

    /**
     * @author Example Author
     * @reason Custom cancelling of chat message sending
     */
    @Overwrite
    public void sendChatMessage(String msg, boolean addToChat) {

        if (msg.startsWith(CommandManager.getPrefix())) {
            if (addToChat) this.mc.ingameGUI.getChatGUI().addToSentMessages(msg);
            exampleClient$handleCustomCommand(msg);
            return;
        }

        if (addToChat) {
            this.mc.ingameGUI.getChatGUI().addToSentMessages(msg);
        }

        if (net.minecraftforge.client.ClientCommandHandler.instance.executeCommand(mc.thePlayer, msg) != 0) return;

        this.mc.thePlayer.sendChatMessage(msg);
    }

    @Unique
    private void exampleClient$handleCustomCommand(String msg) {
        CommandManager.handleCommand(mc.thePlayer, msg);
    }
}