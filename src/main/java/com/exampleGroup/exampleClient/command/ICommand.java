package com.exampleGroup.exampleClient.command;

import net.minecraft.client.entity.EntityPlayerSP;

public interface ICommand {

    /**
     * Gets the name of the command
     *
     * @return command name
     */
    String getCommandName();

    /**
     * Gets the correct usage of the command
     *
     * @param sender the player that executed the command
     * @return correct command usage
     */
    String getCommandUsage(EntityPlayerSP sender);

    /**
     * What happens on execution
     *
     * @param sender the player that executed the command
     * @param args   the subcommands and values of the command
     */
    void processCommand(EntityPlayerSP sender, String[] args);

    /**
     * Get the best value for tab completion
     *
     * @param input the current input in the text field
     * @return get the most likely chat completion
     */
    default String[][] getTabCompletion(String input) {
        return new String[][]{{input}};
    }
}
