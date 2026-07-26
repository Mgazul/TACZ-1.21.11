package com.tacz.guns.mixin.client;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import org.spongepowered.asm.mixin.Mixin;

/**
 * TODO: 26.2 - ItemInHandLayer now uses submit/extractRenderState pattern.
 * Gun model rendering needs to be reworked for ItemStackRenderState system.
 */
@Mixin(ItemInHandLayer.class)
public class ItemInHandLayerMixin {
}
