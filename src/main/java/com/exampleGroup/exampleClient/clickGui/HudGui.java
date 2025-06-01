package com.exampleGroup.exampleClient.clickGui;

import com.exampleGroup.exampleClient.ExampleClient;
import com.exampleGroup.exampleClient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HudGui extends GuiScreen {

    private final ArrayList<Module> moveableModules = new ArrayList<Module>();
    private final HashMap<Module, int[]> modulePositions = new HashMap<Module, int[]>();
    private final int width = 6, height = 6;

    private int moduleX, moduleY, offsetX, offsetY;
    private Module selectedModule;

    Minecraft mc = Minecraft.getMinecraft();
    FontRenderer fr;
    ScaledResolution sr = new ScaledResolution(mc);

    @Override
    public void initGui() {
        super.initGui();
        for (Module module : ExampleClient.MODULE_MANAGER.getModules()) {
            if (module.isMoveable()) {
                moveableModules.add(module);
                modulePositions.put(module, module.getHudPos());
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        fr = mc.fontRendererObj;

        if (selectedModule != null) {
            moduleX = mouseX - offsetX;
            moduleY = mouseY - offsetY;
            modulePositions.put(selectedModule, new int[]{moduleX, moduleY});
            selectedModule.setHudPos(modulePositions.get(selectedModule)[0], modulePositions.get(selectedModule)[1]);
        }

        for (Module module : moveableModules) {
            System.out.println(module.getName());
            int moduleX = modulePositions.get(module)[0];
            int moduleY = modulePositions.get(module)[1];
            drawRect(moduleX, moduleY, moduleX + width, moduleY + height, Color.BLACK.getRGB());
            drawRect(moduleX + 1, moduleY + 1, moduleX + width - 1, moduleY + height - 1, Color.GRAY.getRGB());
            fr.drawStringWithShadow(module.getName(), moduleX + 7, moduleY, -1);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (Module module : moveableModules) {
            int moduleX = modulePositions.get(module)[0];
            int moduleY = modulePositions.get(module)[1];
            if (!isHovered(mouseX, mouseY, moduleX, moduleY, moduleX + width, moduleY + height)) continue;
            selectedModule = module;
            offsetX = mouseX - moduleX;
            offsetY = mouseY - moduleY;
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        super.mouseReleased(mouseX, mouseY, mouseButton);
        if (selectedModule == null) return;
        selectedModule.setHudPos(modulePositions.get(selectedModule)[0], modulePositions.get(selectedModule)[1]);
        selectedModule = null;
    }

    private boolean isHovered(int mouseX, int mouseY, int x1, int y1, int x2, int y2) {
        return x1 <= mouseX && mouseX < x2 && y1 <= mouseY && mouseY < y2;
    }
}
