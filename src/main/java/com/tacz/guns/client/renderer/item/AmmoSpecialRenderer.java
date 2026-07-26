package com.tacz.guns.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.IAmmo;
import com.tacz.guns.client.model.SlotModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class AmmoSpecialRenderer implements SpecialModelRenderer<ItemStack> {
    private static final SlotModel SLOT_AMMO_MODEL = new SlotModel();

    @Override
    public @Nullable ItemStack extractArgument(ItemStack stack) {
        return stack;
    }

    @Override
    public void submit(ItemStack stack, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        if (!(stack.getItem() instanceof IAmmo iAmmo)) return;
        Identifier slotTexture = TimelessAPI.getClientAmmoIndex(iAmmo.getAmmoId(stack))
                .map(index -> index.getSlotTextureLocation()).orElse(null);
        if (slotTexture == null) return;

        RenderType renderType = RenderTypes.entityCutout(slotTexture);
        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(-1, -1, 1);
        submitNodeCollector.submitCustomGeometry(poseStack, renderType, (pose, vertexConsumer) -> {
            SLOT_AMMO_MODEL.renderToBuffer(poseStack, vertexConsumer, lightCoords, overlayCoords);
        });
        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<org.joml.Vector3fc> output) {
        output.accept(new Vector3f(0, 0, 0));
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked<ItemStack> {
        public static final MapCodec<AmmoSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new AmmoSpecialRenderer.Unbaked());

        @Override
        public AmmoSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
            return new AmmoSpecialRenderer();
        }

        @Override
        public MapCodec<AmmoSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
