package com.exampleGroup.exampleClient.mixins.chat;

import com.exampleGroup.exampleClient.command.CommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.*;

@Mixin(GuiTextField.class)
public abstract class MixinGuiTextField extends Gui {

    @Shadow
    @Final
    private FontRenderer fontRendererInstance;

    @Shadow
    public int xPosition;

    @Shadow
    public int yPosition;

    @Shadow
    public int width;

    @Shadow
    public int height;

    @Shadow
    private boolean isEnabled = true;

    @Shadow
    private int enabledColor = 14737632;

    @Shadow
    private int disabledColor = 7368816;

    @Shadow
    private int lineScrollOffset;

    @Shadow
    private int cursorPosition;

    @Shadow
    private int selectionEnd;

    @Shadow
    private boolean isFocused;

    @Shadow
    private int cursorCounter;

    @Shadow
    private boolean enableBackgroundDrawing = true;

    @Shadow
    private String text;

    @Shadow
    public abstract boolean getVisible();

    @Shadow
    public abstract boolean getEnableBackgroundDrawing();

    @Shadow
    public abstract int getWidth();

    @Shadow
    public abstract int getMaxStringLength();

    @Shadow
    protected abstract void drawCursorVertical(int p_146188_1_, int p_146188_2_, int p_146188_3_, int p_146188_4_);


    /**
     * @author Example Author
     * @reason Creating visualisation for custom commands.
     */
    @Overwrite
    public void drawTextBox() {
        if (this.getVisible()) {
            if (this.getEnableBackgroundDrawing()) {
                drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
                drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
            }

            // Draws white box around the chat to visualise that the player will execute a custom command
            if (CommandManager.isCustomCommand() && Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
                exampleClient$drawChatOutline();
            }

            int i = this.isEnabled ? this.enabledColor : this.disabledColor;
            int j = this.cursorPosition - this.lineScrollOffset;
            int k = this.selectionEnd - this.lineScrollOffset;
            String s = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
            boolean flag = j >= 0 && j <= s.length();
            boolean flag1 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && flag;
            int l = this.enableBackgroundDrawing ? this.xPosition + 4 : this.xPosition;
            int i1 = this.enableBackgroundDrawing ? this.yPosition + (this.height - 8) / 2 : this.yPosition;
            int j1 = l;

            if (k > s.length()) {
                k = s.length();
            }

            if (s.length() > 0) {
                String s1 = flag ? s.substring(0, j) : s;
                j1 = this.fontRendererInstance.drawStringWithShadow(s1, (float) l, (float) i1, i);
            }

            boolean flag2 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
            int k1 = j1;

            if (!flag) {
                k1 = j > 0 ? l + this.width : l;
            } else if (flag2) {
                k1 = j1 - 1;
                --j1;
            }

            if (s.length() > 0 && flag && j < s.length()) {
                j1 = this.fontRendererInstance.drawStringWithShadow(s.substring(j), (float) j1, (float) i1, i);
            }

            if (flag1) {
                if (flag2) {
                    Gui.drawRect(k1, i1 - 1, k1 + 1, i1 + 1 + this.fontRendererInstance.FONT_HEIGHT, -3092272);
                } else {
                    this.fontRendererInstance.drawStringWithShadow("_", (float) k1, (float) i1, i);
                }
            }

            if (k != j) {
                int l1 = l + this.fontRendererInstance.getStringWidth(s.substring(0, k));
                this.drawCursorVertical(k1, i1 - 1, l1 - 1, i1 + 1 + this.fontRendererInstance.FONT_HEIGHT);
            }
        }
    }

    @Unique
    public void exampleClient$drawChatOutline() {
        drawRect(this.xPosition - 2, this.yPosition - 2, this.xPosition + this.width - 1, this.yPosition - 1, -1); // Top
        drawRect(this.xPosition - 2, this.yPosition - 2, this.xPosition - 1, this.yPosition + this.height - 2, -1); // Left
        drawRect(this.xPosition - 2, this.yPosition + this.height - 2, this.xPosition + this.width - 2, this.yPosition + this.height - 1, -1); // Bottom
        drawRect(this.xPosition + this.width - 2, this.yPosition - 2, this.xPosition + this.width - 1, this.yPosition + this.height - 1, -1); // Right
    }
}