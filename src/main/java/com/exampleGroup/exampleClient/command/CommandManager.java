package com.exampleGroup.exampleClient.command;

import com.exampleGroup.exampleClient.command.commands.*;
import com.exampleGroup.exampleClient.events.chat.ChatCloseEvent;
import com.exampleGroup.exampleClient.events.chat.ChatInitEvent;
import com.exampleGroup.exampleClient.events.chat.ChatInputEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;
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
        // Testing
        registerCommand(new HelloCommand());
        registerCommand(new HeloCommand());

        // Relations
        registerCommand(new FriendCommand());
        registerCommand(new TargetCommand());

        // Config
        registerCommand(new ConfigCommand());

        // Click gui
        registerCommand(new ClickGuiCommand());
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
        if (event.getKeyCode() == 15) { // tab
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
        String cleanedInput = input.replaceFirst(prefix, ""); // turns .test into test
        if (!cleanedInput.contains(" ")) {
            ArrayList<String> commandNames = new ArrayList<>(commands.keySet()); // makes a list with all command names
            List<String> possibleValues = commandNames.stream().filter(s -> s.toLowerCase().startsWith(cleanedInput.toLowerCase())).collect(Collectors.toList()); // filters it to only include ones that make sense
            if (possibleValues.isEmpty()) return input;
            return prefix + possibleValues.get(option % possibleValues.size());
        }

        String[] split = cleanedInput.split(" ");
        String[] args;
        if (cleanedInput.charAt(cleanedInput.length() - 1) == ' ') {
            args = new String[split.length + 1];
            System.arraycopy(split, 0, args, 0, split.length);
            args[args.length - 1] = "";
        } else {
            args = split;
        }

        ICommand command = commands.get(args[0]);
        if (command == null) return input;

        String[][] chatCompletion = command.getTabCompletion(input);

        //String possibleValuesStr = "{";
        if (args.length > chatCompletion.length + 1) return input;
        /*for (int i = 0; i < chatCompletion.length; i++) {
            possibleValuesStr += "{";
            for (int j = 0; j < chatCompletion[i].length; j++) {
                if (j != 0) possibleValuesStr += ", ";
                possibleValuesStr += chatCompletion[i][j];
            }
            possibleValuesStr += "}";
        }
        possibleValuesStr += "}";*/
        //System.out.println(possibleValuesStr);
        // TODO: remove comments
        //System.out.println("Starts with: " + args[args.length - 1]);
        //System.out.println("chat comp:" + Arrays.toString(chatCompletion[args.length - 2]));
        List<String> possibleValues = Arrays.stream(chatCompletion[args.length - 2]).filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
        //System.out.println(Arrays.toString(possibleValues.toArray()));

        if (possibleValues.isEmpty()) return input;

        String output = prefix;
        for (int i = 0; i < args.length - 1; i++) {
            output += args[i] + " ";
        }
        output += possibleValues.get(option % possibleValues.size());
        return output;
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
