package com.exampleGroup.exampleClient.utility;

import java.util.HashMap;

import static org.lwjgl.input.Keyboard.*;

public class KeyUtils {

    HashMap<String, Integer> customKeyMappings = new HashMap<String, Integer>();

    public KeyUtils() {
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
        customKeyMappings.put(".", KEY_DECIMAL);
        customKeyMappings.put("DECIMAL", KEY_DECIMAL);
        customKeyMappings.put("DOT", KEY_DECIMAL);
        customKeyMappings.put("PERIOD", KEY_DECIMAL);
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
        // TODO: FINISH THIS!!
    }

    public static int getIndexFromKeyName(String keyName) {
        return 0;
    }

}
