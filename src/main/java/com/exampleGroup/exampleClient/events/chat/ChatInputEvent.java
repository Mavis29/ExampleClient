package com.exampleGroup.exampleClient.events.chat;

import net.minecraftforge.fml.common.eventhandler.Event;

public class ChatInputEvent extends Event {

    private int keyCode;
    private String input;

    public ChatInputEvent(int keyCode, String input) {
        this.keyCode = keyCode;
        this.input = input;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

}
