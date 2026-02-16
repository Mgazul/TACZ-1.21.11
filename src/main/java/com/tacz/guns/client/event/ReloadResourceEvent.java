package com.tacz.guns.client.event;

import com.tacz.guns.client.resource.InternalAssetLoader;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ReloadResourceEvent {
    public static final Identifier BLOCK_ATLAS_TEXTURE = Identifier.parse("textures/atlas/blocks.png");

    @SubscribeEvent
    public static void onTextureStitchEventPost(TextureAtlasStitchedEvent event) {
        if (BLOCK_ATLAS_TEXTURE.equals(event.getAtlas().location())) {
            // InternalAssetLoader 需要加载一些默认的动画、模型，需要先于枪包加载。
            InternalAssetLoader.onResourceReload();
//            ClientReloadManager.reloadAllPack();
        }
    }
}
