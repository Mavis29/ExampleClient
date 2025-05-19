package com.exampleGroup.exampleClient.command.commands;

import com.exampleGroup.exampleClient.command.CommandManager;
import com.exampleGroup.exampleClient.command.ICommand;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class HelloCommand implements ICommand {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName() {
        return "hello";
    }

    @Override
    public String getCommandUsage(EntityPlayerSP sender) {
        return CommandManager.getPrefix() + "hello [value]";
    }

    @Override
    public void processCommand(EntityPlayerSP sender, String[] args) {
        if (args.length <= 1) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Hello!"));
            return;
        }
        try {
            int num = Integer.parseInt(args[1]);
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Hello " + num));
        } catch (NumberFormatException e) {
            mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + getCommandUsage(sender)));
        }
    }

    @Override
    public String getTabCompletion(String input) {
        return ICommand.super.getTabCompletion(input);
    }
}
