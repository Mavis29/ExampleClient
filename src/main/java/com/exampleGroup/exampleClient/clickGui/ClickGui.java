package com.exampleGroup.exampleClient.clickGui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class ClickGui extends GuiScreen {

    private int gui_key = Keyboard.KEY_RSHIFT;

    public ClickGui() {

    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public int getKey() {
        return gui_key;
    }

    public void setKey(int gui_key) {
        this.gui_key = gui_key;
    }

}
