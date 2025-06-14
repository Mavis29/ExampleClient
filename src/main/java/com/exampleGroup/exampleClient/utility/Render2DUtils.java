package com.exampleGroup.exampleClient.utility;


import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import static org.lwjgl.opengl.GL11.*;

public class Render2DUtils {

    public static void drawRectangle(float x, float y, float width, float height, int red, int green, int blue, float alpha) {

        float redPercentage = (float) red / 255;
        float greenPercentage = (float) green / 255;
        float bluePercentage = (float) blue / 255;

        glPushAttrib(GL_ALL_ATTRIB_BITS); // Save current GL state

        glEnable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(redPercentage, greenPercentage, bluePercentage, alpha);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(x, y + height, 0).endVertex();
        worldrenderer.pos(x + width, y + height, 0).endVertex();
        worldrenderer.pos(x + width, y, 0).endVertex();
        worldrenderer.pos(x, y, 0).endVertex();
        tessellator.draw();

        glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);

        glPopAttrib(); // Restore GL state
    }
}
