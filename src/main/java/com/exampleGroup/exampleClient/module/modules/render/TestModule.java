package com.exampleGroup.exampleClient.module.modules.render;

import com.exampleGroup.exampleClient.events.PreUpdateEvent;
import com.exampleGroup.exampleClient.module.Category;
import com.exampleGroup.exampleClient.module.Module;
import com.exampleGroup.exampleClient.module.ModuleInfo;
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

    public TestModule() {
        setKey(Keyboard.KEY_L);
        registerSetting(renderTest = new ToggleSetting("renderTest", false));
    }

    @SubscribeEvent
    public void onRenderGamerOverlay(RenderGameOverlayEvent.Text event) {
        FontRenderer fr = mc.fontRendererObj;
        fr.drawStringWithShadow("Example Client", 2, 2, -1);
        if (!renderTest.isEnabled()) return;
        fr.drawStringWithShadow("Render Test", 2, 10, -1);
    }

    @SubscribeEvent
    public void onPreUpdate(PreUpdateEvent event) {

    }

}
