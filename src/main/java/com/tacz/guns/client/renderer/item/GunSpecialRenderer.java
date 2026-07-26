package com.tacz.guns.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class GunSpecialRenderer implements SpecialModelRenderer<ItemStack> {

    @Override
    public @Nullable ItemStack extractArgument(ItemStack stack) {
        return stack;
    }

    @Override
    public void submit(ItemStack stack, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        // 从 item atlas 获取 sprite（slot 纹理已注册到 item atlas）
        var sprite = Minecraft.getInstance().getModelManager().getAtlas(TextureAtlas.LOCATION_ITEMS)
                .getSprite(Identifier.fromNamespaceAndPath("tacz", "item/ak47"));
        // 从 sprite 创建 render type（使用 atlas + sprite uv 裁剪）
        var renderType = sprite.renderType(RenderTypes.cutout());

        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(-1, -1, 1);
        int light = lightCoords, overlay = overlayCoords;
        submitNodeCollector.submitCustomGeometry(poseStack, renderType, (pose, consumer) -> {
            // 用 sprite 包裹 consumer，自动处理 UV
            var spriteConsumer = sprite.wrap(consumer);
            final int c = 0xFFFFFFFF;
            spriteConsumer.addVertex(pose,0,0,0).setColor(c).setUv(0,0).setOverlay(overlay).setLight(light).setNormal(0,0,1);
            spriteConsumer.addVertex(pose,1,0,0).setColor(c).setUv(1,0).setOverlay(overlay).setLight(light).setNormal(0,0,1);
            spriteConsumer.addVertex(pose,1,1,0).setColor(c).setUv(1,1).setOverlay(overlay).setLight(light).setNormal(0,0,1);
            spriteConsumer.addVertex(pose,0,1,0).setColor(c).setUv(0,1).setOverlay(overlay).setLight(light).setNormal(0,0,1);
        });
        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<org.joml.Vector3fc> output) {
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked<ItemStack> {
        public static final MapCodec<GunSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new GunSpecialRenderer.Unbaked());
        @Override public GunSpecialRenderer bake(SpecialModelRenderer.BakingContext context) { return new GunSpecialRenderer(); }
        @Override public MapCodec<GunSpecialRenderer.Unbaked> type() { return MAP_CODEC; }
    }
}
