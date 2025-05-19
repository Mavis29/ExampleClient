package com.exampleGroup.exampleClient.mixins.entity;

import com.exampleGroup.exampleClient.events.PreUpdateEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onUpdatePre(CallbackInfo ci) {
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new PreUpdateEvent());
    }
}
