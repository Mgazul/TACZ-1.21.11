package com.tacz.guns.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.client.resource.GunDisplayInstance;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class GunSpecialRenderer implements SpecialModelRenderer<ItemStack> {

    @Override
    public @Nullable ItemStack extractArgument(ItemStack stack) {
        return stack;
    }

    @Override
    public void submit(ItemStack stack, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        Identifier slotTexture = TimelessAPI.getGunDisplay(stack).map(GunDisplayInstance::getSlotTexture).orElse(null);
        if (slotTexture == null) return;

        RenderType renderType = RenderTypes.entityCutout(slotTexture);
        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(-1, -1, 1);
        // 写入一个简单面片（Slot 图标）
        submitNodeCollector.submitCustomGeometry(poseStack, renderType, (pose, consumer) -> {
            final float s = 1.0f;
            final int white = 0xFFFFFFFF;
            // 正面片
            consumer.addVertex(pose, 0, 0, 0).setColor(white).setUv(0, 0).setOverlay(overlayCoords).setLight(lightCoords).setNormal(pose, 0, 0, 1);
            consumer.addVertex(pose, s, 0, 0).setColor(white).setUv(1, 0).setOverlay(overlayCoords).setLight(lightCoords).setNormal(pose, 0, 0, 1);
            consumer.addVertex(pose, s, s, 0).setColor(white).setUv(1, 1).setOverlay(overlayCoords).setLight(lightCoords).setNormal(pose, 0, 0, 1);
            consumer.addVertex(pose, 0, s, 0).setColor(white).setUv(0, 1).setOverlay(overlayCoords).setLight(lightCoords).setNormal(pose, 0, 0, 1);
        });
        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<org.joml.Vector3fc> output) {
        output.accept(new Vector3f(0, 0, 0));
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked<ItemStack> {
        public static final MapCodec<GunSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new GunSpecialRenderer.Unbaked());

        @Override
        public GunSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
            return new GunSpecialRenderer();
        }

        @Override
        public MapCodec<GunSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
