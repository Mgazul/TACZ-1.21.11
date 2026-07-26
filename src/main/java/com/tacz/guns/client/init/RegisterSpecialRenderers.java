package com.tacz.guns.client.init;

import com.tacz.guns.GunMod;
import com.tacz.guns.client.renderer.item.AmmoSpecialRenderer;
import com.tacz.guns.client.renderer.item.AttachmentSpecialRenderer;
import com.tacz.guns.client.renderer.item.GunSpecialRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

@EventBusSubscriber(modid = GunMod.MOD_ID, value = Dist.CLIENT)
public class RegisterSpecialRenderers {

    @SubscribeEvent
    public static void onRegisterSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(
                Identifier.fromNamespaceAndPath(GunMod.MOD_ID, "gun"),
                GunSpecialRenderer.Unbaked.MAP_CODEC
        );
        event.register(
                Identifier.fromNamespaceAndPath(GunMod.MOD_ID, "ammo"),
                AmmoSpecialRenderer.Unbaked.MAP_CODEC
        );
        event.register(
                Identifier.fromNamespaceAndPath(GunMod.MOD_ID, "attachment"),
                AttachmentSpecialRenderer.Unbaked.MAP_CODEC
        );
    }
}
