package com.exampleGroup.exampleClient.mixins.chat;

import com.exampleGroup.exampleClient.command.CommandManager;
import com.exampleGroup.exampleClient.events.chat.ChatCloseEvent;
import com.exampleGroup.exampleClient.events.chat.ChatInitEvent;
import com.exampleGroup.exampleClient.events.chat.ChatInputEvent;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiChat.class)
public class MixinGuiChat extends GuiScreen {

    @Shadow
    protected GuiTextField inputField;

    @Inject(method = "keyTyped", at = @At("RETURN"))
    private void keyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        ChatInputEvent event = new ChatInputEvent(keyCode, this.inputField.getText());
        MinecraftForge.EVENT_BUS.post(event);
        this.inputField.setText(event.getInput());
    }

    @Inject(method = "autocompletePlayerNames", at = @At("HEAD"), cancellable = true)
    public void autocompletePlayerNames(CallbackInfo ci) {
        if (CommandManager.isCustomCommand()) ci.cancel();
    }

    @Inject(method = "onGuiClosed", at = @At("RETURN"))
    public void onGuiClosed(CallbackInfo ci) {
        ChatCloseEvent event = new ChatCloseEvent();
        MinecraftForge.EVENT_BUS.post(event);
    }

    @Inject(method = "initGui", at = @At("RETURN"))
    public void initGui(CallbackInfo ci) {
        ChatInitEvent event = new ChatInitEvent();
        MinecraftForge.EVENT_BUS.post(event);
    }
}