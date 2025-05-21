package com.exampleGroup.exampleClient.command.commands;

import com.exampleGroup.exampleClient.command.CommandManager;
import com.exampleGroup.exampleClient.command.ICommand;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class HeloCommand implements ICommand {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName() {
        return "helo";
    }

    @Override
    public String getCommandUsage(EntityPlayerSP sender) {
        return CommandManager.getPrefix() + "helo [value]";
    }

    @Override
    public void processCommand(EntityPlayerSP sender, String[] args) {
        if (args.length <= 1) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Helo!"));
            return;
        }
        try {
            int num = Integer.parseInt(args[1]);
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Helo " + num));
        } catch (NumberFormatException e) {
            mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + getCommandUsage(sender)));
        }
    }
}
