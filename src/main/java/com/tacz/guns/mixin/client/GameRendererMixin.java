package com.tacz.guns.mixin.client;

import com.tacz.guns.api.client.event.RenderLevelBobEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * TODO: 26.2 - GameRenderer rendering methods are now private.
 * BobHurt/BobView events need to be integrated differently.
 */
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow @Final
    private Minecraft minecraft;

    @Inject(method = "renderLevel", at = @At("HEAD"))
    public void onRenderLevel(net.minecraft.client.DeltaTracker deltaTracker, CallbackInfo ci) {
        NeoForge.EVENT_BUS.post(new RenderLevelBobEvent.BobHurt());
        NeoForge.EVENT_BUS.post(new RenderLevelBobEvent.BobView());
    }
}
