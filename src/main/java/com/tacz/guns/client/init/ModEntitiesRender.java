package com.tacz.guns.client.init;

import com.tacz.guns.client.renderer.entity.EntityBulletRenderer;
import com.tacz.guns.client.renderer.entity.TargetMinecartRenderer;
import com.tacz.guns.entity.EntityKineticBullet;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ModEntitiesRender {
    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers evt) {
        EntityRenderers.register(EntityKineticBullet.TYPE, EntityBulletRenderer::new);
        EntityRenderers.register(TargetMinecart.TYPE, TargetMinecartRenderer::new);
    }
}
