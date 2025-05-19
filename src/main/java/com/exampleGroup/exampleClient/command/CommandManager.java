package com.exampleGroup.exampleClient.command;

import com.exampleGroup.exampleClient.command.commands.HelloCommand;
import com.exampleGroup.exampleClient.command.commands.HeloCommand;
import com.exampleGroup.exampleClient.command.commands.TestCommand;
import com.exampleGroup.exampleClient.events.chat.ChatCloseEvent;
import com.exampleGroup.exampleClient.events.chat.ChatInitEvent;
import com.exampleGroup.exampleClient.events.chat.ChatInputEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager {

    private static String prefix = ".";
    private static boolean isCustomCommand;
    private static HashMap<String, ICommand> commands = new HashMap<>();
    private String lastPreTabInput;
    private int tabCounter;

    private static final Minecraft mc = Minecraft.getMinecraft();

    public CommandManager() {
        isCustomCommand = false;
        registerCommands();
    }

    public void registerCommands() {
        registerCommand(new TestCommand());
        registerCommand(new HelloCommand());
        registerCommand(new HeloCommand());
    }

    public void registerCommand(ICommand command) {
        commands.put(command.getCommandName(), command);
    }

    public static void handleCommand(EntityPlayerSP sender, String msg) {
        msg = msg.replaceFirst(prefix, "");
        String[] args = msg.split(" ");
        ICommand command = commands.get(args[0]);
        if (command == null) {
            mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + "Command wasn't found: " + args[0]));
            return;
        }
        command.processCommand(sender, args);
    }

    @SubscribeEvent
    public void onChatInput(ChatInputEvent event) {
        String input = event.getInput();
        isCustomCommand = input.startsWith(prefix);
        if (!isCustomCommand) {
            tabCounter = 0;
            return;
        }
        if (event.getKeyCode() == 15 && !input.contains(" ")) { // tab
            if (tabCounter == 0) lastPreTabInput = input;
            event.setInput(getTabCompletion(lastPreTabInput, tabCounter));
            tabCounter++;
        } else {
            tabCounter = 0;
        }
    }

    @SubscribeEvent
    public void onChatClose(ChatCloseEvent event) {
        isCustomCommand = false;
    }

    @SubscribeEvent
    public void onChatInit(ChatInitEvent event) {
        isCustomCommand = false;
    }

    public String getTabCompletion(String input, int option) {
        String cleanedInput = input.replaceFirst(prefix, ""); // turns .command into command
        ArrayList<String> commandNames = new ArrayList<>(commands.keySet()); // makes a list with all command names
        //System.out.println(Arrays.toString(commandNames.toArray()));
        List<String> possibleValues = commandNames.stream().filter(s -> s.startsWith(cleanedInput)).collect(Collectors.toList()); // filters it to only include ones that make sense
        //System.out.println(Arrays.toString(possibleValues.toArray()));
        if (possibleValues.isEmpty()) return input;
        return prefix + possibleValues.get(option % possibleValues.size());
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        CommandManager.prefix = prefix;
    }

    public static boolean isCustomCommand() {
        return isCustomCommand;
    }
}
