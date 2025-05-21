package com.exampleGroup.exampleClient.command.commands;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.command.ICommand;
import com.exampleGroup.exampleClient.relations.RelationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ChatComponentText;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class FriendCommand implements ICommand {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName() {
        return "f";
    }

    @Override
    public String getCommandUsage(EntityPlayerSP sender) {
        return "§cf <add|remove> <player>\n" +
                "§cf <list|clear>";
    }

    @Override
    public void processCommand(EntityPlayerSP sender, String[] args) {
        if (args.length < 2) {
            mc.thePlayer.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            return;
        }

        if (args.length < 3) {
            if (args[1].equals("list")) {
                listPlayers();
            } else if (args[1].equals("clear")) {
                clearList();
            } else {
                mc.thePlayer.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
            return;
        }

        if (args.length == 3) {
            if (args[1].equals("add"))
                addPlayer(args[2]);
            else if (args[1].equals("remove"))
                removePlayer(args[2]);
            else {
                mc.thePlayer.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
        } else {
            mc.thePlayer.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
        }
    }

    @Override
    public String[][] getTabCompletion(String input) {

        String[] args1 = new String[]{"add", "remove", "list"};

        Collection<NetworkPlayerInfo> playerInfoList = mc.getNetHandler().getPlayerInfoMap();

        String[] args2 = new String[playerInfoList.size()];
        int counter = 0;

        for (NetworkPlayerInfo info : playerInfoList) {
            args2[counter] = info.getGameProfile().getName();
            counter++;
        }

        return new String[][]{args1, args2};
    }

    private void addPlayer(String ign) {
        ExampleClient.RELATION_MANAGER.addEntry(ign, RelationManager.Relation.FRIEND);
    }

    private void removePlayer(String ign) {
        ExampleClient.RELATION_MANAGER.removeEntry(ign, RelationManager.Relation.FRIEND);
    }

    private void listPlayers() {
        String output = "§a§lFRIENDS:\n";
        for (String target : ExampleClient.RELATION_MANAGER.getFriendsList()) {
            output += " §8- §a" + target + "\n";
        }
        output = StringUtils.chop(output);
        mc.thePlayer.addChatMessage(new ChatComponentText(output));
    }

    private void clearList() {
        ExampleClient.RELATION_MANAGER.clearList(RelationManager.Relation.FRIEND);
    }
}
