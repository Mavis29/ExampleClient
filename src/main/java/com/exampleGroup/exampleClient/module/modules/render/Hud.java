package com.exampleGroup.exampleClient.module.modules.render;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.module.Category;
import com.exampleGroup.exampleClient.module.Module;
import com.exampleGroup.exampleClient.module.ModuleInfo;
import com.exampleGroup.exampleClient.setting.settings.ListSetting;
import com.exampleGroup.exampleClient.setting.settings.SliderSetting;
import com.exampleGroup.exampleClient.setting.settings.ToggleSetting;
import com.exampleGroup.exampleClient.utility.Render2DUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Comparator;

@ModuleInfo(
        name = "Hud",
        commandName = "hud",
        description = "Draws info about active modules on the HUD",
        moveable = true,
        category = Category.RENDER
)
public class Hud extends Module {

    private final ListSetting orientation;
    private final ToggleSetting renderBackground;
    private final SliderSetting posX, posY, backgroundOpacity;

    public Hud() {
        setKey(Keyboard.KEY_NONE);
        registerSetting(orientation = new ListSetting("orientation", new String[]{"Left", "Right"}, "Left"));
        registerSetting(renderBackground = new ToggleSetting("background", false));
        registerSetting(backgroundOpacity = new SliderSetting("opacity", 0, 1, 0.4));
        registerSetting(posX = new SliderSetting("posX", 0, 4096, 2));
        registerSetting(posY = new SliderSetting("posY", 0, 4096, 2));
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        if (orientation.getCurrent().equals("Left")) renderLeft(fr);
        else if (orientation.getCurrent().equals("Right")) renderRight(fr);
    }

    @Override
    public void setHudPos(int hudX, int hudY) {
        posX.setCurrent(hudX);
        posY.setCurrent(hudY);
    }

    @Override
    public int[] getHudPos() {
        return new int[]{(int) posX.getCurrent(), (int) posY.getCurrent()};
    }

    private void renderLeft(FontRenderer fr) {
        int currentY = (int) posY.getCurrent() + 1;
        ArrayList<String> modules = getModulesSorted();
        if (renderBackground.isEnabled())
            Render2DUtils.drawRectangle((int) posX.getCurrent() + 2, (int) posY.getCurrent(), fr.getStringWidth(modules.get(0)), 1, 0, 0, 0, (float) backgroundOpacity.getCurrent());
        for (String name : modules) {
            int width = fr.getStringWidth(name);
            if (renderBackground.isEnabled())
                Render2DUtils.drawRectangle((int) posX.getCurrent() + 2, currentY, width, 9, 0, 0, 0, (float) backgroundOpacity.getCurrent());
            fr.drawStringWithShadow(name, (int) (posX.getCurrent() + 2), currentY, -1);
            currentY += 9;
        }

        Render2DUtils.drawRectangle((float) posX.getCurrent(), (float) posY.getCurrent(), 1F, (float) (currentY - posY.getCurrent()), 255, 255, 255, 1.0f);
    }

    private void renderRight(FontRenderer fr) {
        int currentY = (int) posY.getCurrent() + 1;
        ArrayList<String> modules = getModulesSorted();
        if (renderBackground.isEnabled())
            Render2DUtils.drawRectangle((int) posX.getCurrent() - 2 - fr.getStringWidth(modules.get(0)), (int) posY.getCurrent(), fr.getStringWidth(modules.get(0)), 1, 0, 0, 0, (float) backgroundOpacity.getCurrent());
        for (String name : modules) {
            int width = fr.getStringWidth(name);
            if (renderBackground.isEnabled())
                Render2DUtils.drawRectangle((int) posX.getCurrent() - 2 - width, currentY, width, 9, 0, 0, 0, (float) backgroundOpacity.getCurrent());
            fr.drawStringWithShadow(name, (int) (posX.getCurrent() - 2 - width), currentY, -1);
            currentY += 9;
        }

        Render2DUtils.drawRectangle((float) posX.getCurrent(), (float) posY.getCurrent(), 1F, (float) (currentY - posY.getCurrent()), 255, 255, 255, 1.0f);
    }

    private ArrayList<String> getModulesSorted() {

        FontRenderer fr = mc.fontRendererObj;

        ArrayList<String> output = new ArrayList<>();
        for (Module module : ExampleClient.MODULE_MANAGER.getModules()) {
            if (module.isEnabled()) output.add(module.getName());
        }

        output.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(fr.getStringWidth(s2), fr.getStringWidth(s1));
            }
        });

        return output;
    }
}
