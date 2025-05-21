package com.exampleGroup.exampleClient.relations;

import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

import static com.exampleGroup.exampleClient.logging.Logger.sendChatMessage;

public class RelationManager {

    private ArrayList<String> friendsList, targetList;

    public RelationManager() {
        friendsList = new ArrayList<String>();
        targetList = new ArrayList<String>();
    }

    public void addEntry(String ign, Relation relation) {
        if (relation == Relation.FRIEND) {
            if (friendsList.contains(ign))
                sendChatMessage(EnumChatFormatting.RED + "This player is already in the friends list.");
            else {
                friendsList.add(ign);
                sendChatMessage("§aAdded §n" + ign + "§r§a to the Friends list§r§a.");
            }
        } else if (relation == Relation.TARGET) {
            if (targetList.contains(ign))
                sendChatMessage(EnumChatFormatting.RED + "This player is already in the target list.");
            else {
                targetList.add(ign);
                sendChatMessage("§aAdded §n" + ign + "§r§a to the §cTarget list§r§a.");
            }
        }
    }

    public void removeEntry(String ign, Relation relation) {
        if (relation == Relation.FRIEND) {
            if (!friendsList.contains(ign))
                sendChatMessage(EnumChatFormatting.RED + "This player is not in the target list.");
            else {
                friendsList.remove(ign);
                sendChatMessage("§aRemoved §n" + ign + "§r§a from the Friends list§r§a.");
            }
        } else if (relation == Relation.TARGET) {
            if (!targetList.contains(ign))
                sendChatMessage(EnumChatFormatting.RED + "This player is not in the target list.");
            else {
                targetList.remove(ign);
                sendChatMessage("§aRemoved §n" + ign + "§r§a from the §cTarget list§r§a.");
            }
        }
    }

    public void clearList(Relation relation) {
        if (relation == Relation.FRIEND) {
            friendsList.clear();
            sendChatMessage("§aCleared the Friends list§r§a.");
        } else if (relation == Relation.TARGET) {
            targetList.clear();
            sendChatMessage("§aCleared the §cTarget list§r§a.");
        }
    }

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public ArrayList<String> getTargetList() {
        return targetList;
    }

    public enum Relation {
        FRIEND,
        TARGET,
    }
}
