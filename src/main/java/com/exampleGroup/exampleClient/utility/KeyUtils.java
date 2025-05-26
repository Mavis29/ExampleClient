package com.exampleGroup.exampleClient.utility;

import java.util.HashMap;

import static org.lwjgl.input.Keyboard.*;

public class KeyUtils {

    private static final HashMap<String, Integer> customKeyMappings = new HashMap<String, Integer>();

    public KeyUtils() {
        registerKeys();
    }

    private static void registerKeys() {
        customKeyMappings.put("`", KEY_GRAVE);
        customKeyMappings.put("GRAVE", KEY_GRAVE);
        customKeyMappings.put("-", KEY_MINUS);
        customKeyMappings.put("MINUS", KEY_MINUS);
        customKeyMappings.put("+", KEY_ADD);
        customKeyMappings.put("PLUS", KEY_ADD);
        customKeyMappings.put("'", KEY_APOSTROPHE);
        customKeyMappings.put("APOSTROPHE", KEY_APOSTROPHE);
        customKeyMappings.put("BACK", KEY_BACK);
        customKeyMappings.put("\\", KEY_BACKSLASH);
        customKeyMappings.put("BACKSLASH", KEY_BACKSLASH);
        customKeyMappings.put("CAPITAL", KEY_CAPITAL);
        customKeyMappings.put(",", KEY_COMMA);
        customKeyMappings.put("COMMA", KEY_COMMA);
        customKeyMappings.put("DECIMAL", KEY_DECIMAL);
        customKeyMappings.put(".", KEY_PERIOD);
        customKeyMappings.put("DOT", KEY_PERIOD);
        customKeyMappings.put("PERIOD", KEY_PERIOD);
        customKeyMappings.put("DEL", KEY_DELETE);
        customKeyMappings.put("/", KEY_DIVIDE);
        customKeyMappings.put("SLASH", KEY_DIVIDE);
        customKeyMappings.put("DIVIDE", KEY_DIVIDE);
        customKeyMappings.put("DOWN", KEY_DOWN);
        customKeyMappings.put("UP", KEY_UP);
        customKeyMappings.put("LEFT", KEY_LEFT);
        customKeyMappings.put("RIGHT", KEY_RIGHT);
        customKeyMappings.put("END", KEY_END);
        customKeyMappings.put("=", KEY_EQUALS);
        customKeyMappings.put("HOME", KEY_HOME);
        customKeyMappings.put("INSERT", KEY_INSERT);
        customKeyMappings.put("INS", KEY_INSERT);
        customKeyMappings.put("NONE", KEY_NONE);
        customKeyMappings.put("UNBIND", KEY_NONE);
        customKeyMappings.put("[", KEY_LBRACKET);
        customKeyMappings.put("LBRACKET", KEY_LBRACKET);
        customKeyMappings.put("RBRACKET", KEY_RBRACKET);
        customKeyMappings.put("]", KEY_RBRACKET);
        customKeyMappings.put("LCTRL", KEY_LCONTROL);
        customKeyMappings.put("RCTRL", KEY_RCONTROL);
        customKeyMappings.put("LSHIFT", KEY_LSHIFT);
        customKeyMappings.put("RSHIFT", KEY_RSHIFT);
        customKeyMappings.put(";", KEY_SEMICOLON);
        customKeyMappings.put("SEMICOLON", KEY_SEMICOLON);
        customKeyMappings.put("SCROLLWHEEL", 2);
        customKeyMappings.put("MOUSE2", 2);
        customKeyMappings.put("MOUSE3", 3);
        customKeyMappings.put("MOUSE4", 4);
    }

    public static int getIndexFromKeyName(String keyName) {
        try {
            return customKeyMappings.get(keyName);
        } catch (NullPointerException e) {
            return 0;
        }
    }

}
