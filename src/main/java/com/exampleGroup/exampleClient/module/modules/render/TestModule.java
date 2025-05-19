package com.exampleGroup.exampleClient.module.modules.render;

import com.exampleGroup.exampleClient.events.PreUpdateEvent;
import com.exampleGroup.exampleClient.module.Module;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TestModule extends Module {

    @SubscribeEvent
    public void onRenderGamerOverlay(RenderGameOverlayEvent.Text event) {
        FontRenderer fr = mc.fontRendererObj;
        fr.drawStringWithShadow("Example Client", 2, 2, -1);
    }

    @SubscribeEvent
    public void onPreUpdate(PreUpdateEvent event) {

    }

}
