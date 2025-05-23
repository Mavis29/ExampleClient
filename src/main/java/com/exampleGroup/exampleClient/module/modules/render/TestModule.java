package com.exampleGroup.exampleClient.module.modules.render;

import com.exampleGroup.exampleClient.events.PreUpdateEvent;
import com.exampleGroup.exampleClient.module.Category;
import com.exampleGroup.exampleClient.module.Module;
import com.exampleGroup.exampleClient.module.ModuleInfo;
import com.exampleGroup.exampleClient.setting.settings.ListSetting;
import com.exampleGroup.exampleClient.setting.settings.SliderSetting;
import com.exampleGroup.exampleClient.setting.settings.ToggleSetting;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

@ModuleInfo(
        name = "Test Module",
        commandName = "testmodule",
        description = "Just a module for testing",
        moveable = false,
        category = Category.RENDER
)
public class TestModule extends Module {

    private ToggleSetting renderTest;
    private SliderSetting sliderSetting;
    private ListSetting listSetting;

    private double x;

    public TestModule() {
        setKey(Keyboard.KEY_L);
        String[] listSettings = new String[]{"Text1", "Text2", "Text3", "Text4"};
        registerSetting(renderTest = new ToggleSetting("renderTest", false));
        registerSetting(sliderSetting = new SliderSetting("sliderSetting", 0, 100, 2));
        registerSetting(listSetting = new ListSetting("listSetting", listSettings, listSettings[0]));
    }

    @SubscribeEvent
    public void onRenderGamerOverlay(RenderGameOverlayEvent.Text event) {
        FontRenderer fr = mc.fontRendererObj;
        x = sliderSetting.getCurrent();


        fr.drawStringWithShadow("Example Client", 2, 2, -1);
        if (!renderTest.isEnabled()) return;
        fr.drawStringWithShadow(listSetting.getCurrent(), (float) x, 10, -1);
    }

    @SubscribeEvent
    public void onPreUpdate(PreUpdateEvent event) {

    }

}
